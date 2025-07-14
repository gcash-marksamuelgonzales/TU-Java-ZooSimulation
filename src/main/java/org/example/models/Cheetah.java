package org.example.models;

public class Cheetah extends Feline {
    public Cheetah(String name, boolean isHealthy) {
        super(name, isHealthy);
    }

    public Cheetah(String name, String type, boolean isSick) {
        super(name, "Cheetah", isSick);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s hisses and purrs.\n", this.name);
    }
}
