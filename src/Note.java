import java.util.List;

class Note {
    private int id;
    private String text;
    private List<String> labels;

    public Note(int id, String text, List<String> labels) {
        this.id = id;
        this.text = text;
        this.labels = labels;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<String> getLabels() {
        return labels;
    }

    public boolean hasLabels(List<String> labels) {
        for (String label : labels) {
            if (!this.labels.contains(label.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Текст: " + text + ", Метки: " + labels;
    }
}