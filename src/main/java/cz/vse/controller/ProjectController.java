package cz.vse.controller;

import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectForm;
import cz.vse.dto.ProjectName;
import cz.vse.entity.RoleEnum;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

//import org.springframework.web.bind.annotation.PostMapping;

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
    public String createProjectForm(Model model, ProjectForm projectForm) {
        l.info("project/create get");
        model.addAttribute("persons", personService.findAllPersonNames());
        return "projectCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(Model model, @Valid ProjectForm projectForm, BindingResult bindingResult, HttpServletRequest request) {
        l.info("/project/create post");
        if (bindingResult.hasErrors()) {
            l.error("form has errors");
            model.addAttribute("persons", personService.findAllPersonNames());
            return "projectCreate";
        }

        if (projectForm.getId() == null) {
            projectService.createTestProject(projectForm);
        } else {
            projectService.updateTestProject(projectForm);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/edit/{id}")
    public String editProject(@PathVariable("id") long id, Model model) {
        l.info("/project/edit/{id}" + id);
        model.addAttribute("projectForm", projectService.findTestProjectFormById(id));
        model.addAttribute("persons", personService.findAllPersonNames());
        model.addAttribute("members", personService.findAllPersonNamesByProjectIdOrderById(id));
//        model.addAttribute("listSuites", suiteService.findAllTestSuites());
        return "projectCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeProject(@PathVariable("id") long id) {
        l.info("/project/remove/" + id);
        projectService.deleteTestProjectById(id);
        return "redirect:/project/create";
    }

    @RequestMapping("{id}")
    public String projectDetail(@PathVariable("id") long id, Model model) {
        l.info("/project/{id}" + id);
        ProjectDTO projectDTO = projectService.findTestProjectDTOById(id);
        model.addAttribute("projectDTO", projectDTO);
        model.addAttribute("projectsStat", projectService.getProjectWithStatistics(id));
        model.addAttribute("membersAll", personService.getProjectMembers(id));
        model.addAttribute("membersTesters", personService.getProjectMembers(id, RoleEnum.TESTER));
        model.addAttribute("membersAnalytics", personService.getProjectMembers(id, RoleEnum.ANALYTIC));
        model.addAttribute("suites", suiteService.findAllTestSuitesDTOByProjectId(id));
        return "project";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String projectsByLoggedUser(Model model) {
        Long personId = securityUtils.getLoggedPersonId();
        List<ProjectName> projectNames = projectService.findAllTestProjectNamesByUserId(personId);
        model.addAttribute("projects", projectNames);
        model.addAttribute("person", personService.findPersonById(personId));

        return "projects";
    }


}