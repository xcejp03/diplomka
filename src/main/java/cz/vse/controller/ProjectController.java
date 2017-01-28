package cz.vse.controller;

import cz.vse.dto.PersonDTO;
import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.entity.Person;
import cz.vse.entity.RoleEnum;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @Autowired
    SuiteService suiteService;

    @Autowired
    SecurityUtils securityUtils;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProjectForm(Model model) {
        l.info("request mapping project/create");
        model.addAttribute("projectDTO", new ProjectDTO());
        model.addAttribute("person", new PersonDTO());
        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listSuites", suiteService.findAllTestSuites());

        return "projectCreate";
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

    @RequestMapping("/edit/{id}")
    public String editProject(@PathVariable("id") int id, Model model) {
        l.info("/edit/{id}" + id);
        model.addAttribute("projectDTO", projectService.findTestProjectDTOById(id));
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listMembersDTO", personService.findAllPersonDTOByProjectId(id));
        model.addAttribute("listMembers", personService.findAllPersonByProjectIdOrderById(id));
        model.addAttribute("listSuites", suiteService.findAllTestSuites());
        return "projectCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id) {
        projectService.deleteTestProjectById(id);
        return "redirect:/project/create";
    }

    @RequestMapping("{id}")
    public String projects(@PathVariable("id") int id, Model model) {
        l.info("project/{id}" + id);
        ProjectDTO projectDTO = projectService.findTestProjectDTOById(id);
        model.addAttribute("projectDTO", projectDTO);
        model.addAttribute("membersAll", personService.getProjectMembers(id));
        model.addAttribute("membersTesters", personService.getProjectMembers(id, RoleEnum.TESTER));
        model.addAttribute("membersAnalytics", personService.getProjectMembers(id, RoleEnum.ANALYTIC));
        return "project";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String projectsByLoggedUser(Model model) {
        Long personId = securityUtils.getLoggedPersonId();
        List<ProjectsNamesDTO> listProjectsNameDTO = projectService.findAllTestProjectsByUserIdDTO(personId);
        model.addAttribute("listProjects", listProjectsNameDTO);
        model.addAttribute("person", personService.findPersonById(personId));

        return "projects";
    }


}