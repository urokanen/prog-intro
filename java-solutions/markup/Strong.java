package markup;

import java.util.List;

public class Strong extends Mark {
    private final List<Mark> list;

    public Strong(List<Mark> list) {
        this.list = list;
    }

    public String open() {
        return "[b]";
    }

    public String markdown() {
        return "__";
    }

    public String close() {
        return "[/b]";
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
