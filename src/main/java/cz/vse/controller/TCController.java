package cz.vse.controller;

import cz.vse.dto.*;
import cz.vse.entity.*;
import cz.vse.logic.TCMusterLogic;
import cz.vse.service.*;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
@RequestMapping("/tc")
public class TCController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @Autowired
    TSMusterService tsMusterService;

    @Autowired
    TCMusterService tcMusterService;

    @Autowired
    TCInstanceService tcInstanceService;

    @Autowired
    TSInstanceService tsInstanceService;

    @Autowired
    TCService tcService;

    @Autowired
    SuiteService suiteService;

    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    WorkTCService workTCService;

    @Autowired
    TCMusterLogic tcMusterLogic;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTC(Model model, @RequestParam(required = false, value = "project") Long projectId) {
        l.info("/tc/create");
        Long personId = securityUtils.getLoggedPersonId();
        TCMusterForm tcForm = new TCMusterForm();
        tcForm.setProject_id(projectId);

        model.addAttribute("tcForm", tcForm);
//        model.addAttribute("listTCMusters", tcMusterService.findAllTestCaseMustersDTO());
//        model.addAttribute("listTSMusters", tsMusterService.findAllTestStepMustersDTO());
//        model.addAttribute("listProjects", projectService.findAllTestProjects());
        model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(personId));
        model.addAttribute("projectsSuites", suiteService.findAllTestSuiteListsByProjectId(projectId));    // xxx
        return "tcCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTSPost(@ModelAttribute("project") TCMusterForm tcMusterForm, HttpServletRequest request) {
        l.info("/tc/create saving");
        if (tcMusterForm.getId() == null) {
            tcMusterForm.setAuthor_id(securityUtils.getLoggedPersonId());
            tcMusterService.createTestCaseMuster(tcMusterForm);
        } else {
            tcMusterService.updateTestCaseMuster(tcMusterForm);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/edit/{id}")
    public String editTCMuster(@PathVariable("id") long id, Model model) {
        l.info("/tc/edit/{id}" + id);
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("tcForm", tcMusterService.findTestCaseMusterFormById(id));
//        model.addAttribute("listPersons", personService.findAllPersons());
//        model.addAttribute("listProjects", projectService.findAllTestProjects());
        model.addAttribute("listUsersProjects", projectService.findAllTestProjectNamesByUserId(personId));
        return "tcCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeTCMuster(@PathVariable("id") long id) {
        l.info("/tc/remove/" + id);
        tcMusterService.deleteTestCaseMusterById(id);
        return "redirect:/tc/create";
    }

    @RequestMapping("/instance/remove/{id}")
    public String removeTInstance(@PathVariable("id") long id) {
        l.info("/tc/instance/remove/" + id);
        Long tcInstanceMusterId = tcInstanceService.findTestCaseInstanceById(id).gettCMuster().getId();
        tcInstanceService.deleteTestCaseInstanceById(id);
        return "redirect:/tc/history/" + tcInstanceMusterId;
    }

    @RequestMapping("/run/{id}")
    public String runTCMuster(Model model, @PathVariable("id") long id,
                              @RequestParam(required = false, value = "worktc") Long worktcId) {
        l.info("/tc/run/" + id);
        TCInstanceRunDTO tcInstanceRunDTO;
        TCInstance tcInstance;
        Person person = securityUtils.getLoggedPerson();
//        tcInstanceRunDTO = tcService.runNewTC(id, person);
        tcInstance = tcService.runNewTC(id, person);
        l.info("tcService.runNewTC(id, person): " + tcInstance);

        if (worktcId != null) {
            l.info("worktcId != null");
            workTCService.addWorkTCHistory(worktcId, tcInstance);
        }
        tcInstanceRunDTO = tcInstanceService.findTCInstanceRunDTOById(tcInstance.getId());
//        model.addAttribute("tcInstanceRunDTO", tcInstanceRunDTO);
//        model.addAttribute("tsInstances", tsInstanceService.
//                findAllTSInstanceListsByTCInstanceId(tcInstanceRunDTO.getTcInstance_id()));

        return "redirect:/tc/show/" + tcInstanceRunDTO.getTcInstance_id();
    }

    @RequestMapping("/show/{id}")
    public String showTCMuster(Model model, @PathVariable("id") long id) {
        l.info("/tc/show/"+id);
        TCInstanceRunDTO tcInstanceRunDTO;
        tcInstanceRunDTO = tcInstanceService.findTCInstanceRunDTOById(id);
        model.addAttribute("tcInstanceRunDTO", tcInstanceRunDTO);
        model.addAttribute("tsInstances", tsInstanceService.
                findAllTSInstanceListsByTCInstanceId(tcInstanceRunDTO.getTcInstance_id()));
        return "tcRun";
    }

    @RequestMapping("/history/{id}")
    public String showTCHistory(@PathVariable("id") long id, Model model) {
        l.info("/tc/history/{id}" + id);
        model.addAttribute("tc", tcMusterService.findTCMusterListById(id));
//        model.addAttribute("listTCInstances", tcInstanceService.findAllTCInstancesByTCMusterId(id));
        model.addAttribute("tcInstances", tcInstanceService.findAllTCInstanceListsByTCMusterId(id));
        return "tcHistory";
    }

    @RequestMapping(value = "/tc-by-suite/{id}", method = RequestMethod.GET)
    public String tcBySuite(@PathVariable("id") long id, Model model) {
        l.info("/tc/tc-by-suite/" + id);

        model.addAttribute("tcs", tcService.findAllTCMusterListsBySuiteId(id));
        model.addAttribute("suite", suiteService.findTestSuiteListById(id));
        return "tcs";
    }

    @RequestMapping(value = "/tcs", method = RequestMethod.GET)
    public String tcsAllShow(Model model, @RequestParam(required = false, defaultValue = "all", value = "filter") String filter) {
        l.info("/tc/tcs?filter=" + filter);
        Long loggedUserId = securityUtils.getLoggedPersonId();
        List<Project> projects = projectService.findAllTestProjectByUserId(loggedUserId);
        List<TCMusterList> tcMusters;
        if (filter.equals("all")) {
            l.warn("filtr je all - " + filter);
            tcMusters = tcMusterService.findTCMusterListsByProject(projects);
        } else {
            l.warn("filtr není all, je to: " + filter);
            tcMusters = tcMusterService.findTCMusterListByProject(projectService.findTestProjectById(Long.parseLong(filter)));
        }
        model.addAttribute("tcMusters", tcMusters);
        model.addAttribute("tcMustersCopy", new TCMusterCopyDTO());
        model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(loggedUserId));
//        model.addAttribute("statusenum", Arrays.asList(StatusEnum.values()));
        return "tcsAll";
    }

    @RequestMapping(value = "/copy")
    public String manualTest(@ModelAttribute("project") TCMusterCopyDTO tcMusterCopyDTO, Model model, HttpServletRequest request) {
        l.warn("manualTest: " + model);
        l.warn("manualTest2: " + tcMusterCopyDTO);

        tcMusterLogic.copyTCMuster(tcMusterCopyDTO);
        l.warn("kopírování hotovo");
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

}