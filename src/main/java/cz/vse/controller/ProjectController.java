package cz.vse.controller;

import cz.vse.dto.TestProjectDTO;
import cz.vse.entity.Person;
import cz.vse.entity.TestProject;
import cz.vse.service.PersonService;
import cz.vse.service.TestProjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    TestProjectService testProjectService;

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProjectForm(Model model) {
        l.info("request mapping project/create");
        model.addAttribute("testProject", new TestProjectDTO());
        model.addAttribute("listProjects", testProjectService.findAllTestProjects());
        model.addAttribute("listPersons", personService.findAllPersons());

        return "testProject";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("testProject") TestProjectDTO testProjectDTO) {
//        if(person.getId() == 0){
        //new person, add it
        testProjectService.createTestProject(testProjectDTO);
//        }else{
        //existing person, call update
//            this.personService.updatePerson(p);

//        testProjectService.createTestProject(null);
        return "testProject";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testik (Model model)  {
        l.info("testik");
        List<Person> personList = new ArrayList<>();
        personList.add(personService.findPersonById(11));
        personList.add(personService.findPersonById(12));
// osoby jsou 10 a 11
        TestProjectDTO testProjectDTO = new TestProjectDTO();
        testProjectDTO.setName("XxX");
//        testProjectDTO.setProjectMembers(personList);
//        testProjectDTO.setProjectOwner(personService.findPersonById(10));
        testProjectService.createTestProject(testProjectDTO);
        return "testProject";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String testik2 (Model model)  {
        l.info("testik");
        TestProject testProject = new TestProject();
        testProject.setName("XxX");
        testProjectService.createTestProject(testProject);
        return "testProject";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        l.info("/edit/{id}" + id);
        model.addAttribute("person", personService.findPersonById(id));
//        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
        personService.deletePerson(id);
        return "person";
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

