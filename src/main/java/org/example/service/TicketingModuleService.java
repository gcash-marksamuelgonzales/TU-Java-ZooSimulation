package org.example.service;

import org.example.models.Ticket;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TicketingModuleService {
    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Ticket> ticketsList = new ArrayList<>();

    static Path outputCsv = Paths.get("../resources/tickets.csv");

    public static void main(String[] args) {

        System.out.println("=== WELCOME TO THE ZOO TICKET SHOP ===");
        System.out.println();
        System.out.println();
        System.out.println("Would you like to buy a ticket? (1 = Yes | 2 = No)");
        System.out.print(">> ");

        try {
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter your name: ");
                String name = sc.next();
                System.out.print("Enter your age: ");
                int age = sc.nextInt();

                int ageRange = validateAgeRange(age);

                switch (ageRange) {
                    case 1:
                        System.out.println("You qualify for a free entrance!");
                        System.out.println("Ticket price: Php 0.00");
                        System.out.print("Would you like to proceed? (Yes/No): ");
                        String purchaseChoice = sc.next();

                        if (purchaseChoice.equalsIgnoreCase("yes")) {
                            int ticketNumber = randomizeTicketNumber();
                            System.out.println("Ticket purchased!");
                            System.out.printf("Your ticket code is: ZOO-%d", ticketNumber);
                        }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid input. Please enter either 1 or 2 only.");
        }
    }

    private static int validateAgeRange(int age) {

        if (age <= 5 && age >= 0) {
            return 1;
        } else if (age <= 17 && age >= 6) {
            return 2;
        } else if (age >= 18 && age <= 59) {
            return 3;
        } else if (age >= 60) {
            return 4;
        } else {
            System.out.println("Invalid age. Please try again.");
            System.out.print("Enter your age: ");
            age = sc.nextInt();
            validateAgeRange(age);
        }

        return 0;
    }

    private static int randomizeTicketNumber() {
        int ticketNumber = rand.nextInt(1000) + 1000;

        return ticketNumber;
    }
}
