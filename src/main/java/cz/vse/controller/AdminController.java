package cz.vse.controller;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.RoleEnum;
import cz.vse.entity.TCMuster;
import cz.vse.service.*;
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
@RequestMapping("/admin")
public class AdminController {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    PersonService personService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SuiteService suiteService;

    @Autowired
    private TCMusterService tcMusterService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        l.info("person/role");
        model.addAttribute("usersDTOList", personService.findAllPersonsDTO());
        return "usersAdmin";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String showAllProjects(Model model) {
        l.info("person/role");
        model.addAttribute("projectsDTOList", projectService.findAllTestProjectsDTO());
        return "projectsAdmin";
    }

    @RequestMapping(value = "/suites", method = RequestMethod.GET)
    public String showAllSuites(Model model) {
        l.info("person/role");
        model.addAttribute("suitesDTOList", suiteService.findAllTestSuitesDTO());
        return "suitesAdmin";
    }

    @RequestMapping(value = "/tcs", method = RequestMethod.GET)
    public String showAllTCs(Model model) {
        l.info("person/role");
        model.addAttribute("tcsDTOList", tcMusterService.findAllTestCaseMustersDTO());
        return "tcsAdmin";
    }




}

