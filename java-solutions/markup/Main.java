package markup;

public abstract class Main {
    public void toBBCode(StringBuilder value) {
        value.append(open());
        pass(value);
        value.append(close());
    }

    abstract public void pass(StringBuilder value);

    abstract public String open();

    abstract public String close();
}
