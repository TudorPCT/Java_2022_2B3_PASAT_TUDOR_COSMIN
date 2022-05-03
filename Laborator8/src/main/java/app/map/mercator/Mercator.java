package app.map.mercator;

public abstract class Mercator {

    protected final static double RADIUS_MAJOR = 6378137.0;
    protected final static double RADIUS_MINOR = 6356752.3142;

    public static double xAxisProjection(double input) {
        return Math.toRadians(input) * RADIUS_MAJOR;
    }

    public static double yAxisProjection(double input) {
        return Math.log(Math.tan(Math.PI / 4 + Math.toRadians(input) / 2));// * RADIUS_MAJOR;
    }
}