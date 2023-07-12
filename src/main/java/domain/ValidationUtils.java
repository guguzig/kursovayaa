package domain;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtils {
    public static void validateNoteText(String text) {
        if (text.length() < 3) {
            throw new IllegalArgumentException("Текст заметки должен быть длиннее 3 символов.");
        }
    }

    public static List<String> validateLabels(String labelsInput) {
        List<String> labels = new ArrayList<>();
        if (!labelsInput.isEmpty()) {
            String[] labelArray = labelsInput.split(" ");
            for (String label : labelArray) {
                validateLabel(label);
                labels.add(label.toUpperCase());
            }
        }
        return labels;
    }

    private static void validateLabel(String label) {
        if (!label.matches("^[а-яА-Я]+$")) {
            throw new IllegalArgumentException("Метка может содержать только буквы.");
        }
    }
}
