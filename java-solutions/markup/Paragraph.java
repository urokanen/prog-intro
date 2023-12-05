package markup;

import java.util.List;

public class Paragraph extends Main {
    private final List<Mark> list;

    public Paragraph(List<Mark> list) {
        this.list = list;
    }

    public void toMarkdown(StringBuilder value) {
        for (Mark element : list) {
            element.toMarkdown(value);
        }
    }

    public void pass(StringBuilder value) {
        for (Mark element : list) {
            element.toBBCode(value);
        }
    }

    public String open() {
        return "";
    }

    public String close() {
        return "";
    }
}
