package org.example.models;

public class Tiger extends Feline {
    public Tiger(String name, boolean isSick) {
        super(name, isSick);
    }

    public Tiger(String name, String type, boolean isSick) {
        super(name, "Tiger", isSick);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s growls, although it isn't as loud as a lion's.\n", this.name);
    }
}
