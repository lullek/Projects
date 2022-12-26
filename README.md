## Projects
Lucas Kerslow

Some of the code lacks comments. I would be commenting the code a lot more in a professional project.


### Factorial experiment

This was an experiment I did when I realized that the sum when you add all the numbers from the result of many factorials, until only one number remains, became 9. I did this to test to see how many become 9, and the program shows the result up to 20!. An example:

10! = 10*9*8*7*6*5*4*3*2*1 = 3628800 => 3+6+2+8+8+0+0 = 27 => 2+7 = 9


### GUI for library database

This was an assignment for my university. The task was to make a GUI for a database in PostgreSQL. The database was handed to us, so I haven't made it myself. Although we made similar ones. The database is local on my computer, but I included the .psql-files so that you can try it if you want. The dbURL, userName and password strings in the connectToPSQL() method have to be changed to ones matching your own database server.

Main class is in JavaGUI.java

To run the program:
java -cp "/"filepath"/JavaGUI/jbdc/postgresql-42.3.1.jar:." JavaGUI

Where "filepath" has to be changed to the actual filepath.


### Game project

This is a project me and another person made for the university. Since I had previous experience with making a game, the Snake game also included in my projects, I made the basic game mechanics in the beginning, while my partner made many of the images, or "sprites", for the graphics. As the project progressed, she also wrote code, like for example how the different rooms should be built. So I have not done all of the coding myself, but I did many of the things. Examples of my code is the Java Swing stuff for making the game appear, the puzzles, and how the game objects, characters and enemies should behave. The music is my composition too.

Main class is in Game.java


### Snake

This was the first game I built. I did it on my spare time to learn, since we have not learned how to make graphics at the university. I used tutorials to learn how Java Swing worked, and to get an idea of how to build the game, but tried to solve as much stuff I could on my own without copying the tutorials straight off. It was a really fun experience to build my first game.

Main class is in Snake.java