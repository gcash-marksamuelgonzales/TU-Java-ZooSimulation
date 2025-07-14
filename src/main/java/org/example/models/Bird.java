package org.example.models;

public class Bird extends Animal {
    public String type;

    public Bird(String name, String type, boolean isSick) {
        this.name = name;
        this.type = type;
        this.isSick = isSick;
    }

    public Bird(String name, boolean isSick) {
        this.name = name;
        this.isSick = isSick;
    }

    @Override
    public void roam() {
        System.out.printf("%s is currently flying around the area.", this.name);
    }

    public void makeSound() {};
}
