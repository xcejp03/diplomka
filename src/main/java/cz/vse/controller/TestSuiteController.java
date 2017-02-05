package cz.vse.controller;

import cz.vse.dto.TestSuiteForm;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
import cz.vse.service.TCMusterService;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    SecurityUtils securityUtils;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public String defectShowByUser(Model model, @RequestParam(required = false, defaultValue = "open", value = "filter") String filter) {
    public String createTestSuite(Model model, @RequestParam(required = false, value = "project") Long projectId) {
        l.info("/suite/create");
        Long personId = securityUtils.getLoggedPersonId();

        TestSuiteForm suiteForm = new TestSuiteForm();
        if (projectId != null) {
            suiteForm.setProject_id(projectId);
        }

        model.addAttribute("suiteForm", suiteForm);
//        model.addAttribute("listSuites", suiteService.findAllTestSuites());
//        model.addAttribute("listSuitesDTO", suiteService.findAllTestSuitesDTO());
//        model.addAttribute("listProjects", projectService.findAllTestProjects());
//        model.addAttribute("listProjectsDTO", projectService.findAllTestProjectsDTO());
//        model.addAttribute("listPersons", personService.findAllPersons());
//        model.addAttribute("listTcMusters", tcMusterService.findAllTestCaseMusters());

        model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(personId));

        return "suiteCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("suiteDTO") TestSuiteForm suiteForm) {
        l.info("/suite/create");
        if (suiteForm.getId() == null) {
            suiteService.createTestSuite(suiteForm);
        } else {
            suiteService.updateTestSuite(suiteForm);
        }
        return "redirect:/suite/suites-by-project/" + suiteForm.getProject_id();
    }

    @RequestMapping("/edit/{id}")
    public String editTestSuite(@PathVariable("id") int id, Model model) {
        l.info("/suite/edit/" + id);
        Long personId = securityUtils.getLoggedPersonId();
        TestSuiteForm suiteForm = suiteService.findTestSuiteFormById(id);

        model.addAttribute("suiteForm", suiteForm);
//        model.addAttribute("suiteE", suiteService.findTestSuiteById(id));
//        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
//        model.addAttribute("listPersons", personService.findAllPersons());
//        model.addAttribute("listTcMusters", tcMusterService.findAllTestCaseMusters());
//        model.addAttribute("listTcMustersDTO", tcMusterService.findAllTestCaseMustersDTO());
        model.addAttribute("tcMustersByProject", tcMusterService.findTCMusterNamesByProjectId(suiteForm.getProject_id()));
        model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(personId));
        model.addAttribute("tcMusterNames", tcMusterService.findAllTestCaseMusterNamesByTestSuiteId(suiteService.findTestSuiteDTOById(id).getId()));
//        model.addAttribute("tcmusterdto", tcMusterService.findTestCaseMusterDTOById(suiteService.findTestSuiteDTOById(id).getId()));
//        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
//        tcMusterNames, tcmuster
        return "suiteCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeSuite(@PathVariable("id") int id, HttpServletRequest request) {
        l.info("/suite/remove/" + id);
        suiteService.deleteTestSuiteById(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/suites-by-project/{id}")
    public String suitesByProject(@PathVariable("id") long id, Model model) {
        l.info("/suites/suites-by-project/{id} - " + id);

        model.addAttribute("suites", suiteService.findAllTestSuiteListsByProjectId(id));
        model.addAttribute("project", projectService.findTestProjectNameById(id));
        return "suites";
    }

    @RequestMapping(value = "/suites", method = RequestMethod.GET)
    public String suitesAllShow(Model model) {
        l.info("/suite/suites}");
        model.addAttribute("suites", suiteService.findAllTestSuitesDTOByUser(securityUtils.getLoggedPerson()));
//        model.addAttribute("project", projectService.findTestProjectById(id));
        return "suites";
    }


}

