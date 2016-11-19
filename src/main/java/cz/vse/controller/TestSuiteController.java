package cz.vse.controller;

import cz.vse.dto.TestSuiteDTO;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
import cz.vse.service.TCMusterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
@RequestMapping("/suite")
public class TestSuiteController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @Autowired
    TCMusterService tcMusterService;

    @Autowired
    SuiteService suiteService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTestSuite(Model model) {
        l.info("request mapping suite/create");
        model.addAttribute("suiteDTO", new TestSuiteDTO());
        model.addAttribute("listSuites", suiteService.findAllTestSuites());
        model.addAttribute("listSuitesDTO", suiteService.findAllTestSuitesDTO());
        model.addAttribute("listProjects", projectService.findAllTestProjects());
        model.addAttribute("listProjectsDTO", projectService.findAllTestProjectsDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listTcMusters", tcMusterService.findAllTestCaseMusters());

        return "suite";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("suiteDTO") TestSuiteDTO testSuiteDTO) {
        if (testSuiteDTO.getId() == null) {
            suiteService.createTestSuite(testSuiteDTO);
        } else {
            suiteService.updateTestSuite(testSuiteDTO);
        }
        return "redirect:create";
    }

    @RequestMapping("/edit/{id}")
    public String editTestSuite(@PathVariable("id") int id, Model model) {
        l.info("/edit/{id}" + id);
//            model.addAttribute("person", personService.findPersonById(id));
        model.addAttribute("suiteDTO", suiteService.findTestSuiteDTOById(id));
            model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
        return "suite";
    }

    @RequestMapping("/remove/{id}")
    public String removeSuite(@PathVariable("id") int id) {
        suiteService.deleteTestSuiteById(id);
        return "redirect:/suite/create";
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

