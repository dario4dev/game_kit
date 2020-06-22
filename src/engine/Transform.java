package engine;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Transform {

    public class DIMENSION_2D {
        public static final int X = 0;
        public static final int Y = 1;
    }

    private AffineTransform affineTransform;
    private List<Double> position2D;
    private List<Double> scale2D;

    public Transform(){
        affineTransform = new AffineTransform();
        position2D = new ArrayList<>(2);
    }

    // Getter/Setter Translation
    public List<Double> getPosition2D() {
        position2D.set(DIMENSION_2D.X, affineTransform.getTranslateX());
        position2D.set(DIMENSION_2D.Y, affineTransform.getTranslateY());

        return position2D;
    }

    public Double getPositionX() {
        return affineTransform.getTranslateX();
    }

    public Double getPositionY() {
        return affineTransform.getTranslateY();
    }

    public void setPosition(final double x, final double y) {
        affineTransform.translate(x, y);
    }

    public void setPositionX(final double x) {
        affineTransform.translate(x, affineTransform.getTranslateY());
    }

    public void setPositionY(final double y) {
        affineTransform.translate(affineTransform.getTranslateX(), y);
    }

    public void setPosition(final List<Double> position2D) {
        affineTransform.translate(position2D.get(DIMENSION_2D.X), position2D.get(DIMENSION_2D.Y));
    }

    //Getter setter Scale
    public List<Double> getScale() {
        scale2D.set(DIMENSION_2D.X, affineTransform.getScaleX());
        scale2D.set(DIMENSION_2D.Y, affineTransform.getScaleY());
        return scale2D;
    }

    public Double getScaleX() {
        return affineTransform.getScaleX();
    }

    public Double getScaleY() {
        return affineTransform.getScaleY();
    }

    public void setScale(final Double x, final Double y) {
        affineTransform.scale(x, y);
    }

    public void setScaleX(final Double x) {
        affineTransform.scale(x, affineTransform.getScaleY());
    }

    public void setScaleY(final Double y) {
        affineTransform.scale(affineTransform.getScaleX(), y);
    }

    public void setScale(final List<Double> scale2D) {
        affineTransform.scale(scale2D.get(DIMENSION_2D.X), scale2D.get(DIMENSION_2D.Y));
    }

    //Getter/Setter Rotations Todo @Dario later.
}
