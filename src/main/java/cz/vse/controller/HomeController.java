package cz.vse.controller;

import cz.vse.DBPopulator;
import cz.vse.dao.PersonDao;
import cz.vse.entity.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pcejka on 08.10.2016.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    PersonDao personDao;

    @Autowired
    DBPopulator dbPopulator;

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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home2(ModelMap model) {
        l.error("/home");
        Person person = new Person();
        person.setName("XXX");
        personDao.savePerson(person);

        model.addAttribute("largestQuadruplet");
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home3(ModelMap model) {
        l.error("/");
        Person person = new Person();
        person.setName("XXX");
        personDao.savePerson(person);

        model.addAttribute("largestQuadruplet");
        return "home";
    }

}


