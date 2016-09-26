package cz.vse.entity;

import javax.persistence.Entity;
import java.security.acl.Owner;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity
public class TestProject  extends BaseEntity{
    private String name;
    private List<TestSuite> testSuites;
    private Person ProjectOwner;
}
