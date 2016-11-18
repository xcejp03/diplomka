package cz.vse.controller;

import cz.vse.dto.PersonDTO;
import cz.vse.dto.ProjectDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
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
@RequestMapping("/person")
public class PersonController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProjectForm(Model model) {
        l.info("request mapping person/create");
        model.addAttribute("person", new PersonDTO());
        model.addAttribute("listPersons", personService.findAllPersons());

        return "person";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("person") PersonDTO personDTO) {
        if(personDTO.getId() == null){
        //new person, add it
        personService.createPerson(personDTO);
        }else {
            //existing person, call update
            this.personService.updatePerson(personDTO);

//        testProjectService.createTestProject(null);
        }
        return "redirect:create";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String testik2(Model model) {
        l.info("testik");
        Project project = new Project();
        project.setName("XxX");
        projectService.createTestProject(project);
        return "project";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        l.info("/edit/{id}" + id);
        model.addAttribute("person", personService.findPersonById(id));
//        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return "redirect:/person/create";
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

