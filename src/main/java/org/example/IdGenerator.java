package org.example;

public class IdGenerator {
    private int nextId;

    public IdGenerator() {
        nextId = 1;
    }

    public int getNextId() {
        int id = nextId;
        nextId++;
        return id;
    }
}