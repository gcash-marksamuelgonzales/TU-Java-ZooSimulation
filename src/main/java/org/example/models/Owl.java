package org.example.models;

public class Owl extends Bird {
    public Owl(String name, boolean isSick) {
        super(name, isSick);
    }

    public Owl(String name, String type, boolean isSick) {
        super(name, "Owl", isSick);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s hoots. Is it almost nighttime?\n", this.name);
    }
}
