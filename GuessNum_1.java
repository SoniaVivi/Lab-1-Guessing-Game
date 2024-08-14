// Siona Vivian
// 30/6/24
// CS 143
// Lab 1: Guessing Game
/*
The program is to allow to let the user play a guessing game.
Ex. The program chooses a number, the program tells if the user
is correct or if the number is higher or lower than the guess.
The user can play multiple games and the program tells the overall stats.
*/
// Sources: https://stackoverflow.com/a/4377856 / Dietel

import java.util.Scanner;
import java.util.Random;


public class GuessNum_1 {
   private static final Random randomGenerator = new Random();
   private static final int MAX_NUM = 100;

   // main method
   public static void main(String args[]) {
      boolean continueGame = true;
      int bestGame = 99 * 99 * 99;
      int totalGuesses = 0;
      int totalGames = 0;

      printIntro();

      while (continueGame) {
         continueGame = false;
         int lastGuessCount = playGame();

         totalGames += 1;
         totalGuesses += lastGuessCount;

         if (lastGuessCount < bestGame) {
            bestGame = lastGuessCount;
         }

         continueGame = askPlayerForContinuation();
      }

      printStatistics(totalGames, totalGuesses, bestGame);

   } // end method main

   public static boolean askPlayerForContinuation() {
      Scanner scanner = new Scanner(System.in);

      System.out.printf("%nDo you want to play again? ");
      String firstChar = scanner.next().toLowerCase().substring(0, 1);

      return firstChar.equals("y");

   }

   // Prints formatted statistics of the games played
   public static void printStatistics(int games, int guesses, int bestGame) {
      // In the sample log there are 4 spaces, not a tab
      String indent = "    ";
      float guessRatio = (float) guesses / games;
      System.out.printf("%nOverall results:%n");
      System.out.printf("%stotal games   = %d%n",indent, games);
      System.out.printf("%stotal guesses = %d%n",indent, guesses);
      System.out.printf("%sguesses/game  = %.1f%n",indent, guessRatio);
      System.out.printf("%sbest game     = %d%n",indent, bestGame);
   }

   // Introduces the game to the user
   public static void printIntro() {
      System.out.println("This program allows you to play a guessing game.");
      System.out.println("I will think of a number between 1 and ");
      System.out.println("100 and will allow you to guess until");
      System.out.println("you get it. For each guess, I will tell you");
      System.out.println("whether the right answer is higher or lower");
      System.out.printf("than your guess.%n");
   }

   // Plays a single game of the guessing game
   public static int playGame() {
      int targetNumber = randomGenerator.nextInt(MAX_NUM) + 1;
      int lastGuess = -1;
      int guessCount = 0;

      System.out.printf("%nI'm thinking of a number between 1 and");
      System.out.printf(" %d...%n", MAX_NUM);

      while (lastGuess != targetNumber) {
         lastGuess = getGuess();
         guessCount += 1;

         if (isCorrectGuess(lastGuess, targetNumber)) {
            String pluralModifier = "";

            if (guessCount != 1) {
               pluralModifier = "es";
            }

            System.out.println("Congratulations. You guessed the number!");
            System.out.printf("You got it right in %d guess", guessCount);
            System.out.print(pluralModifier);

         }
      }
      return guessCount;
   }

   public static boolean isCorrectGuess(int guess, int target) {
      if (guess == target) {
         return true;

      } else if (guess < target) {
         System.out.println("It's higher.");

      } else {
         System.out.println("It's lower.");
      }

      return false;
   }

   public static int getGuess() {
      Scanner scanner = new Scanner(System.in);

      System.out.print("Your guess? ");

      return scanner.nextInt();
   }
} // end class SVGuess
