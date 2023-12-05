package markup;

public abstract class Mark {
    public void toMarkdown(StringBuilder value) {
        value.append(markdown());
        passMarkdown(value);
        value.append(markdown());
    }

    public void toBBCode(StringBuilder value) {
        value.append(open());
        pass(value);
        value.append(close());
    }

    abstract public void pass(StringBuilder value);

    abstract public void passMarkdown(StringBuilder value);

    abstract public String open();

    abstract public String close();

    abstract public String markdown();
}
