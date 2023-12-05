package markup;

import java.util.List;

public class Emphasis extends Mark {
    private final List<Mark> list;

    public Emphasis(List<Mark> list) {
        this.list = list;
    }

    public String open() {
        return "[i]";
    }

    public String markdown() {
        return "*";
    }

    public String close() {
        return "[/i]";
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
