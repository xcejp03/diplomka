package cz.vse.controller;

import cz.vse.dto.*;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import cz.vse.entity.TCInstance;
import cz.vse.logic.TCMusterLogic;
import cz.vse.service.*;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public String createTC(Model model, TCMusterForm tcMusterForm, HelpContext helpContext,
                           @RequestParam(required = false, defaultValue = "", value = "project") Long projectId,
                           @RequestParam(required = false, defaultValue = "", value = "suiteFrom") Long suiteFrom) {
        l.info("/tc/create");
        Long personId = securityUtils.getLoggedPersonId();
        helpContext.setProjectId(projectId);
        helpContext.setSuiteFrom(suiteFrom);

        if (tcMusterForm.getProject_id() == null) {
            tcMusterForm.setProject_id(projectId);
        }

        model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(personId));
        model.addAttribute("helpContext", helpContext);

        return "tcCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTSPost(Model model, @Valid TCMusterForm tcMusterForm, BindingResult bindingResult, HttpServletRequest request,
                               HelpContext helpContext) {
        l.info("/tc/create saving");
        model.addAttribute("helpContext", helpContext);
        if (bindingResult.hasErrors()) {
            l.error("form has errors");
            Long personId = securityUtils.getLoggedPersonId();
            model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(personId));

            return "tcCreate";
        }

        if (tcMusterForm.getId() == null) {
            tcMusterForm.setAuthor_id(securityUtils.getLoggedPersonId());
            tcMusterService.createTestCaseMuster(tcMusterForm);
        } else {
            tcMusterService.updateTestCaseMuster(tcMusterForm);
        }
        return "redirect:/tc/tc-by-suite/" + helpContext.getSuiteFrom();
    }

    @RequestMapping("/edit/{id}")
    public String editTCMuster(@PathVariable("id") long id, Model model, TCMusterForm tcMusterForm,
                               @RequestParam(required = false, defaultValue = "", value = "project") Long projectId,
                               @RequestParam(required = false, defaultValue = "", value = "suiteFrom") Long suiteFrom,
                               HelpContext helpContext) {
        l.info("/tc/edit/{id}" + id);
        Long personId = securityUtils.getLoggedPersonId();
        helpContext.setProjectId(projectId);
        helpContext.setSuiteFrom(suiteFrom);
        tcMusterForm = tcMusterService.findTestCaseMusterFormById(id);
        tcMusterForm.setSuiteFrom(suiteFrom);
        model.addAttribute("tcForm", tcMusterForm);
        model.addAttribute("TCMusterForm", tcMusterForm);

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
                              @RequestParam(required = false, value = "worktc") Long worktcId, @RequestParam(required = false, defaultValue = "null", value = "backid") String backId,
                              @RequestParam(required = false, defaultValue = "null", value = "origin") String instanceOrigin) {
        l.info("/tc/run/" + id);
        TCInstanceRunDTO tcInstanceRunDTO;
        TCInstance tcInstance;
        Person person = securityUtils.getLoggedPerson();
        tcInstance = tcService.runNewTC(id, person, backId, instanceOrigin);
        l.info("tcService.runNewTC(id, person): " + tcInstance);

        if (worktcId != null) {
            l.info("worktcId != null");
            workTCService.addWorkTCHistory(worktcId, tcInstance);
        }
        tcInstanceRunDTO = tcInstanceService.findTCInstanceRunDTOById(tcInstance.getId());

        return "redirect:/tc/show/" + tcInstanceRunDTO.getTcInstance_id();
    }

    @RequestMapping("/show/{id}")
    public String showTCMuster(Model model, @PathVariable("id") long id) {
        l.info("/tc/show/" + id);
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
    public String tcsAllShow(Model model, TCMusterCopyDTO TCMusterCopyDTO, @RequestParam(required = false, defaultValue = "all", value = "filter") String filter) {
        l.info("/tc/tcs?filter=" + filter);
        Long loggedUserId = securityUtils.getLoggedPersonId();
        List<Project> projects = projectService.findAllTestProjectByUserId(loggedUserId);
        List<TCMusterList> tcMusters;
        if (filter.equals("all")) {
            l.warn("filtr je all - " + filter);
            tcMusters = tcMusterService.findTCMusterListsByProject(projects);
            model.addAttribute("filterHelper", filter);
        } else {
            l.warn("filtr není all, je to: " + filter);
            tcMusters = tcMusterService.findTCMusterListByProject(projectService.findTestProjectById(Long.parseLong(filter)));
            model.addAttribute("filterHelper", filter);
        }
        model.addAttribute("tcMusters", tcMusters);
        model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(loggedUserId));
        return "tcsAll";
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String manualTest(Model model, @Valid TCMusterCopyDTO TCMusterCopyDTO, BindingResult bindingResult,
                             HttpServletRequest request, @RequestParam(required = false, value = "filter") String filter) {
        l.warn("manualTest: " + model);
        l.warn("filterHelper: " + filter);
        String referer = request.getHeader("Referer");


        if (bindingResult.hasErrors()) {
            l.error("form has errors");
            Long personId = securityUtils.getLoggedPersonId();
            Long loggedUserId = securityUtils.getLoggedPersonId();
            List<Project> projects = projectService.findAllTestProjectByUserId(loggedUserId);
            List<TCMusterList> tcMusters;
            if (filter.equals("all")) {
                l.warn("filtr je all - " + filter);
                tcMusters = tcMusterService.findTCMusterListsByProject(projects);
                model.addAttribute("filterHelper", filter);
            } else {
                l.warn("filtr není all, je to: " + filter);
                tcMusters = tcMusterService.findTCMusterListByProject(projectService.findTestProjectById(Long.parseLong(filter)));
                model.addAttribute("filterHelper", filter);
            }
            model.addAttribute("tcMusters", tcMusters);
            model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(loggedUserId));

            return "tcsAll";
        }

        tcMusterLogic.copyTCMuster(TCMusterCopyDTO);
        l.warn("kopírování hotovo");

        return "redirect:/tc/tcs?filter=" + filter;
    }

}