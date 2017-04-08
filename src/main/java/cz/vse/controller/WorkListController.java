package cz.vse.controller;

import cz.vse.dto.*;
import cz.vse.entity.*;
import cz.vse.service.*;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        l.info("/worklist");
        Long personId = securityUtils.getLoggedPersonId();
//        model.addAttribute("workListMember", workListService.findAllWorkListListByMember(personId));
        model.addAttribute("membersWorkLists", workListService.findAllWorkListListByMember(personId));
        model.addAttribute("authorsWorkLists", workListService.findAllWorkListListByAuthorId(personId));

//        model.addAttribute("listWorkTC", workTCService.findWorkTCDTOByWorkListId(id));
//        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
//        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
//        model.addAttribute("listPerson", personService.findAllPersons());
//        model.addAttribute("listPriority", PriorityTCEnum.values());
        return "workLists";
    }

    @RequestMapping(value = "/worktc", method = RequestMethod.GET)
    public String showWorkListWorkTC(Model model) {
        l.info("/worklist/worktc");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("membersWorkLists", workListService.findAllWorkListListByMember(personId));
        model.addAttribute("authorsWorkLists", workListService.findAllWorkListListByAuthorId(personId));

//        model.addAttribute("listWorkTC", workTCService.findWorkTCDTOByWorkListId(id));
//        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
//        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
//        model.addAttribute("listPerson", personService.findAllPersons());
//        model.addAttribute("listPriority", PriorityTCEnum.values());
        return "workTCs";
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String showWorkListByMember(Model model) {
        l.info("/worklist/member");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("membersWorkLists", workListService.findAllWorkListListByMember(personId));
//        model.addAttribute("listWorkTC", workTCService.findWorkTCDTOByWorkListId(id));
//        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
//        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
//        model.addAttribute("listPerson", personService.findAllPersons());
//        model.addAttribute("listPriority", PriorityTCEnum.values());
        return "workLists";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createWorkList(Model model, WorkListForm workListForm) {
        l.info("/worklist/create");
        Long personId = securityUtils.getLoggedPersonId();
//        model.addAttribute("workListForm", new WorkListForm());
//        model.addAttribute("listWorkTC", workTCService.findAllWorkTC());
//        model.addAttribute("listPerson", personService.findAllPersons());
        model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(personId));
        model.addAttribute("priorityList", PriorityTCEnum.values());
        model.addAttribute("tcNamesByProject", tcMusterService.findAllTestCaseMustersNames());
        return "workListCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTSPost(Model model, @Valid WorkListForm workListForm, BindingResult bindingResult) {
        l.info("/worklist/create creating");

        if (bindingResult.hasErrors()) {
            l.error("form has errors");
            Long personId = securityUtils.getLoggedPersonId();
            model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(personId));
            model.addAttribute("priorityList", PriorityTCEnum.values());
            model.addAttribute("tcNamesByProject", tcMusterService.findAllTestCaseMustersNames());
            return "workListCreate";
        }


        if (workListForm.getId() == null) {
            workListForm.setAuthor_id(securityUtils.getLoggedPersonId());
            workListService.createWorkList(workListForm);
        } else {
            workListService.updateWorkList(workListForm);
        }
        return "redirect:/worklist";
    }

    /**
     * TADY --------------
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showWorklist(@PathVariable("id") int id, Model model) {
        l.info("/worklist/worktc/" + id);

        model.addAttribute("workListDTO", workListService.findWorkListDTOById(id));
//        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
        model.addAttribute("persons", personService.findAllPersonNames());
        model.addAttribute("priorityList", PriorityTCEnum.values());
        return "worklist";
    }

    @RequestMapping(value = "/worktc/{id}", method = RequestMethod.GET)
    public String showWorkTCList(@PathVariable("id") int id, Model model) {
        l.info("/worklist/worktc/" + id);
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("workTCWrapper", workListService.findWorkTCWrapperByWorklistId(id));


        model.addAttribute("workListDTO", workListService.findWorkListDTOById(id));
//        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
        model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(personId));
        model.addAttribute("persons", personService.findAllPersonNames());
        model.addAttribute("priorityList", PriorityTCEnum.values());
        return "workTC";
    }

    @RequestMapping(value = "/worktc/edit", method = RequestMethod.POST)
    public String editWorkTC(Model model,WorkTCWrapper workTCWrapper) {
        l.info("/worklist/worktc/edit");
//        workListService.updateWorkList(workListForm);
        workListService.updateWorkTC(workTCWrapper);
        return "redirect:/worklist/worktc/" + workTCWrapper.getWorkList_id();
    }


//    @RequestMapping(value = "/worktc/{id}", method = RequestMethod.GET)
//    public String showWorkTC(@PathVariable("id") int id, Model model) {
//        l.info("/worklist/worktc/" + id);
//        Long personId = securityUtils.getLoggedPersonId();
//        model.addAttribute("workListDTO", workListService.findWorkListDTOById(id));
////        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
//        model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(personId));
//        model.addAttribute("persons", personService.findAllPersonNames());
//        model.addAttribute("priorityList", PriorityTCEnum.values());
//        return "workTCCreate";
//    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showWorkList(@PathVariable("id") int id, Model model) {
        l.info("request mapping /worklist/show/");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("workListDTO", workListService.findWorkListDTOById(id));
//        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectNameDTOByUserId(personId));
        return "workTCShow";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editWorkList(@PathVariable("id") int id, Model model) {
        l.info("/worklist/edit/" + id);
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("workListForm", workListService.findWorkListFormById(id));
//        model.addAttribute("listWorkTC", workTCService.findAllWorkTC());
        model.addAttribute("tcMusterIdByWorklistInWorkTC", workListService.getListTCMusterIdByWorklistInWorkTCDTO(id));
//        model.addAttribute("listPerson", personService.findAllPersons());
        model.addAttribute("usersProjects", projectService.findAllTestProjectNamesByUserId(personId));
        model.addAttribute("priorityList", PriorityTCEnum.values());
        model.addAttribute("tcsByProject", tcMusterService.findAllTestCaseMusters());
        model.addAttribute("tcNamesByProject", tcMusterService.findAllTestCaseMustersNames());

        return "workListCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeTCMuster(@PathVariable("id") long id) {
        l.info("/worklist/remove/" + id);
        tcMusterService.deleteTestCaseMusterById(id);
        return "redirect:/tc/create";
    }

    @RequestMapping("/instance/remove/{id}")
    public String removeTInstance(@PathVariable("id") long id) {
        l.info("/worklist/instance/remove/" + id);
        Long tcInstanceMusterId = tcInstanceService.findTestCaseInstanceById(id).gettCMuster().getId();
        tcInstanceService.deleteTestCaseInstanceById(id);
        return "redirect:/tc/history/" + tcInstanceMusterId;
    }

    @RequestMapping("/history/{id}")
    public String showWorkTCHistory(@PathVariable("id") long id, Model model) {
        l.info("/worklist/history/" + id);
        model.addAttribute("workListId", workTCService.findWorkTCById(id).getWorkList().getId());
        model.addAttribute("tcInstanceLists", tcInstanceService.findTCInstanceListsByWorkTCId(id));
        return "workTCHistory";
    }

    @RequestMapping("/run/{id}")
    public String runTCMuster(Model model, @PathVariable("id") long id,  @RequestParam(required = false, defaultValue = "null", value = "origin") String instanceOrigin,
                              @RequestParam(required = false, defaultValue = "null", value = "backid") String backId) {
        l.info("/worklist/run/" + id);
        TCInstanceRunDTO tcInstanceRunDTO;
        TCInstance tcInstance;
        Person person = securityUtils.getLoggedPerson();
        tcInstance = tcService.runNewTC(id, person, backId, instanceOrigin);
        tcInstanceRunDTO = tcInstanceService.findTCInstanceRunDTOById(tcInstance.getId());
//        model.addAttribute("tcInstance", tcInstanceRunDTO);
//        model.addAttribute("tsInstances", tsInstanceService.findAllTSInstanceListsByTCInstanceId(tcInstanceRunDTO.getTcInstance_id()));

        return "redirect:/tc/show/" + tcInstanceRunDTO.getId();
    }

    @RequestMapping("/show/{id}")
    public String showTCMuster(Model model, @PathVariable("id") long id) {
        l.info("/worklist/show/" + id);
        TCInstanceRunDTO tcInstanceRunDTO;
        tcInstanceRunDTO = tcInstanceService.findTCInstanceRunDTOById(id);
        model.addAttribute("tcInstance", tcInstanceRunDTO);
        model.addAttribute("tsInstances", tsInstanceService.
                findAllTSInstanceListsByTCInstanceId(tcInstanceRunDTO.getTcInstance_id()));
        return "tcRun";
    }

    @RequestMapping(value = "/tc-by-suite/{id}", method = RequestMethod.GET)
    public String tcBySuite(@PathVariable("id") long id, Model model) {
        l.info("/wotklist/tc-by-suite/" + id);
        model.addAttribute("tcs", tcService.findAllTCMusterListsBySuiteId(id));
        model.addAttribute("suite", suiteService.findTestSuiteListById(id));
        return "tcs";
    }

    @RequestMapping(value = "/tcs", method = RequestMethod.GET)
    public String tcsAllShow(Model model) {
        l.info("/tc/tcs");
        model.addAttribute("tcs", tcMusterService.findAllTCListsByUser(securityUtils.getLoggedPerson()));
//        model.addAttribute("suite", suiteService.findTestSuiteById(id));
//        model.addAttribute("statusenum", Arrays.asList(StatusEnum.values()));
        return "tcs";
    }

}