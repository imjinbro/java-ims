package codesquad.domain;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

public enum Label {
    BUG(1),
    HELP(2),
    QUESTION(3);

    private long id;

    Label(long id) {
        this.id = id;
    }

    public static Label find(long id) {
        return Arrays.stream(Label.values()).filter(label -> label.id == id).findFirst().orElseThrow(EntityNotFoundException::new);
    }

    public static List<Label> findAll() {
        return Arrays.asList(Label.values());
    }

    public String getLabel() {
        return name();
    }
}
