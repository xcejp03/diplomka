package cz.vse.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.security.acl.Owner;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TestProject  extends BaseEntity{
    private String name;

    @OneToMany (mappedBy = "id")
    private List<TestSuite> testSuites;

    @ManyToOne
    @JoinColumn (name = "projectOwner_id")
    private Person projectOwner;
}
