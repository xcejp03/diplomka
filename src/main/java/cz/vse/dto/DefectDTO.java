package cz.vse.dto;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */
public class DefectDTO extends BaseDTO {

    private String description;

    private String affectsVersion;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAffectsVersion() {
        return affectsVersion;
    }

    public void setAffectsVersion(String affectsVersion) {
        this.affectsVersion = affectsVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefectDTO)) return false;
        if (!super.equals(o)) return false;

        DefectDTO defectDTO = (DefectDTO) o;

        if (getDescription() != null ? !getDescription().equals(defectDTO.getDescription()) : defectDTO.getDescription() != null)
            return false;
        return getAffectsVersion() != null ? getAffectsVersion().equals(defectDTO.getAffectsVersion()) : defectDTO.getAffectsVersion() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getAffectsVersion() != null ? getAffectsVersion().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DefectDTO{" +
                "description='" + description + '\'' +
                ", affectsVersion='" + affectsVersion + '\'' +
                '}';
    }
}

