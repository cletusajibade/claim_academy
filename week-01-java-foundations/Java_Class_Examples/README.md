# Claim Academy: Classes and Objects

Use each numbered folder as a separate Java project. Each has its own Main class.
Do not compile all folders together. Examples use Java 8-compatible syntax and no external dependencies.

Run from a folder using a configured JDK:

    javac *.java
    java Main

Or open that folder in your Java IDE and run Main.

## Teaching sequence

- 01-student-basics: slides 7–15, no declared constructor. Output: Hi, I am Maya / 5 / Hi, I am Leo / 4.
- 02-student-constructor: slides 17–21, explicit constructor. Same output.
- 03-car-solution: guided practice answer. Output: 10 / 0.
- 04-bank-live-demo: slide 32. Output: true / false / 500.
- 06-lab-starter: share with learners at slide 36. It intentionally contains only scaffolding.
- 05-bank-lab-solution: instructor answer. Keep until learners finish.

## Scope

Student study hours and Car acceleration inputs are nonnegative in these introductory examples.
BankAccount uses whole cents, assumes a non-null, nonblank owner and amounts/totals that fit int.
The model is a teaching exercise, with no persistence, concurrency or production banking features.
A balance setter is intentionally absent. The owner setter assumes valid input.

The deck contains instructor prompts, timing, knowledge-check answers and lab guidance in speaker notes.
Slides 1–35 total 180 minutes including two 10-minute breaks. Slides 36–42 total 60 minutes.
Slides 43–48 are reference material outside the schedule.
