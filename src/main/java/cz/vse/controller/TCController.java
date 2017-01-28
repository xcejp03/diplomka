package cz.vse.controller;

import cz.vse.dto.ProjectsNamesDTO;
import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.dto.TCMusterDTO;
import cz.vse.entity.*;
import cz.vse.service.*;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
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

    @RequestMapping(method = RequestMethod.GET)
    public String tcDefault(Model model) {
        l.info("request mapping /tc");
        model.addAttribute("tc", new TCMusterDTO());
        model.addAttribute("listTCMusters", tcMusterService.findAllTestCaseMustersDTO());
        model.addAttribute("listTSMusters", tsMusterService.findAllTestStepMustersDTO());
        model.addAttribute("listProjects", projectService.findAllTestProjects());
        return "tcDashboard";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTC(Model model, @RequestParam(required = false, value = "project") Long projectId) {
        l.info("request mapping tc/create");
        Long personId = securityUtils.getLoggedPersonId();
        TCMusterDTO tcDTO = new TCMusterDTO();
        tcDTO.setProject_id(projectId);

        model.addAttribute("tcDTO", tcDTO);
        model.addAttribute("listTCMusters", tcMusterService.findAllTestCaseMustersDTO());
        model.addAttribute("listTSMusters", tsMusterService.findAllTestStepMustersDTO());
        model.addAttribute("listProjects", projectService.findAllTestProjects());
        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
        return "tcCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTSPost(@ModelAttribute("project") TCMusterDTO tcMusterDTO, HttpServletRequest request) {
        if (tcMusterDTO.getId() == null) {
            tcMusterDTO.setAuthor_id(securityUtils.getLoggedPersonId());
            tcMusterService.createTestCaseMuster(tcMusterDTO);
        } else {
            tcMusterService.updateTestCaseMuster(tcMusterDTO);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/edit/{id}")
    public String editTCMuster(@PathVariable("id") long id, Model model) {
        l.info("/edit/{id}" + id);
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("tcDTO", tcMusterService.findTestCaseMusterDTOById(id));
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listProjects", projectService.findAllTestProjects());
        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
        return "tcCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeTCMuster(@PathVariable("id") long id) {
        tcMusterService.deleteTestCaseMusterById(id);
        return "redirect:/tc/create";
    }

    @RequestMapping("/instance/remove/{id}")
    public String removeTInstance(@PathVariable("id") long id) {
        Long tcInstanceMusterId = tcInstanceService.findTestCaseInstanceById(id).gettCMuster().getId();
        tcInstanceService.deleteTestCaseInstanceById(id);
        return "redirect:/tc/history/" + tcInstanceMusterId;
    }

    @RequestMapping("/run/{id}")
    public String runTCMuster(Model model, @PathVariable("id") long id,
                              @RequestParam(required = false, value = "worktc") Long worktcId) {
        TCInstanceRunDTO tcInstanceRunDTO;
        TCInstance tcInstance;
        Person person = securityUtils.getLoggedPerson();
//        tcInstanceRunDTO = tcService.runNewTC(id, person);
        tcInstance = tcService.runNewTC(id, person);
        l.warn("tcService.runNewTC(id, person): " + tcInstance);

        if (worktcId != null) {
            workTCService.addWorkTCHistory(worktcId, tcInstance);
        }

        tcInstanceRunDTO = tcInstanceService.findTCInstanceRunDTOById(tcInstance.getId());

        model.addAttribute("tcInstance", tcInstance);
        model.addAttribute("listTSInstances", tsInstanceService.findAllTSInstancesByTCInstanceId(tcInstanceRunDTO.getTcInstance_id()));

        return "redirect:/tc/show/" + tcInstanceRunDTO.getTcInstance_id();
    }

    @RequestMapping("/show/{id}")
    public String showTCMuster(Model model, @PathVariable("id") long id) {
        TCInstanceRunDTO tcInstanceRunDTO;
        tcInstanceRunDTO = tcInstanceService.findTCInstanceRunDTOById(id);
        model.addAttribute("tcInstance", tcInstanceRunDTO);
        model.addAttribute("listTSInstances", tsInstanceService.
                findAllTSInstancesByTCInstanceId(tcInstanceRunDTO.getTcInstance_id()));

        return "tcRun";
    }

    @RequestMapping("/history/{id}")
    public String showTCHistory(@PathVariable("id") long id, Model model) {
        l.info("/history/{id}" + id);
        model.addAttribute("tc", tcMusterService.findTestCaseMusterDTOById(id));
//        model.addAttribute("listTCInstances", tcInstanceService.findAllTCInstancesByTCMusterId(id));
        model.addAttribute("listTCInstances", tcInstanceService.findAllTCInstancesDTOByTCMusterId(id));
        return "tcHistory";
    }

    @RequestMapping(value = "/tc-by-suite/{id}", method = RequestMethod.GET)
    public String tcBySuite(@PathVariable("id") long id, Model model) {
        l.info("/tc-by-suite/{id} - " + id);

        model.addAttribute("listTCDTO", tcService.findAllTCMustersDTOBySuiteId(id));
        model.addAttribute("suite", suiteService.findTestSuiteById(id));
        model.addAttribute("statusenum", Arrays.asList(StatusEnum.values()));
        return "tcs";
    }

    @RequestMapping(value = "/tcs", method = RequestMethod.GET)
    public String tcsAllShow(Model model, @RequestParam(required = false, defaultValue = "all", value = "filter") String filter) {
        l.info("/tc/tcs?filter="+filter);
        Long loggedUserId = securityUtils.getLoggedPersonId();
        List<Project> projects = projectService.findAllTestProjectByUserId(loggedUserId);
        List<TCMusterDTO> tcMusters = new ArrayList<>();
        if (filter.equals("all")){
            l.warn("filtr je all - "+filter);
            tcMusters = tcMusterService.findTCMustersDTOByProject(projects);
        } else {
            l.warn("filtr není all, je to: "+filter);
            tcMusters = tcMusterService.findTCMustersDTOByProject(projectService.findTestProjectById(Long.parseLong(filter)));
        }

//        List<TCMusterDTO> tcMusters = filter == "all" ? tcMusterService.findTCMustersDTOByProject(projects) :
//                tcMusterService.findTCMustersDTOByProject(projectService.findTestProjectById(Long.parseLong(filter)));
        model.addAttribute("tcMusters", tcMusters);
        model.addAttribute("usersProjects", projectService.findAllTestProjectNameDTOByUserId(loggedUserId));
        model.addAttribute("statusenum", Arrays.asList(StatusEnum.values()));
        return "tcsAll";
    }

}