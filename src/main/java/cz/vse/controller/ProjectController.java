package cz.vse.controller;

import cz.vse.dto.PersonDTO;
import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.entity.Person;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProjectForm(Model model) {
        l.info("request mapping project/create");
        model.addAttribute("project", new ProjectDTO());
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
        l.info("{id}/list" + id);
        List<ProjectsNamesDTO> listProjectsNameDTO = projectService.findAllTestProjectsByUserIdDTO(id);
        model.addAttribute("listProjects", listProjectsNameDTO);
        model.addAttribute("person", personService.findPersonById(id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        l.error(auth);
        String name = auth.getName();
        l.error("XXX: " + name);
        return "project";
    }

    @RequestMapping("{id}/old")
    public String ProjectList(@PathVariable("id") int id, Model model) {
        l.info("{id}/list" + id);
        List<ProjectsNamesDTO> listProjectsNameDTO = projectService.findAllTestProjectsByUserIdDTO(id);
        model.addAttribute("listProjects", listProjectsNameDTO);
        model.addAttribute("person", personService.findPersonById(id));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        l.error(auth);
        String name = auth.getName();
        return "ProjectList";
    }

    @RequestMapping()
    public String projectsByLoggedUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        l.info("User Authentication: " + auth);
        Person person = personService.findPersonByAuthentication(auth);
        Long personId = person.getId();
        l.info("Person is " + person.getId() + " - " + person.getName());

        List<ProjectsNamesDTO> listProjectsNameDTO = projectService.findAllTestProjectsByUserIdDTO(personId);
        model.addAttribute("listProjects", listProjectsNameDTO);
        model.addAttribute("person", personService.findPersonById(personId));

        return "projects";
    }
}