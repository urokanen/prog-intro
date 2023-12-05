package markup;

import java.util.List;

public class OrderedList extends Main {
    private final List<ListItem> list;

    public OrderedList(List<ListItem> list) {
        this.list = list;
    }

    public void pass(StringBuilder value) {
        for (ListItem element : list) {
            element.toBBCode(value);
        }
    }

    public String open() {
        return "[list=1]";
    }

    public String close() {
        return "[/list]";
    }
}
