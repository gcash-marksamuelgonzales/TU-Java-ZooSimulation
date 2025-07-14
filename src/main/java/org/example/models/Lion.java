package org.example.models;

public class Lion extends Feline {
    public Lion(String name, boolean isSick) {
        super(name, isSick);
    }

    public Lion(String name, String type, boolean isSick) {
        super(name, "Lion", isSick);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s roars. It's so loud that it's almost deafening!\n", this.name);
    }
}
