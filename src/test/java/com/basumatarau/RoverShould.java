package com.basumatarau;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RoverShould {

    private Rover roverOnGridWithNoObstacles;
    private Rover roverOnGridWithObstacles;

    @BeforeEach
    public void init(){
        LocationPoint[] obstacles =
                new LocationPoint[]{LocationPoint.at(1,1), LocationPoint.at(3,3)};
        Grid gridWithObstacles = new Grid(10, 10, obstacles);
        Grid gridWithNoObstacles = new Grid(10, 10);
        this.roverOnGridWithNoObstacles = new Rover(gridWithNoObstacles);
        this.roverOnGridWithObstacles = new Rover(gridWithObstacles);
    }

    @ParameterizedTest
    @CsvSource({"r,0:0:E", "rr,0:0:S", "rrr,0:0:W", "rrrr,0:0:N"})
    public void rotate_right(String commands, String expectedOutput){
        Assertions.assertEquals(expectedOutput, roverOnGridWithNoObstacles.execute(commands));
    }

    @ParameterizedTest
    @CsvSource({"l,0:0:W","ll,0:0:S","lll,0:0:E","llll,0:0:N"})
    public void rotate_left(String commands, String expectedOutput){
        Assertions.assertEquals(expectedOutput, roverOnGridWithNoObstacles.execute(commands));
    }

    @ParameterizedTest
    @CsvSource({"f,0:1:N","ff,0:2:N","ffff,0:4:N","rff,2:0:E","lbb,2:0:W"})
    public void move_forward(String commands, String expectedOutput){
        Assertions.assertEquals(expectedOutput, roverOnGridWithNoObstacles.execute(commands));
    }

    @ParameterizedTest
    @CsvSource({"ffffffffff,0:0:N", "lffffffffff,0:0:W", "llffffffffff,0:0:S", "rffffffffff,0:0:E"})
    public void wrap_from_one_border_edge_to_another(String commands, String expectedOutput){
        Assertions.assertEquals(expectedOutput, roverOnGridWithNoObstacles.execute(commands));
    }

    @ParameterizedTest
    @CsvSource({"rflff,O:1:0:N", "rflffffff,O:1:0:N", "rffflffff,O:3:2:N"})
    public void return_to_the_last_position_without_obstacle(String commands, String expectedOutput){
        Assertions.assertEquals(expectedOutput, roverOnGridWithObstacles.execute(commands));
    }
}
