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

        return "suiteCreate";
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
        model.addAttribute("suiteDTO", suiteService.findTestSuiteDTOById(id));
        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listTcMusters", tcMusterService.findAllTestCaseMusters());
        return "suiteCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeSuite(@PathVariable("id") int id) {
        suiteService.deleteTestSuiteById(id);
        return "redirect:/suite/create";
    }

    @RequestMapping("/suites-by-project/{id}")
    public String suitesByProject(@PathVariable("id") long id, Model model) {
        l.info("/suites-by-project/{id} - "+id);

        model.addAttribute("listSuitesDTO", suiteService.findAllTestSuitesDTOByProjectId(id));
        model.addAttribute("project", projectService.findTestProjectById(id));
        return "suitesCreate";
    }


}

