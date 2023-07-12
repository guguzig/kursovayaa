package test;

import domain.IdGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdGeneratorTest {

    @Test
    public void testNewId() {
        long expectedId = IdGenerator.getNextId() + 1;
        long actualId = IdGenerator.getNextId();
        assertEquals(expectedId, actualId);
    }
}