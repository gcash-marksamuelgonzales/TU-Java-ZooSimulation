package org.example.models;

public class Elephant extends Pachyderm{

    public Elephant(String name, String type, boolean isSick) {
        super(name, "Elephant", isSick);
    }

    public Elephant(String name, boolean isSick) {
        super(name, isSick);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s lifts its trunk high and trumpets!\n", this.name);
    }
}
