package test;

import domain.ValidationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationUtilsTest {

    @Test
    public void testValidateNoteText_ValidText() {
        String text = "Valid note text";
        Assertions.assertDoesNotThrow(() -> ValidationUtils.validateNoteText(text));
    }

    @Test
    public void testValidateNoteText_InvalidText() {
        String text = "A";
        Assertions.assertThrows(IllegalArgumentException.class, () -> ValidationUtils.validateNoteText(text));
    }

    @Test
    public void testValidateLabels_ValidLabels() {
        String labelsInput = "Метка";
        Assertions.assertDoesNotThrow(() -> ValidationUtils.validateLabels(labelsInput));
    }

    @Test
    public void testValidateLabels_InvalidLabels() {
        String labelsInput = "Label1 123 Label3";
        Assertions.assertThrows(IllegalArgumentException.class, () -> ValidationUtils.validateLabels(labelsInput));
    }
}