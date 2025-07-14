package org.example.models;

public class Elephant extends Pachyderm{

    public Elephant(String name, String type, boolean isHealthy) {
        super(name, "Elephant", isHealthy);
    }

    public Elephant(String name, boolean isHealthy) {
        super(name, isHealthy);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s lifts its trunk high and trumpets!\n", this.name);
    }
}
