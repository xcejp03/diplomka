package cz.vse.controller;

import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.dto.TCMusterDTO;
import cz.vse.dto.WorkListDTO;
import cz.vse.dto.WorkTCDTO;
import cz.vse.entity.*;
import cz.vse.service.*;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String tcDefault(Model model) {
        l.info("request mapping /worklist");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("workListDTO", new WorkListDTO());
        model.addAttribute("listWorkTC", workTCService.findAllWorkTC());
        model.addAttribute("listPerson", personService.findAllPersons());
        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectsByUserIdDTO(personId));
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
            workListService.createWorkList(workListDTO);
        }
        return "redirect:/worklist";
    }

    @RequestMapping(value = "/worktc/edit", method = RequestMethod.POST)
    public String createTSPodsyyxt(@ModelAttribute("workListDTO") WorkListDTO workListDTO) {
        l.info("/worktc/edit");
        workListService.updateWorkList(workListDTO);
        l.info("/worktc/edit PROBĚHL");
        return "redirect:/worklist/worktc/"+workListDTO.getId();
    }



    @RequestMapping(value = "/worktc/{id}",method = RequestMethod.GET)
    public String showWorkTC(@PathVariable("id") int id, Model model) {
        l.info("request mapping /worktc");
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("workListDTO", workListService.findWorkListDTOById(id));
//        model.addAttribute("listWorkTC", workTCService.findWorkTCDTOByWorkListId(id));
        model.addAttribute("listTCByProject", tcMusterService.findAllTestCaseMusters());
        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectsByUserIdDTO(personId));
        model.addAttribute("listPerson", personService.findAllPersons());
        model.addAttribute("listPriority", PriorityTCEnum.values());
        return "workTCCreate";
    }


    @RequestMapping(value = "/xxx", method = RequestMethod.POST)
//    public String YourActionName( ){
    @ResponseStatus(value= HttpStatus.OK)
    public String YourActionName(@RequestParam("memberId") String id, @RequestParam("memberPw") String pw ){
        l.error("Jooooooo");
        return "ExpectedReturnView";
    }
    @RequestMapping(value = "/xx2")
//    public String YourActionNamde( ){
    @ResponseStatus(value= HttpStatus.OK)
    public String YourAcfstionName(@RequestParam("memberId") String id, @RequestParam("memberPw") String pw ){
        l.error("Joooooooo - 2");
        return "workListCreate";
    }

    @RequestMapping(value = "/empty")
//    public String YourActionName( ){
    @ResponseStatus(value= HttpStatus.OK)
    public String YourActionNadme(){
        l.error("Jooooooo je to empty");
        return "ExpectedReturnView";
    }

    @RequestMapping("/edit/{id}")
    public String editTCMuster(@PathVariable("id") long id, Model model) {
        l.info("/edit/{id}" + id);
        Long personId = securityUtils.getLoggedPersonId();
        model.addAttribute("tcDTO", tcMusterService.findTestCaseMusterDTOById(id));
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listProjects", projectService.findAllTestProjects());
        model.addAttribute("listUsersProjectsDTO", projectService.findAllTestProjectsByUserIdDTO(personId));
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
    public String runTCMuster(Model model, @PathVariable("id") long id) {
        TCInstanceRunDTO tcInstanceRunDTO;
        Person person = securityUtils.getLoggedPerson();
        tcInstanceRunDTO = tcService.runNewTC(id, person);
        model.addAttribute("tcInstance", tcInstanceRunDTO);
        model.addAttribute("listTSInstances", tsInstanceService.findAllTSInstancesByTCInstanceId(tcInstanceRunDTO.getTcInstance_id()));

        return "redirect:/tc/show/"+tcInstanceRunDTO.getId();
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
    public String tcsAllShow(Model model) {
        l.info("/tc/tcs");
        model.addAttribute("listTCDTO", tcMusterService.findAllTCDTOByUser(securityUtils.getLoggedPerson()));
//        model.addAttribute("suite", suiteService.findTestSuiteById(id));
        model.addAttribute("statusenum", Arrays.asList(StatusEnum.values()));
        return "tcs";
    }

}