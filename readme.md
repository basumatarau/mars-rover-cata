##Kata description

Develop an api that moves a rover around on a grid.

1. You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
2. The rover receives a character array of commands.
3. Implement commands that move the rover forward/backward (f,b).
4. Implement commands that turn the rover left/right (l,r).
5. Implement wrapping from one edge of the grid to another. (planets are spheres after all)
6. Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.


solution api description:
command exampe: lffrblffff 
new location reported: <x>:<y>:<direction> where <x>/<y> and <direction> - the currnet point on a grid and direction the rover is currently moving;
obstacle reported: O:<x>:<y>:<direction> where <x>/<y> - last point before obstacle and <direction> - direction the rover was moving right before it hit the obstacle