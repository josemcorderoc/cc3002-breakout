# Breakout

This project is a clone of the game [Breakout](https://en.wikipedia.org/wiki/Breakout_(video_game)), developed as homework 2 of the course CC3002 (Metodologías de Diseño y Programación) 2018 of [Universidad de Chile](http://www.uchile.cl).

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


## Getting Started

### Prerequisites

* [Java 8](https://www.java.com/en/download/faq/java8.xml) (OpenJDK 1.8)
* [Maven]((https://maven.apache.org/))
* [FXGL](https://github.com/AlmasB/FXGL)
* [Java FX](https://www.java.com/en/download/faq/javafx.xml) (version [openjfx](https://packages.qa.debian.org/o/openjfx.html))
* [JUnit 4](https://junit.org/junit4/) (used for testing)

## Running the game

For running the game, you have 

## Running the tests

All the project tests are in package [test.java](../cc3002-breakout/src/test/java) and can be executed one by one.

## Versioning

We use [Git](https://git-scm.com/) for versioning. For the versions available, see the [tags on this repository](https://github.com/josemcorderoc/cc3002-breakout/tags). 

## Authors

* **José Miguel Cordero Carvacho** - [josemcorderoc](https://github.com/josemcorderoc)

## License

This project is licensed under the [GNU GPL-3](https://www.gnu.org/licenses/gpl-3.0.en.html) License.