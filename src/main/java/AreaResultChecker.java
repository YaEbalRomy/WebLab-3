public class AreaResultChecker {

    private AreaResultChecker() {
    }
    public static boolean isPointInArea(Point point) {
        return isCoordinatesInArea(Double.parseDouble(point.getX()), Double.parseDouble(point.getY()), Double.parseDouble(point.getR()));
    }
    public static boolean isCoordinatesInArea(double x, double y, double r) {
        if ((x <= 0) && (x >= -r) && (y >= -r) && (y <= 0)) {
            return true;
        }
        if ((x <= 0) && (y >= 0) && (y <= x + r)) {
            return true;
        }
        if ((x >= 0) && (y <= 0) && (x * x + y * y <= (r) * (r))) {
            return true;
        }
        return false;
    }
}