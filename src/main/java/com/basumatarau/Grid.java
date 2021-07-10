package com.basumatarau;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Grid {
    private final Integer maxWidth;
    private final Integer maxHeight;
    private final Set<LocationPoint> obstacles;

    public Grid(Integer maxWidth, Integer maxHeight, LocationPoint... obstacles) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;

        for (LocationPoint obstacle : obstacles) {
            if(obstacle.getY() >= maxHeight || obstacle.getX() >= maxWidth) {
                throw new IllegalArgumentException("obstacle outside of the grid");
            }
        }
        this.obstacles = Arrays.stream(obstacles).collect(Collectors.toSet());
    }

    LocationPoint wrapCoordinate(LocationPoint initial) {
        return LocationPoint.at(initial.getX() % maxWidth, initial.getY() % maxHeight);
    }

    boolean isObstacle(LocationPoint point) {
        return obstacles.stream().anyMatch(point::equals);
    }
}
