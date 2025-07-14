package org.example.service;

import org.example.models.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ZooVisitorModuleService {
    private static Scanner sc = new Scanner(System.in);
    private static int choice;

    private static ArrayList<Pachyderm> pachyderms = new ArrayList<>();
    private static ArrayList<Feline> felines = new ArrayList<>();
    private static ArrayList<Bird> birds = new ArrayList<>();
    private static ArrayList<String> shoppingCart = new ArrayList<>();
    private static ArrayList<Animal> sickAnimals = new ArrayList<>();
    private static ArrayList<String> healedAnimals = new ArrayList<>();

    public static Pachyderm elephant;
    public static Pachyderm rhino;
    public static Pachyderm hippo;

    public static Feline tiger;
    public static Feline lion;
    public static Feline cheetah;

    public static Bird parrot;
    public static Bird owl;
    public static Bird falcon;

    private static String veterinarianName;
    private static int hospitalChoice;

    static String pachydermFileName = "src/main/java/org/example/resources/pachyderm.csv";
    static String felineFileName = "src/main/java/org/example/resources/feline.csv";
    static String birdFileName = "src/main/java/org/example/resources/bird.csv";


//    public static void main(String[] args) throws IOException {
    public void run() throws IOException {

        executeArraySetup();
        String line = Files.readAllLines(Paths.get("src/main/java/org/example/resources/staff.csv")).get(1);
        String[] parsedLine = line.split(",");
        veterinarianName = parsedLine[1];

        mainMenu();
        visitLocation();

        overwritePachydermFile(pachydermFileName);
        overwriteFelineFile(felineFileName);
        overwriteBirdFile(birdFileName);

    }

    private static void visitLocation() throws IOException {
        switch (choice) {
            case 1 -> visitEnclosure();
            case 2 -> visitShop();
            case 3 -> visitHospital();
            case 4 -> {
            }
            default -> {
                System.out.println("Invalid input. Please try again.");
                mainMenu();
            }
        }
    }

    private static void executeArraySetup() {
        pachyderms.clear();
        felines.clear();
        birds.clear();

        extractPachydermFromFile(pachydermFileName);
        extractFelineFromFile(felineFileName);
        extractBirdFromFile(birdFileName);

    }

    private static void overwritePachydermFile(String fileName) {

        String isSick;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))){
            for (Pachyderm animal : pachyderms ) {
                if (animal.isSick) {
                    isSick = "1";
                } else {
                    isSick = "0";
                }
                writer.write(animal.getName() + "," + animal.type + ",Pachyderm," + isSick + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void overwriteFelineFile(String fileName) {

        String isSick;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))){
            for (Feline animal : felines ) {
                if (animal.isSick) {
                    isSick = "1";
                } else {
                    isSick = "0";
                }
                writer.write(animal.getName() + "," + animal.type + ",Feline," + isSick + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void overwriteBirdFile(String fileName) {

        String isSick;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))){
            for (Bird animal : birds) {
                if (animal.isSick) {
                    isSick = "1";
                } else {
                    isSick = "0";
                }
                writer.write(animal.getName() + "," + animal.type + ",Bird," + isSick + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void extractPachydermFromFile(String fileName) {
        boolean isSick;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                isSick = record[3].equalsIgnoreCase("1");
                Pachyderm pachyderm = new Pachyderm(record[0], record[1], isSick);
                pachyderms.add(pachyderm);

                if (isSick) {
                    sickAnimals.add(pachyderm);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
        }

        elephant = new Elephant(pachyderms.get(0).name, "Elephant", pachyderms.get(0).isSick);
        rhino = new Rhino(pachyderms.get(1).name, "Rhino", pachyderms.get(1).isSick);
        hippo = new Hippo(pachyderms.get(2).name, "Hippo", pachyderms.get(2).isSick);
    }

    private static void extractFelineFromFile(String fileName) {
        boolean isSick;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                isSick = record[3].equalsIgnoreCase("1");
                Feline feline = new Feline(record[0], record[1], isSick);
                felines.add(feline);

                if (isSick) sickAnimals.add(feline);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
        }

        tiger = new Tiger(felines.get(0).name, "Tiger", felines.get(0).isSick);
        lion = new Lion(felines.get(1).name, "Lion", felines.get(1).isSick);
        cheetah = new Cheetah(felines.get(2).name, "Cheetah", felines.get(2).isSick);
    }

    private static void extractBirdFromFile(String fileName) {
        boolean isSick;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                isSick = record[3].equalsIgnoreCase("1");
                Bird bird = new Bird(record[0], record[1], isSick);
                birds.add(bird);

                if (isSick) sickAnimals.add(bird);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
        }

        parrot = new Parrot(birds.get(0).name, "Parrot", birds.get(0).isSick);
        owl = new Owl(birds.get(1).name, "Owl", birds.get(1).isSick);
        falcon = new Falcon(birds.get(2).name, "Falcon", birds.get(2).isSick);
    }

    private static void mainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("[1] Visit Enclosure");
        System.out.println("[2] Visit Shop");
        System.out.println("[3] Visit Hospital");
        System.out.println("[4] Leave Zoo");
        System.out.print(">> ");
        try {
            choice = sc.nextInt();
            visitLocation();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid input. Please try again.");
            mainMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void visitEnclosure() {
        System.out.println(" === ZOO ENCLOSURE ===");
        System.out.println("Choose Enclosure:");
        System.out.println("[1] Pachyderm");
        System.out.println("[2] Feline");
        System.out.println("[3] Bird");
        System.out.print(">> ");

        int enclosureChoice = 0;
        try {
            enclosureChoice = sc.nextInt();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid input. Please try again.");
            mainMenu();
        }

        switch (enclosureChoice) {
            case 1 -> {
                System.out.println(" === PACHYDERM ENCLOSURE ===");
                System.out.println("Choose Animal:");
                System.out.println("[1] Elephant");
                System.out.println("[2] Rhino");
                System.out.println("[3] Hippo");
                System.out.print(">> ");
                choosePachyderm();
            }
            case 2 -> {
                System.out.println(" === FELINE ENCLOSURE ===");
                System.out.println("Choose Animal:");
                System.out.println("[1] Lion");
                System.out.println("[2] Tiger");
                System.out.println("[3] Cheetah");
                System.out.print(">> ");
                chooseFeline();
            }
            case 3 -> {
                System.out.println(" === BIRD ENCLOSURE ===");
                System.out.println("Choose Animal:");
                System.out.println("[1] Parrot");
                System.out.println("[2] Owl");
                System.out.println("[3] Falcon");
                System.out.print(">> ");
                chooseBird();
            }
            default -> {
                System.out.println("Invalid input for enclosure. Returning to main menu.");
                mainMenu();
            }
        }

        System.out.println("\nWhere do you want to go next?\n");
        mainMenu();
    }

    private static void feedAnimal(Animal animal) {
        System.out.println();
        animal.roam();
        System.out.printf("\nWould you like to feed %s? (Yes/No) ", animal.getName());
        String feedAnimal = sc.next();

        if (feedAnimal.equalsIgnoreCase("yes")) {
            animal.eat();
            animal.makeSound();
            System.out.println();
        }
    }

    private static void chooseBird() {
        int pachydermChoice = sc.nextInt();
        switch (pachydermChoice) {
            case 1 -> {
                feedAnimal(parrot);
            }
            case 2 -> {
                feedAnimal(owl);
            }
            case 3 -> {
                feedAnimal(falcon);
            }
        }
    }

    private static void chooseFeline() {
        int felineChoice = sc.nextInt();
        switch (felineChoice) {
            case 1 -> {
                feedAnimal(lion);
            }
            case 2 -> {
                feedAnimal(tiger);
            }
            case 3 -> {
                feedAnimal(cheetah);
            }
        }
    }

    private static void choosePachyderm() {
        int pachydermChoice = sc.nextInt();
        switch (pachydermChoice) {
            case 1 -> {
                feedAnimal(elephant);
            }
            case 2 -> {
                feedAnimal(rhino);
            }
            case 3 -> {
                feedAnimal(hippo);
            }
        }
    }

    private static void visitShop() {
        int totalPrice = 0;

        System.out.println("====== ZOO SHOP ======");
        System.out.println("Available Products:");
        System.out.println("[1] Soft Drink = P30");
        System.out.println("[2] Popcorn    = P50");
        System.out.println("[3] Plush Toy  = P150");
        System.out.println("[4] Keychain   = P100");
        System.out.println("=======================");

        try {
            addItems(totalPrice);
            System.out.println();
            System.out.println("Returning to the main menu...");
            mainMenu();

        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid input. Please try again.");
            mainMenu();
        }

    }

    private static int addItems(int totalPrice) {
        System.out.println("Enter the number corresponding to the item you want to buy:");
        System.out.print(">> ");
        int shopChoice = sc.nextInt();
        switch (shopChoice) {
            case 1 -> {
                shoppingCart.add("Soft Drink - P30");
                totalPrice +=30;
            }
            case 2 -> {
                shoppingCart.add("Popcorn - P50");
                totalPrice +=50;
            }
            case 3 -> {
                shoppingCart.add("Plush Toy - P150");
                totalPrice +=150;
            }
            case 4 -> {
                shoppingCart.add("Keychain - P100");
                totalPrice +=100;
            }
            default -> {
                System.out.println("Invalid input. Please try again.");
                addItems(totalPrice);
            }
        }

        showCart(totalPrice);

        System.out.print("Would you like to add more items? (Yes/No): ");
        String addMore = sc.next();

        if (addMore.equalsIgnoreCase("yes")) addItems(totalPrice);
        else if (addMore.equalsIgnoreCase("no")) {
            System.out.print("Proceed to checkout? (Yes/No - Add more items): ");
            String checkoutChoice = sc.next();

            if (checkoutChoice.equalsIgnoreCase("yes")) checkout(totalPrice);
            else addItems(totalPrice);
        }

        return totalPrice;
    }

    private static void showCart(int total) {
        System.out.println("Shopping Cart:");
        for (String item : shoppingCart) {
            System.out.println(item);
        }
        System.out.printf("\nTotal: %d\n\n", total);
    }

    private static void checkout(int total) {
        System.out.println("Payment successful!");
        System.out.println("\n=== Receipt ===");
        for (String item : shoppingCart) {
            System.out.printf("- %s\n", item);
        }
        System.out.printf("Total paid: %d\n", total);
    }

    private static void visitHospital() throws IOException {

        hospitalMenu();

        try {
            hospitalChoice = sc.nextInt();

            switch (hospitalChoice) {
                case 1 -> {
                    viewSickAnimals();
                    visitHospital();
                }
                case 2 -> {
                    viewHealedAnimals();
                    visitHospital();
                }
                case 3 -> attendLecture();
                case 4 -> {
                    watchVet();
                    visitHospital();
                }
                case 5 -> {
                    System.out.println("Thanks for visiting the hospital. See you again!");
                    System.out.println();
                    mainMenu();
                }
                default -> System.out.println("Invalid input.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid input. Please try again.");
            mainMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void watchVet() {

        if (sickAnimals.isEmpty()) System.out.println("No animals in hospital. Vet can relax! :)\n");
        else {
            System.out.println();
            System.out.printf("Dr. %s begins healing sick animals...\n", veterinarianName);
            for (Animal sickAnimal : sickAnimals) {
                System.out.printf("+++ Healed: %s\n", sickAnimal.getName());
                System.out.printf("%s has been healed and discharged. %s has been returned to the enclosure.\n\n", sickAnimal.getName(), sickAnimal.getName());

                healedAnimals.add(sickAnimal.getName() + " | " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
                for (Pachyderm pachyderm : pachyderms) healSickAnimal(pachyderm);
                for (Feline feline : felines) healSickAnimal(feline);
                for (Bird bird : birds) healSickAnimal(bird);
            }
            sickAnimals.clear();
        }

    }

    private static void healSickAnimal(Animal animal) {
        if (animal.isSick) {
            animal.setSick(false);
        }
    }

    private static void attendLecture() throws IOException {

        System.out.printf("\nDr. %s gives a science lecture on animal health and conservation.\n", veterinarianName);
        System.out.println("You listen and take notes.");
        System.out.println("\nWhat would you like to do next?\n");
        visitHospital();
    }

    private static void viewHealedAnimals() {

        System.out.println("\n+ Healed Animals with Timestamps +");
        if (healedAnimals.isEmpty()) System.out.println("No animals have been healed yet. Please try again later!");

        for (String healedAnimal : healedAnimals) {
            System.out.printf(" - %s\n", healedAnimal);
        }
        System.out.println();
    }

    private static void viewSickAnimals() {
        System.out.println("\n--- Sick Animals Currently in Hospital ---");
        if (sickAnimals.isEmpty()) System.out.println("No sick animals right now, yay! :)");

        for (Animal sickAnimal : sickAnimals) {
            System.out.printf(" - %s\n", sickAnimal.getName());
        }
        System.out.println();
    }

    private static void hospitalMenu() {
        System.out.println("=== Zoo Visitor Hospital Monitor ===");
        System.out.println("[1] View Sick Animals");
        System.out.println("[2] View Healed Animals");
        System.out.println("[3] Attend Science Lecture");
        System.out.printf("[4] Watch Vet Heal Animals (On duty: Dr. %s)\n", veterinarianName);
        System.out.println("[5] Leave Zoo Hospital");
        System.out.println("=====================================");
        System.out.println("Choose an option:");
        System.out.print(">> ");
    }
}
