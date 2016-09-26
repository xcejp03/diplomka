package cz.vse.entity;

import javax.persistence.*;
import java.security.acl.Owner;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TestProject  extends BaseEntity{
    private String name;

    @ManyToOne
    @JoinColumn (name = "projectOwner_id")
    private Person projectOwner;

    @ManyToMany (mappedBy = "testProjectsMember")
    private List<Person> projectMembers;

    @OneToMany (mappedBy = "testProject")
    private List<TestSuite> testSuites;

    @OneToMany (mappedBy = "testProject")
    private List<TestCaseMuster> testCaseMusters;


}
