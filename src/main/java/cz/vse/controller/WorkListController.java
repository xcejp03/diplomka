package cz.vse.controller;

import cz.vse.dto.TCInstanceDTO;
import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.dto.WorkListDTO;
import cz.vse.entity.*;
import cz.vse.service.*;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
@RequestMapping("/worklist")
public class WorkListController {
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
    WorkListService workListService;

    @Autowired
    WorkTCService workTCService;

    @RequestMapping(method = RequestMethod.GET)
//    public String showWorkList(Model model) {
    public String showWorkList(Model model) {
        l.info("request mapping /worktc");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("listWorkListDTOMember", workListService.findAllWorkListDTOByMember(personId));
        model.addAttribute("listWorkListDTOAuthor", workListService.findAllWorkListDTOByAuthorId(personId));

//        model.addAttribute("listWorkTC", workTCService.findWorkTCDTOByWorkListId(id));
//        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
//        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
//        model.addAttribute("listPerson", personService.findAllPersons());
//        model.addAttribute("listPriority", PriorityTCEnum.values());
        return "workLists";
    }

    @RequestMapping(value = "/worktc", method = RequestMethod.GET)
//    public String showWorkList(Model model) {
    public String showWorkListWorkTC(Model model) {
        l.info("request mapping /worktc");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("listWorkListDTOMember", workListService.findAllWorkListDTOByMember(personId));
        model.addAttribute("listWorkListDTOAuthor", workListService.findAllWorkListDTOByAuthorId(personId));

//        model.addAttribute("listWorkTC", workTCService.findWorkTCDTOByWorkListId(id));
//        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
//        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
//        model.addAttribute("listPerson", personService.findAllPersons());
//        model.addAttribute("listPriority", PriorityTCEnum.values());
        return "workTCs";
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String showWorkListByMember(Model model) {
//    public String showWorkList(Model model,@RequestParam(required = true, value = "filter") String filter) {
        l.info("request mapping /worktc");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("listWorkListDTO", workListService.findAllWorkListDTOByMember(personId));
//        model.addAttribute("listWorkTC", workTCService.findWorkTCDTOByWorkListId(id));
//        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
//        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
//        model.addAttribute("listPerson", personService.findAllPersons());
//        model.addAttribute("listPriority", PriorityTCEnum.values());
        return "workLists";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createWorkList(Model model) {
        l.info("request mapping /worklist");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("workListDTO", new WorkListDTO());
//        model.addAttribute("listWorkTC", workTCService.findAllWorkTC());
//        model.addAttribute("listPerson", personService.findAllPersons());
        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
        model.addAttribute("listPriority", PriorityTCEnum.values());
        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
        return "workListCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTSPost(@ModelAttribute("project") WorkListDTO workListDTO) {
        if (workListDTO.getId() == null) {
            workListDTO.setAuthor_id(securityUtils.getLoggedPersonId());
            workListService.createWorkList(workListDTO);
        } else {
            workListService.updateWorkList(workListDTO);
        }
        return "redirect:/worklist";
    }

    @RequestMapping(value = "/worktc/edit", method = RequestMethod.POST)
    public String createTSPodsyyxt(@ModelAttribute("workListDTO") WorkListDTO workListDTO) {
        l.info("/worktc/edit");
        workListService.updateWorkList(workListDTO);
        l.info("/worktc/edit PROBĚHL");
        return "redirect:/worklist/worktc/" + workListDTO.getId();
    }


    @RequestMapping(value = "/worktc/{id}", method = RequestMethod.GET)
    public String showWorkTC(@PathVariable("id") int id, Model model) {
        l.info("request mapping /worktc");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("workListDTO", workListService.findWorkListDTOById(id));
//        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
        model.addAttribute("listPerson", personService.findAllPersons());
        model.addAttribute("listPriority", PriorityTCEnum.values());

        return "workTCCreate";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showWorkList(@PathVariable("id") int id, Model model) {
        l.info("request mapping /worklist/show/");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("workListDTO", workListService.findWorkListDTOById(id));
//        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
        return "workTCShow";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editWorkList(@PathVariable("id") int id,Model model) {
        l.info("request mapping /worklist/edit/"+id);
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("workListDTO", workListService.findWorkListDTOById(id));
//        model.addAttribute("listWorkTC", workTCService.findAllWorkTC());
        model.addAttribute("listTCMusterIdByWorklistInWorkTCDTO", workListService.getListTCMusterIdByWorklistInWorkTCDTO(id));
//        model.addAttribute("listPerson", personService.findAllPersons());
        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
        model.addAttribute("listPriority", PriorityTCEnum.values());
        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
        return "workListCreate";
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

    @RequestMapping("/history/{id}")
    public String showWorkTCHistory(@PathVariable("id") long id, Model model) {
        l.info("/history/{id}" + id);
        List<TCInstanceDTO> workTCInstances =  tcInstanceService.findTCInstancesDTOByWorkTCId(id);
        model.addAttribute("workListId", workTCService.findWorkTCById(id).getWorkList().getId());
        model.addAttribute("workTCInstances", workTCInstances);
        return "workTCHistory";
    }


    @RequestMapping("/run/{id}")
    public String runTCMuster(Model model, @PathVariable("id") long id) {
        TCInstanceRunDTO tcInstanceRunDTO;
        TCInstance tcInstance;
        Person person = securityUtils.getLoggedPerson();
        tcInstance = tcService.runNewTC(id, person);
        tcInstanceRunDTO = tcInstanceService.findTCInstanceRunDTOById(tcInstance.getId());
        model.addAttribute("tcInstance", tcInstanceRunDTO);
        model.addAttribute("listTSInstances", tsInstanceService.findAllTSInstancesByTCInstanceId(tcInstanceRunDTO.getTcInstance_id()));

        return "redirect:/tc/show/" + tcInstanceRunDTO.getId();
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

    @RequestMapping(value = "/tc-by-suite/{id}", method = RequestMethod.GET)
    public String tcBySuite(@PathVariable("id") long id, Model model) {
        l.info("/tc-by-suite/{id} - " + id);

        model.addAttribute("listTCDTO", tcService.findAllTCMustersDTOBySuiteId(id));
        model.addAttribute("suite", suiteService.findTestSuiteById(id));
//        model.addAttribute("statusenum", Arrays.asList(StatusEnum.values()));
        return "tcs";
    }

    @RequestMapping(value = "/tcs", method = RequestMethod.GET)
    public String tcsAllShow(Model model) {
        l.info("/tc/tcs");
        model.addAttribute("listTCDTO", tcMusterService.findAllTCDTOByUser(securityUtils.getLoggedPerson()));
//        model.addAttribute("suite", suiteService.findTestSuiteById(id));
//        model.addAttribute("statusenum", Arrays.asList(StatusEnum.values()));
        return "tcs";
    }

}