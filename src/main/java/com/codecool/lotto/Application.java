package com.codecool.lotto;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        int quantity = 6;
        int maxNumber = 45;
        int[] numbersPlayer = askForNumbers(quantity);
        int[] winningLottoNumbers = generateRandomNumbers(quantity, maxNumber);
        int[] matchingNumbers = findMatchingNumbers(numbersPlayer, winningLottoNumbers);

        messageNumbers ("You entered:", Arrays.toString(numbersPlayer));
        messageNumbers ("Random numbers generated:", Arrays.toString(winningLottoNumbers));
        messageForMatchingNumbers(matchingNumbers);

    }

    private static String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    private static int[] parseInput(String input) {
        String[] inputArray = input.split(" ");
        int[] numbers = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            numbers[i] = Integer.parseInt(inputArray[i]);
        }
        return numbers;
    }

    private static int[] askForNumbers(int count) {
        System.out.println("Enter " + count + " numbers between 1-45");
        int[] numbers = new int[count];
        for (int i = 0; i < count; i++) {
            boolean validInput = false;
            while (!validInput) {
                    int number = parseInput(getInput())[0];
                    if (number >= 1 && number <= 45) {
                        numbers[i] = number;
                        validInput = true;
                    } else {
                        System.out.println("Invalid input! Please enter a number between 1 and 45.");
                    }
            }
        }
        return numbers;
    }

    private static int[] generateRandomNumbers(int count, int maxNumber) {
        int[] randomNumbers = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            randomNumbers[i] = random.nextInt(maxNumber) + 1;
        }
        return randomNumbers;
    }

    private static int[] findMatchingNumbers(int[] numbers, int[] targetNumbers) {
        int count = 0;
        for (int number : numbers) {
            for (int targetNumber : targetNumbers) {
                if (number == targetNumber) {
                    count++;
                    break;
                }
            }
        }

        int[] matchingNumbers = new int[count];
        int index = 0;
        for (int number : numbers) {
            for (int targetNumber : targetNumbers) {
                if (number == targetNumber) {
                    matchingNumbers[index] = number;
                    index++;
                    break;
                }
            }
        }
        return matchingNumbers;
    }

    private static void messageForMatchingNumbers (int[] matching){
        if (matching.length > 0) {
            System.out.print("Congratulations! You have a matching ");
            if (matching.length > 1) {
                System.out.print("numbers: ");
            } else {
                System.out.print("number: ");
            }
            System.out.println(Arrays.toString(matching));
        } else {
            System.out.println("Sorry, none of your numbers match the generated numbers.");
        }
    }

    private static void messageNumbers (String message, String number){
        System.out.println(message);
        System.out.println(number);
    }
}

