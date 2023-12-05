package markup;

import java.util.List;

public class ListItem {
    private final List<Main> list;

    public ListItem(List<Main> list) {
        this.list = list;
    }

    public void toBBCode(StringBuilder value) {
        value.append("[*]");
        for (Main element : list) {
            element.toBBCode(value);
        }
    }
}
