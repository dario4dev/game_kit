package engine;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Transform {

    public class DIMENSION_2D {
        public static final int X = 0;
        public static final int Y = 1;
    }

    private AffineTransform mAffineTransform;
    private ArrayList<Double> mPosition2D;
    private ArrayList<Double> mScale2D;

    public Transform(){
        mAffineTransform = new AffineTransform();
        mPosition2D = new ArrayList<>(2);
    }

    // Getter/Setter Translation
    public ArrayList<Double> GetPosition2D() {
        mPosition2D.set(DIMENSION_2D.X, mAffineTransform.getTranslateX());
        mPosition2D.set(DIMENSION_2D.Y, mAffineTransform.getTranslateY());

        return mPosition2D;
    }

    public Double GetPositionX() {
        return mAffineTransform.getTranslateX();
    }

    public Double GetPositionY() {
        return mAffineTransform.getTranslateY();
    }

    public void SetPosition(final double x, final double y) {
        mAffineTransform.translate(x, y);
    }

    public void SetPositionX(final double x) {
        mAffineTransform.translate(x, mAffineTransform.getTranslateY());
    }

    public void SetPositionY(final double y) {
        mAffineTransform.translate(mAffineTransform.getTranslateX(), y);
    }

    public void SetPosition(final ArrayList<Double> position2D) {
        mAffineTransform.translate(position2D.get(DIMENSION_2D.X), position2D.get(DIMENSION_2D.Y));
    }

    //Getter setter Scale
    public ArrayList<Double> GetScale() {
        mScale2D.set(DIMENSION_2D.X, mAffineTransform.getScaleX());
        mScale2D.set(DIMENSION_2D.Y, mAffineTransform.getScaleY());
        return mScale2D;
    }

    public Double GetScaleX() {
        return mAffineTransform.getScaleX();
    }

    public Double GetScaleY() {
        return mAffineTransform.getScaleY();
    }

    public void SetScale(final Double x, final Double y) {
        mAffineTransform.scale(x, y);
    }

    public void SetScaleX(final Double x) {
        mAffineTransform.scale(x, mAffineTransform.getScaleY());
    }

    public void SetScaleY(final Double y) {
        mAffineTransform.scale(mAffineTransform.getScaleX(), y);
    }

    public void SetScale(final ArrayList<Double> scale2D) {
        mAffineTransform.scale(scale2D.get(DIMENSION_2D.X), scale2D.get(DIMENSION_2D.Y));
    }

    //Getter/Setter Rotations Todo @Dario later.
}
