package engine;

import java.util.Objects;

public class RenderingLayer {

    protected final int order;

    public RenderingLayer(final int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "RenderingLayer{" +
                "order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RenderingLayer that = (RenderingLayer) o;
        return order == that.order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order);
    }
}
