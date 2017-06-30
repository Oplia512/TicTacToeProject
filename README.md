# TicTacToeDesktop
A stub implementation of a tic tac toe ticTacToeGame with desktop ui.

In order to run this glorious application, you must:
1. Clone this repository to your local repo (in order to do it, you have to have [Git](https://git-scm.com/) installed)
2. Have [Maven](https://maven.apache.org/) installed (Works on 3.5.0 Maven version)
3. Have [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed (Works on 1.8.0_131 java version).
4. Open command window in the top directory of the project, `TicTacToeProject`.
4. Run `mvn package` in command line.
5. When done, get into `target` directory
6. Open command line and type `java -jar TicTacToe-1.0-SNAPSHOT.jar`

What already has been implemented:
1. Player vs player ticTacToeGame
2. Player's score is stored
3. Player can start multiple games
4. Player can determine how big the board is
5. Player can choose a field by it's single number
6. Exceptions and displaying their message on screen
7. User input validation
8. The board's representation is formatted according to it's size

TODO:
1. Playing vs AI
2. Player name choosing
3. Localization
4. Tests for the rest of the classes
5. Other winning conditions


Minimum reqs:
* ~~It must work (it it doesn’t, it’s disqualified): if I cannot play, it doesn't work.~~
* 60% unit test code coverage. Status: 
* Functions in accordance with functional requirements. 
* Code quality – non-OO code is tolerated in little amounts. 
* Project mantra followed (Git, Maven, test cases, etc.). 
* ~~Console UI, no graphics (though see extra reqs)~~
* ~~Hot-seat game, no network (though see extra reqs)~~
* ~~Both players are human (though see extra reqs)~~
* ~~only Java API (JDK8, SE) and TestNG~~

Functional requirements.
* It is "best of three", though I can quit mid-way through.
* ~~Characters: O (naught) and X (cross)~~
* ~~Players have names and scores.~~
* Winner has better score. Draw is possible.

Interactive:
* ~~it should accept players instructions about each move~~
* it should ask who begins
* ~~it informs about session result, who’s turn it is now and the like~~
* Match gives points: win 3, draw 1, loss 0. 3 matches == game.
* ~~Game works with square or rectangular board.~~
* Player wins, if he has unbroken line of his characters, in a row, in a column or diagonally.
* Winning is announced in a message: Wygrywa O. O: 1 X: 0 (numbers are current points).

Game is configurable:
* ~~Board dimensions: 3x3, 4x4, 99x101, etc. (user provides)~~
* Winning condition has variable number of characters: 3, 4, 5, etc. (user provides)
* Game messages should have configurable target: console (System.out) or logs (for the sake of this exercise it’s OK to make it System.err), or external printer.
* before game starts it asks who goes first, O or X
* ~~We are bi-lingual: Polish and English are fine. In future we want to add more languages: messages are to be read from a file for chosen language. Choosing the language depends on configuration variable.~~