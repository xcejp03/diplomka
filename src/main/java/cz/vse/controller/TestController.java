package cz.vse.controller;

import cz.vse.dto.PersonDTO;
import cz.vse.dto.ProjectDTO;
import cz.vse.entity.Project;
import cz.vse.entity.TCInstance;
import cz.vse.entity.TSInstance;
import cz.vse.repository.TCInstanceRepository;
import cz.vse.repository.TSInstanceRepository;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @Autowired
    SuiteService suiteService;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private TSInstanceRepository tsInstanceRepository;

    @Autowired
    private TCInstanceRepository tcInstanceRepository;

    @RequestMapping("/repo/{id}")
    public String showTCMuster(Model model, @PathVariable("id") long id) {
        TCInstance tcInstance = tcInstanceRepository.findOne(id);
        l.info(tcInstance);

        List<TSInstance> tsInstanceList = tsInstanceRepository.findAllTestStepInstancesByTCInstance(tcInstance);
        l.info(tsInstanceList);
        return "tcRun";
    }

    @RequestMapping(value = "/ent", method = RequestMethod.GET)
    public String createProjectForm(Model model) {
        l.info("request mapping project/create");
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listSuites", suiteService.findAllTestSuites());

        projectService.createTestProject(createProjectData());

        return "projectCreate";
    }

    @RequestMapping(value = "/thym", method = RequestMethod.GET)
    public String thyme(Model model) {
        l.info("request mapping project/create");
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("person", new PersonDTO());
//        model.addAttribute("project", new ProjectDTO());
//        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
//        model.addAttribute("listSuites", suiteService.findAllTestSuites());
//
//        projectService.createTestProject(createProjectData());

        return "thyme";
    }

    @RequestMapping(value = "/thym2", method = RequestMethod.GET)
    public String thym2(Model model) {
        l.info("request mapping project/create");
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("person", new PersonDTO());
//        model.addAttribute("project", new ProjectDTO());
//        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
//        model.addAttribute("listSuites", suiteService.findAllTestSuites());
//
//        projectService.createTestProject(createProjectData());

        return "thyme2";
    }


    @RequestMapping(value = "/dto", method = RequestMethod.GET)
    public String createProjectDTO(Model model) {
        l.info("request mapping project/create");
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listSuites", suiteService.findAllTestSuites());

        projectService.createTestProject(createProjectDataDTO());

        return "projectCreate";
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String mapovani(Model model) {
        l.info("request mapping project/create");
        l.info("pred");
        mapper.map(createProjectDataDTO(), Project.class);
        l.info("ddd");

        return "projectCreate";
    }

    @RequestMapping(value = "/map2", method = RequestMethod.GET)
    public String mapovani2(Model model) {
        l.info("request mapping project/create");
        l.info("pred");
        Project project = new Project();
        mapper.map(createProjectDataDTO(), project);
        l.info("ddd");

        return "projectCreate";
    }

    private ProjectDTO createProjectDataDTO() {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("dto");

        List<Long> suitesList = new ArrayList<>();
        long suiteLong = 751;
        suitesList.add(suiteLong);
        projectDTO.setSuites_id(suitesList);

        return projectDTO;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("project") ProjectDTO projectDTO) {
        if (projectDTO.getId() == null) {
            projectService.createTestProject(projectDTO);
        } else {
            projectService.updateTestProject(projectDTO);
        }
        return "redirect:create";
    }

    private Project createProjectData() {

        Project project = new Project();

        project.setName("xxx");

        return project;

    }
    @RequestMapping(value = "/bs", method = RequestMethod.GET)
    public String testujBootstrap() {
        return "bootstrap";
    }



}


