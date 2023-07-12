package domain;

public class IdGenerator {
    private static int nextId;

    public IdGenerator() {
        nextId = 1;
    }

    public static int getNextId() {
        int id = nextId;
        nextId++;
        return id;
    }
}