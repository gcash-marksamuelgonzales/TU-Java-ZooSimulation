package org.example.models;

public class Falcon extends Bird {
    public Falcon(String name, boolean isSick) {
        super(name, isSick);
    }

    public Falcon(String name, String type, boolean isSick) {
        super(name, "Falcon", isSick);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s shrills as it calls. It sounds like its saying 'Kak, kak, kak'!.\n", this.name);
    }
}
