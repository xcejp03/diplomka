package cz.vse.dto;

import java.util.List;

/**
 * Created by pcejka on 11.01.2017.
 */
public class WorkTCWrapper extends BaseDTO {
    private Long workList_id;
    private List<WorkTCDTO> workTCDTOList;

    public Long getWorkList_id() {
        return workList_id;
    }

    public void setWorkList_id(Long workList_id) {
        this.workList_id = workList_id;
    }

    public List<WorkTCDTO> getWorkTCDTOList() {
        return workTCDTOList;
    }

    public void setWorkTCDTOList(List<WorkTCDTO> workTCDTOList) {
        this.workTCDTOList = workTCDTOList;
    }

    @Override
    public String toString() {
        return "WorkTCWrapper{" +
                "workList_id=" + workList_id +
                ", workTCDTOList=" + workTCDTOList +
                '}';
    }
}
