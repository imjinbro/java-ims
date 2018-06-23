package codesquad.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Issues {

    @OneToMany(mappedBy = "milestone", cascade = CascadeType.PERSIST)
    private List<Issue> issues = new ArrayList<>();

    public Issues add(Issue issue) {
        issues.add(issue);
        return this;
    }
}
