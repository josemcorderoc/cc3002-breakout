# Breakout

This project is a clone of the game [Breakout](https://en.wikipedia.org/wiki/Breakout_(video_game)), developed as homework 2 and 3 of the course CC3002 (Metodologías de Diseño y Programación) 2018 of [Universidad de Chile](http://www.uchile.cl).

It contains both the logic and the graphic interface of game, which is based on:

* [Graphical User Interface (GUI)](../cc3002-breakout/src/main/java/gui)
* [Controller](../cc3002-breakout/src/main/java/controller/Game.java)
* [Levels](../cc3002-breakout/src/main/java/logic/level)
* [Bricks](../cc3002-breakout/src/main/java/logic/brick)

For this, we used the following design patterns:

* Facade Pattern (in package [facade](../cc3002-breakout/src/main/java/facade))
* Observer Pattern (in build-in package java.util)
* Null Pattern (in package [logic.level](../cc3002-breakout/src/main/java/logic/level))
* Visitor Pattern (in package [visitor](../cc3002-breakout/src/main/java/visitor))

### Features

This version of the game has 2 major features and 2 minor features:

#### Major features
* **Changing state of bricks**: when a brick is hit, It color changes to a less saturated color.
* **New brick**: we create the [PlasticBrick](../cc3002-breakout/src/main/java/logic/brick/PlasticBrick.java), which has 15
 hit points and the special function to destroy all the bricks in a level when the plastic brick is destroyed.
 The max quantity of Plastic Bricks in one level is 1 (the ProbOfPlastic is the probability of having one plastic brick).


#### Minor features
* **Sound at hit**: when you hit a brick, the game plays a sound (the sound is different depending of the type of brick).
 Also, when a brick is destroyed, 
* **Image with balls left**: we replaced the label with the number of balls left (lives) with drawing the balls in the left top
of the 

### Bricks

We have 4 different types of bricks:
* **Glass Brick**: light blue color, 1 hit point.
* **Wooden Brick**: blue color, 3 hit points.
* **Metal Brick**: dark red color, 10 hit points.
* **Plastic Brick**: yellow color, 15 hit points.

## Getting Started

### Prerequisites

* [Java 8](https://www.java.com/en/download/faq/java8.xml) (OpenJDK 1.8)
* [Maven]((https://maven.apache.org/))
* [FXGL](https://github.com/AlmasB/FXGL)
* [Java FX](https://www.java.com/en/download/faq/javafx.xml) (version [openjfx](https://packages.qa.debian.org/o/openjfx.html))
* [JUnit 4](https://junit.org/junit4/) (used for testing)

## Running the game

To run the game, execute the main method of class BreakoutApp.

### Keys for playing

* Key N: creates a new level
* Key SPACE: launch the ball (starts the game)
* Key ESC: pauses the game (open a Menu for Resume or Quit)
* Key A: moves the player to the left
* Key D: moves the player to the right

## Running the tests

All the project tests are in package [test.java](../cc3002-breakout/src/test/java) and can be executed one by one.

## Versioning

We use [Git](https://git-scm.com/) for versioning. For the versions available, see the [tags on this repository](https://github.com/josemcorderoc/cc3002-breakout/tags). 

## Authors

* **José Miguel Cordero Carvacho** - [josemcorderoc](https://github.com/josemcorderoc)

## License

This project is licensed under the [GNU GPL-3](https://www.gnu.org/licenses/gpl-3.0.en.html) License.