package com.basumatarau;

public class ObstacleEncounteredException extends IllegalStateException {
    public ObstacleEncounteredException(LocationPoint point) {
        super(String.format("obstacle encountered at location %d:%d", point.getX(), point.getY()));
    }
}
