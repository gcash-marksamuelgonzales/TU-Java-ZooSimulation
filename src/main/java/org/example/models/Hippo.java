package org.example.models;

public class Hippo extends Pachyderm {

    public Hippo(String name, String type, boolean isSick) {
        super(name, "Hippo", isSick);
    }

    public Hippo(String name, boolean isSick) {
        super(name, isSick);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s grunts. It almost sounds like a loud honking noise!\n", this.name);
    }
}
