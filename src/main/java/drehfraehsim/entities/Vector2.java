package drehfraehsim.entities;

public record Vector2(double x, double y) {
    public static double distanzZwischenVectoren(Vector2 A, Vector2 B) {
        return Math.sqrt(Math.pow(A.x - B.x, 2) + Math.pow(A.y - B.y, 2));
    }

    public static double distanzZwischenVectoren(double Ax, double Ay, double Bx, double By) {
        return Math.sqrt(Math.pow(Ax - Bx, 2) + Math.pow(Ay - By, 2));
    }
}