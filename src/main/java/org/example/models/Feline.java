package org.example.models;

public class Feline extends Animal{
    public String type;

    public Feline(String name, String type, boolean isSick) {
        this.name = name;
        this.type = type;
        this.isSick = isSick;
    }

    public Feline(String name, boolean isSick) {
        this.name = name;
        this.isSick = isSick;
    }

    @Override
    public void roam() {
        System.out.printf("%s is currently prancing around the area.\n", this.name);
    }

    public void makeSound() {}
}
