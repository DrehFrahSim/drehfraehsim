package drehfraehsim.view;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.*;


public class thisMain extends Application {
  public static final int WIDTH = 1400;
  public static final int HEIGHT = 800;
  Group Werkstück;
  private ArrayList<Shape> Points;
  @Override
  public void start(Stage primarystage) throws Exception {

     Box cube = new Box(20,20,20);
     List<double[]> Points = getRandomPoints();
    int count= Points.size();
    Group group = new Group();
    Werkstück = new Group();
    for(int i = 0; i < count; ++i)
      {
        Sphere sphere = new Sphere(1);
        sphere.translateXProperty().set((WIDTH/2)+ Points.get(i)[0]);
        sphere.translateYProperty().set((HEIGHT/2.5)+ Points.get(i)[1]);
        sphere.translateZProperty().set(Points.get(i)[2]);
        Werkstück.getChildren().add(sphere);
      }
    group.getChildren().add(cube);
    group.getChildren().add(Werkstück);
    cube.translateXProperty().set((WIDTH/3));
    cube.translateYProperty().set((HEIGHT/3));
    cube.translateZProperty().set(0);


    Camera camare = new PerspectiveCamera();

    Scene scene = new Scene(group, WIDTH, HEIGHT);
    scene.setFill(Color.SILVER);
  scene.setCamera(camare);




    primarystage.setTitle("Hello!");
    primarystage.setScene(scene);
    primarystage.show();
    checkShapeIntersection(cube, Werkstück);


    primarystage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
      switch (event.getCode())
      {
        case W:
          group.translateZProperty().set(group.getTranslateZ()+10);
          break;
        case S:
          group.translateZProperty().set(group.getTranslateZ()-10);
          break;
        case A:
          group.translateXProperty().set(group.getTranslateX()+10);
          break;
        case D:
          group.translateXProperty().set(group.getTranslateX()-10);
          break;
        case Q:
          group.translateYProperty().set(group.getTranslateY()+10);
          break;
        case E:
          group.translateYProperty().set(group.getTranslateY()-10);
          break;

        case UP:
          cube.translateYProperty().set(cube.getTranslateY()+10);
          checkShapeIntersection(cube, Werkstück);
          break;
        case DOWN:
          cube.translateYProperty().set(cube.getTranslateY()-10);
          checkShapeIntersection(cube, Werkstück);
          break;
        case RIGHT:
          cube.translateXProperty().set(cube.getTranslateX()-10);
          checkShapeIntersection(cube, Werkstück);
          break;
        case LEFT:
          cube.translateXProperty().set(cube.getTranslateX()+10);
          checkShapeIntersection(cube, Werkstück);
          break;

      }
    });


  }

  public static void main(String[] args) { launch();}

  public static double dot(double[] vector1, double[] vector2) {
    double result = 0.0d;
    if (vector1.length != vector2.length) {
      System.err.println("two input vectors must me equal in length!");
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < vector1.length; i++) {
      result += vector1[i] * vector2[i];
    }
    return result;
  }

  public  List<double[]> getRandomPoints() {
    double[] bottom = {1, 1, 1};
    double[] top = {1, 60000, 1};
    List<double[]> Points = new ArrayList<double[]>();

    int Radius = 60;
    int PointNumberPerItera = 100000;
    double norm = Math.sqrt(dot(top, bottom));

    for (int i = 0; i < PointNumberPerItera; i++) {
      double[] point = {Math.random()*(Radius), Math.random()*norm , Math.random()*(Radius)};
      Points.add(point);
      }
    return Points;
      }

   synchronized private  void  checkShapeIntersection(Box Schneide, Group Werk) {
    boolean collisionDetected = false;
    Group toRem = new Group();
     double wit =Schneide.getWidth();
    double dep = Schneide.getDepth();
    double hei = Schneide.getHeight();

    double Sx = Schneide.getTranslateX();
    double Sy = Schneide.getTranslateY();
    double Sz = Schneide.getTranslateZ();

     Iterator test = Werk.getChildren().iterator();

     while (test.hasNext()){
     Sphere  x = (Sphere) test.next();
      double xX= x.getTranslateX();
      double xY= x.getTranslateY();
      double xZ= x.getTranslateZ();

      if( (xX>=Sx-(wit/2) && xX<=Sx+(wit/2))&&(xY>=Sy-(hei/2) && xY<=Sy+(hei/2))&&(xZ>=Sz-(dep/2) && xZ<=Sz+(dep/2)))
      {
        collisionDetected = true;
        toRem.getChildren().add(x);
              }
    }

     if(collisionDetected){
    toRem.getChildren().removeAll();
       collisionDetected = false;
     }
  }


}

