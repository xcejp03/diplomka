package cz.vse.dto.old;

import cz.vse.dto.*;
import cz.vse.entity.PriorityTCEnum;
import cz.vse.entity.StatusEnum;

import java.util.List;

/**
 * Created by pcejka on 10.10.2016.
 */

public class TCMusterCopyDTO extends cz.vse.dto.BaseDTO {

    private Long project_id;
    private List<Long> tcMusters_id;


    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public List<Long> getTcMusters_id() {
        return tcMusters_id;
    }

    public void setTcMusters_id(List<Long> tcMusters_id) {
        this.tcMusters_id = tcMusters_id;
    }

    @Override
    public String toString() {
        return "TCMusterCopyDTO{" +
                "project_id=" + project_id +
                ", tcMusters_id=" + tcMusters_id +
                '}';
    }
}