package markup;

import java.util.List;

public class UnorderedList extends Main {
    private final List<ListItem> list;

    public UnorderedList(List<ListItem> list) {
        this.list = list;
    }

    public void pass(StringBuilder value) {
        for (ListItem element : list) {
            element.toBBCode(value);
        }
    }

    public String open() {
        return "[list]";
    }

    public String close() {
        return "[/list]";
    }
}
