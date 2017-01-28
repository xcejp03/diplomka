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
@Entity
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TestSuite testSuite = (TestSuite) o;

        if (name != null ? !name.equals(testSuite.name) : testSuite.name != null) return false;
        if (project != null ? !project.equals(testSuite.project) : testSuite.project != null) return false;
        return tcMusters != null ? tcMusters.equals(testSuite.tcMusters) : testSuite.tcMusters == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (tcMusters != null ? tcMusters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestSuite{" +
                "name='" + name + '\'' +
                ", project=" + project +
                '}';
    }

}
