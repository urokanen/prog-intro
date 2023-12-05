package markup;

public class Text extends Mark {
    private final String s;

    public Text(String s) {
        this.s = s;
    }

    public String open() {
        return "";
    }

    public String markdown() {
        return "";
    }

    public String close() {
        return "";
    }

    public void pass(StringBuilder value) {
        value.append(s);
    }

    public void passMarkdown(StringBuilder value) {
        value.append(s);
    }
}
