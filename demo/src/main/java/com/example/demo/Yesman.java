package com.example.demo;

import java.util.Scanner;

public class Yesman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Yes or no? ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("no")) {
                System.out.println("Program stopped.");
                break;
            } else if (input.equals("yes")) {
                System.out.println("Continuing...");
            } else {
                System.out.println("Invalid input. Please enter Yes or No.");
            }
        }

        scanner.close();
    }
}
