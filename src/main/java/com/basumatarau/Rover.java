package com.basumatarau;

public class Rover {
    private Direction direction;
    private LocationPoint locationPoint;
    private final Grid grid;

    public Rover(Grid grid) {
        this.direction = Direction.initial();
        this.locationPoint = LocationPoint.initial();
        this.grid = grid;
    }

    public String execute(String commands) {
        try {
            for (char command : commands.toCharArray()) {
                RoverCommand.ofValue(command).execute(this);
            }
        } catch (ObstacleEncounteredException e) {
            return getLastLocationBeforeObstacleDescription();
        }
        return getCurrentLocationDescription();
    }

    private String getLastLocationBeforeObstacleDescription() {
        return String.join(":", "O", locationPoint.getX().toString(),
                locationPoint.getY().toString(), direction.getForward());
    }

    void moveForward() {
        locationPoint = moveOneStep(direction, locationPoint, true);
    }

    void moveBackwards() {
        locationPoint = moveOneStep(direction, locationPoint, false);
    }

    void rotateLeft() {
        direction = Direction.ofValue(direction.getOnLeft());
    }

    void rotateRight() {
        direction = Direction.ofValue(direction.getOnRight());
    }

    private String getCurrentLocationDescription() {
        return String.join(":", locationPoint.getX().toString(), locationPoint.getY().toString(),
                direction.getForward());
    }

    private LocationPoint moveOneStep(Direction direction, LocationPoint locationPoint, boolean isForward) {
        int stepIncrement = isForward ? 1 : -1;
        LocationPoint newPosition;
        switch (direction) {
            case NORTH:
                newPosition = LocationPoint.at(locationPoint.getX(), locationPoint.getY() + stepIncrement);
                break;
            case EAST:
                newPosition = LocationPoint.at(locationPoint.getX() + stepIncrement, locationPoint.getY());
                break;
            case SOUTH:
                newPosition = LocationPoint.at(locationPoint.getX(), locationPoint.getY() - stepIncrement);
                break;
            default:
                newPosition = LocationPoint.at(locationPoint.getX() - stepIncrement, locationPoint.getY());
        }
        LocationPoint wrappedPosition = grid.wrapCoordinate(newPosition);
        if(grid.isObstacle(wrappedPosition)){
            throw new ObstacleEncounteredException(wrappedPosition);
        }
        return wrappedPosition;
    }

    private enum Direction {
        NORTH("N", "W", "E", "S"),
        EAST("E", "N", "S", "W"),
        SOUTH("S", "E", "W", "N"),
        WEST("W", "S", "N", "E");

        private final String forward;
        private final String onLeft;
        private final String onRight;
        private final String backwards;

        Direction(String forward, String onLeft, String onRight, String backwards) {
            this.forward = forward;
            this.onLeft = onLeft;
            this.onRight = onRight;
            this.backwards = backwards;
        }

        static Direction initial() {
            return Direction.NORTH;
        }

        public String getForward() {
            return forward;
        }

        public String getOnLeft() {
            return onLeft;
        }

        public String getOnRight() {
            return onRight;
        }

        public String getBackwards() {
            return backwards;
        }

        static Direction ofValue(String directionValue) {
            switch (directionValue) {
                case "N":
                    return NORTH;
                case "E":
                    return EAST;
                case "S":
                    return SOUTH;
                case "W":
                    return WEST;
                default:
                    throw new IllegalArgumentException("no direction available");
            }
        }
    }
}
