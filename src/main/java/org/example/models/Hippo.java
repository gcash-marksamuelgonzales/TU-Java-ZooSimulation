package org.example.models;

public class Hippo extends Pachyderm {

    public Hippo(String name, String type, boolean isHealthy) {
        super(name, "Hippo", isHealthy);
    }

    public Hippo(String name, boolean isHealthy) {
        super(name, isHealthy);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s grunts. It almost sounds like a loud honking noise!\n", this.name);
    }
}
