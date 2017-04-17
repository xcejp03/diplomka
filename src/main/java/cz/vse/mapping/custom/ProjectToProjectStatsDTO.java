package cz.vse.mapping.custom;

import cz.vse.dto.ProjectStatsDTO;
import cz.vse.entity.Project;
import cz.vse.entity.StatusEnum;
import cz.vse.service.TCInstanceService;
import cz.vse.service.TCMusterService;
import cz.vse.service.WorkTCService;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    @Override
    public void mapAtoB(Project project, ProjectStatsDTO projectStatsDTO, MappingContext context) {
        l.info("ProjectToProjectStatsDTO A -> B");
        projectStatsDTO.setName(project.getName());
        projectStatsDTO.setNumberOfFailedTCs(tcInstanceService.getNumberOfTCsInProjectByStatus(project, StatusEnum.FAILED));
        projectStatsDTO.setNumberOfPassedTCs(tcInstanceService.getNumberOfTCsInProjectByStatus(project, StatusEnum.PASSED));
        projectStatsDTO.setNumberOfNorunTCs(tcInstanceService.getNumberOfTCsInProjectByStatus(project, StatusEnum.NORUN));
        projectStatsDTO.setNumberOfTCs(tcMusterService.getNumberOfTCsInProject(project));
        projectStatsDTO.setProjectOwner_name(project.getProjectOwner().getName());
        l.warn("id projektu UUU: " + project.getId());
        projectStatsDTO.setProject_id(project.getId());

    }

    @Override
    public void mapBtoA(ProjectStatsDTO projectStatsDTO, Project project, MappingContext context) {
        l.info("ProjectToProjectStatsDTO B -> A");
        super.mapBtoA(projectStatsDTO, project, context);
    }

}
