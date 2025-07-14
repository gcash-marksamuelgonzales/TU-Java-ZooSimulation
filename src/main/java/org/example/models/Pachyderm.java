package org.example.models;

public class Pachyderm extends Animal{
    public String type;

    public Pachyderm(String name, String type, boolean isSick) {
        this.name = name;
        this.type = type;
        this.isSick = isSick;
    }

    public Pachyderm(String name, boolean isSick) {
        this.name = name;
        this.isSick = isSick;
    }

    @Override
    public void roam() {
        System.out.printf("%s is currently walking around.", this.getName());
    }

    public void makeSound() {}
}
