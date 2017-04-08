package cz.vse.utils;

import cz.vse.dto.PersonName;
import cz.vse.dto.WorkListDTO;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.repository.PersonRepository;
import cz.vse.repository.ProjectRepository;
import cz.vse.repository.TestSuiteRepository;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.RoleService;
import cz.vse.service.WorkListService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class StartupHousekeeper {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private HelpService helpService;

    @Autowired
    private TestSuiteRepository testSuiteRepository;

    @Autowired
    private WorkListService workListService;

    @Autowired
    private PersonService personService;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        // do whatever you need here
        System.out.println("OOOOOOOO po staru");
//        createPersonAndProject();
        l.info("QQOOOOOOOOOQQQQQ - po novu");
        l.info("-.-.-.- 6.2. 11:45");
//        clearProjectMembers();
//        l.fatal("Výpis projektu: " + projectRepository.findOne(10L));
//        helpService.userRoleTesty();
//        userRoleTesty();
//        helpService.najdiPosledniTCInstanci(40);
        playSoundAfterStart();
        l.info(".");
        l.info(".");
        l.info(".");
        l.info(".");
        l.info(".");

        l.info(".");
        l.info(".");
        l.info(".");
        l.info(".");
        l.info(".");
        l.info(".");


//        kontrolniVypisDB();

    }


    private void kontrolniVypisDB() {
        l.warn("delej");
//        Project project = projectService.findTestProjectById(21L);
//        List<Project> projects = new ArrayList<>();
//        projects.add(project);
//
//        l.warn("getNumberOfSuitesInProject: " + testSuiteRepository.getNumberOfSuitesInProject(project));

        l.warn("osoba: " + personRepository.findOne(16L));
        l.warn("projekt: " + projectService.findAllTestProjects());
        l.warn("projekt: " + projectService.findAllTestProjectsDTO());

    }


    public void playSoundAfterStart() {
        l.info("prehravam zvuk");
        final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        if (runnable != null) runnable.run();
    }

    private void userRoleTesty() {

//        Person person = personRepository.findById(100);
//        l.info(person);
//        person.getUserRole().clear();
//        personRepository.save(person);

    }

    private void clearProjectMembers() {
        Project project10 = projectRepository.findOne(10L);
        Project project11 = projectRepository.findOne(11L);
        Project project12 = projectRepository.findOne(12L);

//        Person person0 = project.getPersonMembers().get(0);
//        Person person1 = project.getPersonMembers().get(1);

        Person person = personRepository.findOne(21L);
        person.getProjectsMember().remove(project10);
        person.getProjectsMember().remove(project11);

//        project.getPersonMembers().clear();
//        person0.getProjectsMember().remove(project);
//        person1.getProjectsMember().clear();

        personRepository.save(person);
//        personRepository.save(person0);
//        personRepository.save(person1);
//        projectRepository.save(project);
    }


    private void createPersonAndProject() {
        Person person1 = new Person("Petr", "heslo", true);
        Person person2 = new Person("Květoslav", "heslo", true);
        Person person3 = new Person("Randy", "heslo", true);
        Person person4 = new Person("Ctirad", "heslo", true);

//        Project project1 = new Project("NASA");
//        Project project2 = new Project("Inbox");
//        Project project3 = new Project("GTB");
//        Project project4 = new Project("Vyhrnout sníh");

        List<Person> memberList = new ArrayList<>();
        List<Person> memberList2 = new ArrayList<>();
        memberList.add(person1);
        memberList.add(person2);
        memberList.add(person3);

//        project1.setPersonMembers(memberList);
//        project2.setPersonMembers(memberList);

        for (Person person : memberList) {
            List<Project> projectList = new ArrayList<>();
//            projectList.add(project1);
//            projectList.add(project2);
            person.setProjectsMember(projectList);
            l.info("vypisuju projectList - " + projectList);
        }
//        projectRepository.save(project1);
//        projectRepository.save(project2);
//        projectRepository.save(project3);
//        projectRepository.save(project4);

        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);
        personRepository.save(person4);

        System.out.println("Tak to prošlo RRRRR");
    }
}