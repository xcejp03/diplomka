package cz.vse.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pcejka on 21.09.2016.
 */
@Entity (name = "testsuite")
public class TestSuite extends BaseEntity {
    private String name;        //testsuit je možné si pojmenovat

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;        //testsuit patří pod jeden projekt

    @ManyToMany(mappedBy = "testSuites")
    private List<TCMuster> tcMusters;      //testsuit se skládá z testcasů;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<TCMuster> getTcMusters() {
        return tcMusters;
    }

    public void setTcMusters(List<TCMuster> tcMusters) {
        this.tcMusters = tcMusters;
    }

    @Override
    public String toString() {
        return "TestSuite{" +
                "name='" + name + '\'' +
                ", project=" + project +
                '}';
    }

}
