package cz.vse.dto;


import java.io.Serializable;

/**
 * Created by pcejka on 10.10.2016.
 */
public abstract class BaseDTO implements Serializable {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseDTO baseDTO = (BaseDTO) o;

        return id != null ? id.equals(baseDTO.id) : baseDTO.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BaseDTO{" +
                "id=" + id +
                '}';
    }
}
