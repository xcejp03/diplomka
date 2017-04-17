package cz.vse.dto;

/**
 * Created by pcejka on 10.10.2016.
 */
public class PersonName extends BaseDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "PersonName{" +
                "name='" + name + '\'' +
                '}';
    }
}

