package com.basumatarau;

public enum RoverCommand {
    ROTATE_LEFT {
        @Override
        public void execute(Rover rover) {
            rover.rotateLeft();
        }
    },
    ROTATE_RIGHT {
        @Override
        public void execute(Rover rover) {
            rover.rotateRight();
        }
    },
    MOVE_FORWARD {
        @Override
        public void execute(Rover rover) {
            rover.moveForward();
        }
    },
    MOVE_BACKWARDS {
        @Override
        public void execute(Rover rover) {
            rover.moveBackwards();
        }
    };

    public abstract void execute(Rover rover);

    public static RoverCommand ofValue(Character ch) {
        switch (ch) {
            case 'l' : return ROTATE_LEFT;
            case 'r' : return ROTATE_RIGHT;
            case 'f' : return MOVE_FORWARD;
            case 'b' : return MOVE_BACKWARDS;
            default: throw new IllegalArgumentException("command couldn't be resolved");
        }
    }
}
