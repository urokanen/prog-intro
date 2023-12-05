package markup;

import java.util.List;

public class Strikeout extends Mark {
    private final List<Mark> list;

    public Strikeout(List<Mark> list) {
        this.list = list;
    }

    public String open() {
        return "[s]";
    }

    public String markdown() {
        return "~";
    }

    public String close() {
        return "[/s]";
    }

    public void pass(StringBuilder value) {
        for (Mark element : list) {
            element.toBBCode(value);
        }
    }

    public void passMarkdown(StringBuilder value) {
        for (Mark element : list) {
            element.toMarkdown(value);
        }
    }
}
