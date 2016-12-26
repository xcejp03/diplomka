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

//    @RequestMapping(value = "/bs", method = RequestMethod.GET)
//    public String testujBootstrap() {
//        return "bootstrap";
//    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String bootstrapIndex() {
//        return "index";
//    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String bootstrapAbout() {
        return "about";
    }

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public String bootstrapServices() {
        return "services";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String bootstrapContact() {
        return "contact";
    }

    @RequestMapping(value = "/portfolio-1-col", method = RequestMethod.GET)
    public String bootstrapCol1() {
        return "portfolio-1-col";
    }

    @RequestMapping(value = "/portfolio-2-col", method = RequestMethod.GET)
    public String bootstrapCol2() {
        return "portfolio-2-col";
    }

    @RequestMapping(value = "/portfolio-3-col", method = RequestMethod.GET)
    public String bootstrapCol3() {
        return "portfolio-3-col";
    }

    @RequestMapping(value = "/portfolio-4-col", method = RequestMethod.GET)
    public String bootstrapCol4() {
        return "portfolio-4-col";
    }

    @RequestMapping(value = "/portfolio-item", method = RequestMethod.GET)
    public String bootstrapCol0() {
        return "portfolio-item";
    }

    @RequestMapping(value = "/blog-home-1", method = RequestMethod.GET)
    public String bootstrapBlogHome1() {
        return "blog-home-1";
    }

    @RequestMapping(value = "/blog-home-2", method = RequestMethod.GET)
    public String bootstrapBlogHome2() {
        return "blog-home-2";
    }

    @RequestMapping(value = "/blog-post", method = RequestMethod.GET)
    public String bootstrapBlogPost() {
        return "blog-post";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String bootstrap404() {
        return "404";
    }

    @RequestMapping(value = "/full-width", method = RequestMethod.GET)
    public String bootstrapFullWidth() {
        return "full-width";
    }

    @RequestMapping(value = "/sidebar", method = RequestMethod.GET)
    public String bootstrapSideBar() {
        return "sidebar";
    }

    @RequestMapping(value = "/faq", method = RequestMethod.GET)
    public String bootstrapFaq() {
        return "faq";
    }

    @RequestMapping(value = "/pricing", method = RequestMethod.GET)
    public String bootstrapPricing() {
        return "pricing";
    }
}



