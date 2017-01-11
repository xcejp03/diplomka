package cz.vse.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
//@RequestMapping("/")
public class RootController {
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


    @RequestMapping(value = "/thyme", method = RequestMethod.GET)
    public String thymeInclude(Model model) {
        l.info("request mapping project/create");
//        model.addAttribute("project", new ProjectDTO());
//        model.addAttribute("person", new PersonDTO());
//        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("vlozit");

        return "thyme";
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        l.info("request mapping root/index");
//        model.addAttribute("project", new ProjectDTO());
//        model.addAttribute("person", new PersonDTO());
//        model.addAttribute("listPersons", personService.findAllPersons());

        return "index";
    }

    @RequestMapping(value = "/xxx")
    public String YourActionName(@RequestParam("memberId") String id, @RequestParam("memberPw") String pw ){
        l.error("Jóóóooooo");
        return "ExpectedReturnView";
    }

//    @RequestMapping(value = "/bs", method = RequestMethod.GET)
//    public String testujBootstrap() {
//        return "bootstrap";
//    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String bootstrapIndex() {
//        return "index";
//    }


}



