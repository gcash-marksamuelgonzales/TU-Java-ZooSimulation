package org.example.models;

public class Parrot extends Bird {
    public Parrot(String name, boolean isSick) {
        super(name, isSick);
    }

    public Parrot(String name, String type, boolean isSick) {
        super(name, "Parrot", isSick);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s says hello in a bird-like voice. Didn't know they could talk!\n", this.name);
    }
}
