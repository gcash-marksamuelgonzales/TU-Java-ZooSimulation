package org.example.service;

import org.example.models.Ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TicketingModuleService {
    private static Random rand = new Random();
    private static Scanner sc = new Scanner(System.in);
    public static ArrayList<Ticket> ticketsList = new ArrayList<>();
    private static ArrayList<String> ticketNumbers = new ArrayList<>();
    private static String choice;

    private static final int CHILD_PRICE = 0;
    private static final int STUDENT_PRICE = 75;
    private static final int ADULT_PRICE = 150;
    private static final int SENIOR_PRICE = 50;

    private static final String CHILD_DISCOUNT = "a FREE ENTRANCE";
    private static final String STUDENT_DISCOUNT = "a STUDENT discount";
    private static final String ADULT_NO_DISCOUNT = "a REGULAR ADULT ticket";
    private static final String SENIOR_DISCOUNT = "a SENIOR discount";

    static String fileName = "src/main/java/org/example/resources/tickets.csv";

    public static void main(String[] args) {

        ticketsList.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                Ticket temp = new Ticket(record[0], record[1]);
                ticketsList.add(temp);
                ticketNumbers.add(record[1]);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
        }

        System.out.println("=== WELCOME TO THE ZOO TICKET SHOP ===");
        System.out.println();
        System.out.println();
        System.out.println("Would you like to buy a ticket? (Yes/No)");
        System.out.print(">> ");

        initiatePurchase(false);
    }

    private static void initiatePurchase(boolean repeatPurchase) {
        try {
            if (repeatPurchase) {
                choice = "yes";
            } else {
                choice = sc.next();
            }

            if (choice.equalsIgnoreCase("yes")) {
                System.out.print("Enter your name: ");
                String name = sc.next();
                System.out.print("Enter your age: ");
                int age = sc.nextInt();

                int ageRange = validateAgeRange(age);

                switch (ageRange) {
                    case 1 -> proceedWithPurchase(name, CHILD_DISCOUNT, CHILD_PRICE);
                    case 2 -> proceedWithPurchase(name, STUDENT_DISCOUNT, STUDENT_PRICE);
                    case 3 -> proceedWithPurchase(name, ADULT_NO_DISCOUNT, ADULT_PRICE);
                    case 4 -> proceedWithPurchase(name, SENIOR_DISCOUNT, SENIOR_PRICE);
                    default -> System.out.println("Encountered problem with purchase. Please try again.");
                }
            } else if (choice.equalsIgnoreCase("no")) {
                System.out.println("Hope to see you again some other time. Thank you and goodbye for now!");
            } else {
                System.out.println("Invalid choice. Please try again.");
                System.out.println("Would you like to buy a ticket? (Yes/No)");
                System.out.print(">> ");
                initiatePurchase(false);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid input. Please enter either yes or no only.");
        }
    }

    private static void proceedWithPurchase(String name, String discountType, int price) {
        System.out.printf("You qualify for %s!\n", discountType);
        System.out.printf("Ticket price: Php %d.00\n", price);
        System.out.print("Would you like to proceed? (Yes/No): ");
        String purchaseChoice = sc.next();

        if (purchaseChoice.equalsIgnoreCase("yes")) {
            generateTicket(name);
        } else {
            System.out.println("See you next time, then!");
        }
    }

    private static void generateTicket(String name) {
        int ticketNumber = randomizeTicketNumber();

        if (ticketNumbers.contains("ZOO-" + ticketNumber)) {
            while (ticketNumbers.contains("ZOO-" + ticketNumber)) {
                ticketNumber = randomizeTicketNumber();
            }
        }

        System.out.println("Ticket purchased!");
        System.out.printf("Your ticket code is: ZOO-%d", ticketNumber);

        Ticket ticket = new Ticket(name, String.valueOf(ticketNumber));
        ticketsList.add(ticket);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.newLine();
            writer.write(name + ",ZOO-" + ticketNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n\n=== Ticket has been registered to system===");
        System.out.println("Would you like to purchase another ticket? (Yes/No)");
        System.out.print(">> ");
        String repeatChoice = sc.next();

        if (repeatChoice.equalsIgnoreCase("yes")) initiatePurchase(true);
        else System.out.println("Thank you for using this service. See you soon!");
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
        return rand.nextInt(1000) + 1000;
    }
}
