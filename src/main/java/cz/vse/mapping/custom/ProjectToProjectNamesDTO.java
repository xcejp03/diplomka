package cz.vse.mapping.custom;

import cz.vse.dto.ProjectStatsDTO;
import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.entity.Project;
import cz.vse.entity.StatusEnum;
import cz.vse.service.SuiteService;
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
public class ProjectToProjectNamesDTO extends CustomMapper<Project, ProjectsNamesDTO> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    private WorkTCService workTCService;

    @Autowired
    private TCInstanceService tcInstanceService;

    @Autowired
    private TCMusterService tcMusterService;

    @Autowired
    private SuiteService suiteService;
    @Override
    public void mapAtoB(Project project, ProjectsNamesDTO projectsNamesDTO, MappingContext context) {
        l.warn("ProjectToProjectNamesDTO A->B");
        super.mapAtoB(project, projectsNamesDTO, context);
        projectsNamesDTO.setNumberOfTestSuites(suiteService.getNumberOfSuitesInProject(project));
    }

}
