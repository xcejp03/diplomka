package cz.vse.controller;

import cz.vse.DBPopulator;
import cz.vse.dao.PersonDao;
import cz.vse.dto.DefectDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.service.DefectService;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 08.10.2016.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    PersonDao personDao;

    @Autowired
    PersonService personService;

    @Autowired
    DBPopulator dbPopulator;

    @Autowired
    DefectService defectService;

    @Autowired
    ProjectService projectService;

//    @RequestMapping(value = "person", method = RequestMethod.GET)
//    public String person (ModelMap model)   {
//        l.info("person");
//        Person person = new Person();
//        person.setName(LocalDateTime.now().toString());
//        person.setCreatedDate(LocalDateTime.now());
//        person.setLogin("login osoby");
//        personService.createPerson(person);
//        return "test";
//    }

    @RequestMapping(value = "person", method = RequestMethod.GET)
    public String person_testproject(ModelMap model) {
        l.info("person_testproject");

//        Person person = new Person();
//        person.setName(LocalDateTime.now().toString());
//        person.setCreatedDate(LocalDateTime.now());
//        person.setLogin("login osoby");
//        personService.createPerson(person);
//
//        Person person2 = new Person();
//        person2.setName(LocalDateTime.now().toString());
//        person2.setCreatedDate(LocalDateTime.now());
//        person2.setLogin("login osoby");
//        personService.createPerson(person2);
//
//        Person person3 = new Person();
//        person3.setName(LocalDateTime.now().toString());
//        person3.setCreatedDate(LocalDateTime.now());
//        person3.setLogin("login osoby");
//        personService.createPerson(person3);

        Person p1 = personService.findPersonById(25);
        Person p2 = personService.findPersonById(26);
        Person p3 = personService.findPersonById(27);
        Person p4 = personService.findPersonById(3);
        Person p5 = personService.findPersonById(4);

        Project project = projectService.findTestProjectById(33);

        List<Person> personList = new ArrayList<>();
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);

        project.setProjectOwner(p1);
        project.setPersonMembers(personList);

        List<Project> projects = new ArrayList<>();
        projects.add(project);
        p3.setProjectsMember(projects);

//        projectService.updateTestProject(project);

        return "test";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(ModelMap model) {
        l.error("home");
        Person person = new Person();
        person.setName("XXX");
        l.error("im going to save person " + person);
        personDao.savePerson(person);
        l.error("After saving person");
        dbPopulator.createAllDatasWithConstraints();
        l.error("dbPopulator.createAllDatasWithConstraints(); hotovo");

        return "home";
    }

    @RequestMapping(value = "defect", method = RequestMethod.GET)
    public String defectTest(ModelMap model) {
        l.error("defect");
//        Defect defect = new Defect();
//        defect.setDefectStatusEnum(DefectStatusEnum.open);
//        defect.setPriorityEnum(PriorityEnum.blocker);
//        defect.setAffectsVersion("betaverze");
//        defect.setDescription("Popis chyby pro testování");
//        defectService.createDefect(defect);
//        defectService.findAllDefects();
//        defect.setDescription("Updatovaný defect přes service");
//        defectService.updateDefect(defect);
//        defectService.findAllDefects();
//        defectService.deleteDefect(defect);
        model.addAttribute("defect", new DefectDTO());
        model.addAttribute("defectList", defectService.findAllDefects());
        l.debug("nic");
        return "defect";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home2(ModelMap model) {
        l.error("home2");
        Person person = new Person();
        person.setName("XXX");
        personDao.savePerson(person);

        model.addAttribute("largestQuadruplet");
        return "home";
    }

    @RequestMapping(value = "defect", method = RequestMethod.POST)
    public String createDefect(@ModelAttribute("testProject") DefectDTO defectDTO) {
        l.error("/defect");

        defectService.createDefect(defectDTO);
        return "redirect:defect";
    }

}


