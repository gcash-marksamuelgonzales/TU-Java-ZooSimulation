package org.example.models;

public abstract class Animal {
    public String name;
    public boolean isSick;
    public String location;
    public String type;

    public void eat() {
        System.out.printf("%s is currently eating.\n", this.name);
    }

    public void sleep() {
        System.out.printf("%s is currently sleeping.\n", this.name);
    }

    public abstract void roam();

    public abstract void makeSound();

    public String getName() {
        return name;
    }

    public boolean isSick() {
        return isSick;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSick(boolean sick) {
        isSick = sick;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }
}
