package org.example.models;

public class Rhino extends Pachyderm {

    public Rhino(String name, String type, boolean isSick) {
        super(name, "Rhino", isSick);
    }

    public Rhino(String name, boolean isSick) {
        super(name, isSick);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s snorts and growls. It looks intimidating with its horn up!\n", this.name);
    }
}
