package cz.vse.controller;

import cz.vse.dto.ProjectDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
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
        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listSuites", suiteService.findAllTestSuites());

        return "projectCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("project") ProjectDTO projectDTO) {
        if (projectDTO.getId() == null) {
            //new person, add it
            projectService.createTestProject(projectDTO);
        } else {
            //existing person, call update

            this.projectService.updateTestProject(projectDTO);
        }
        return "redirect:create";
    }

    @RequestMapping("/edit/{id}")
    public String editProject(@PathVariable("id") int id, Model model) {
        l.info("/edit/{id}" + id);
//            model.addAttribute("person", personService.findPersonById(id));
        model.addAttribute("project", projectService.findTestProjectDTOById(id));
//            model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
        return "project";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id) {
        projectService.deleteTestProjectById(id);
        return "redirect:/project/create";
    }

//    @RequestMapping(value = "/create", method = RequestMethod.GET)
////    public String createProjectForm(ModelMap model) {
//    public String createProjectForm(Model model) {
//        l.info("request mapping project/create");
//        model.addAttribute("person", new Person());
//        model.addAttribute("listPersons", personService.findAllPersons());
//
//        return "person";
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String addPerson(@ModelAttribute("person") Person person) {
////        if(person.getId() == 0){
//        //new person, add it
//        personService.createPerson(person);
////        }else{
//        //existing person, call update
////            this.personService.updatePerson(p);
//
////        testProjectService.createTestProject(null);
//        return "person";
//    }
//
//    @RequestMapping("/edit/{id}")
//    public String editPerson(@PathVariable("id") int id, Model model) {
//        l.info("/edit/{id}" + id);
//        model.addAttribute("person", personService.findPersonById(id));
////        model.addAttribute("listPersons", this.personService.listPersons());
//        return "person";
//    }
//
//    @RequestMapping("/remove/{id}")
//    public String removePerson(@PathVariable("id") int id){
//        personService.deletePerson(id);
//        return "person";
//    }
}

