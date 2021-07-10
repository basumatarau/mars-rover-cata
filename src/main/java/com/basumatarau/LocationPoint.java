package com.basumatarau;

import java.util.Objects;

public class LocationPoint {
    private final Integer x;
    private final Integer y;

    private LocationPoint(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public static LocationPoint initial() {
        return new LocationPoint(0, 0);
    }

    public static LocationPoint at(Integer x, Integer y) {
        return new LocationPoint(x, y);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationPoint that = (LocationPoint) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
