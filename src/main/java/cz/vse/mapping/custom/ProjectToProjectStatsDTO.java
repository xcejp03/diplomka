package cz.vse.mapping.custom;

import cz.vse.dto.ProjectStatsDTO;
import cz.vse.dto.WorkListDTO;
import cz.vse.entity.*;
import cz.vse.service.TCInstanceService;
import cz.vse.service.TCMusterService;
import cz.vse.service.WorkTCService;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 26.11.2016.
 */
@Component
public class ProjectToProjectStatsDTO extends CustomMapper<Project, ProjectStatsDTO> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    private WorkTCService workTCService;

    @Autowired
    private TCInstanceService tcInstanceService;

    @Autowired
    private TCMusterService tcMusterService;

//    @Override
//    public void mapAtoB(WorkList workList, WorkListDTO workListDTO, MappingContext context) {
//        l.info("A -> B");
//        super.mapAtoB(workList, workListDTO, context);
//        workListDTO.setTcMuster_id(getTCMusterIdFromWorkTCDTO(workList));
//    }


    @Override
    public void mapAtoB(Project project, ProjectStatsDTO projectStatsDTO, MappingContext context) {
        l.warn("ProjectToProjectStatsDTO A -> B");
        projectStatsDTO.setName(project.getName());
        projectStatsDTO.setNumberOfFailedTCs(tcInstanceService.getNumberOfTCsInProjectByStatus(project, StatusEnum.FAILED));
        projectStatsDTO.setNumberOfPassedTCs(tcInstanceService.getNumberOfTCsInProjectByStatus(project, StatusEnum.PASSED));
        projectStatsDTO.setNumberOfTCs(tcMusterService.getNumberOfTCsInProject(project));
        projectStatsDTO.setProjectOwner_name(project.getProjectOwner().getName());

//        super.mapAtoB(project, projectStatsDTO, context);
    }

    @Override
    public void mapBtoA(ProjectStatsDTO projectStatsDTO, Project project, MappingContext context) {
        l.warn("ProjectToProjectStatsDTO B -> A");
        super.mapBtoA(projectStatsDTO, project, context);
    }

}
