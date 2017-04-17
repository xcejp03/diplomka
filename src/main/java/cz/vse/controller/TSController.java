package cz.vse.controller;

import cz.vse.dto.TSInstanceRunDTO;
import cz.vse.dto.TSMusterForm;
import cz.vse.entity.Person;
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

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
@RequestMapping("/ts")
public class TSController {
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
    TSInstanceService tsInstanceService;

    @Autowired
    TCInstanceService tcInstanceService;

    @Autowired
    SecurityUtils securityUtils;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTS(Model model, TSMusterForm tsMusterForm, @RequestParam(required = false, value = "tcmuster") Long tcmusterId) {
        l.info("/ts/create");
//        TSMusterForm tsMusterForm = new TSMusterForm();
        tsMusterForm.setTcMuster_id(tcmusterId);

//        model.addAttribute("tsMusterForm", tsMusterForm);
//        model.addAttribute("listTSMusters", tsMusterService.findAllTestStepMustersDTO());
        model.addAttribute("persons", personService.findAllPersonNames());
        model.addAttribute("loggedPerson", securityUtils.getLoggedPerson());
        model.addAttribute("tcMusters", tcMusterService.findAllTestCaseMustersNames());
        return "tsCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTSPost(Model model, @Valid TSMusterForm tsMusterForm, BindingResult bindingResult, HttpServletRequest request) {
        l.info("/ts/create post");
        Person loggedPerson = securityUtils.getLoggedPerson();

        if (bindingResult.hasErrors()) {
            l.error("form has errors");
            model.addAttribute("persons", personService.findAllPersonNames());
            model.addAttribute("loggedPerson", loggedPerson);
            model.addAttribute("tcMusters", tcMusterService.findAllTestCaseMustersNames());
            return "tsCreate";
        }


        if (tsMusterForm.getId() == null) {
            tsMusterForm.setAuthor_id(loggedPerson.getId());
            tsMusterService.createTestStepMuster(tsMusterForm);
        } else {
            tsMusterService.updateTestStepMuster(tsMusterForm);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/edit/{id}")
    public String editTSMuster(@PathVariable("id") long id, Model model, TSMusterForm tsMusterForm, @RequestParam(required = false, value = "tcmuster") Long tcmusterId) {
        l.info("/ts/edit/{id}" + id);
        tsMusterForm = tsMusterService.findTestStepMusterFormById(id);

        model.addAttribute("tsMusterForm", tsMusterForm);
        model.addAttribute("TSMusterForm", tsMusterForm);
        model.addAttribute("persons", personService.findAllPersonNames());
        model.addAttribute("tcMusters", tcMusterService.findAllTestCaseMustersNames());
        return "tsCreate";
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public String runTSInstance(Model model, @Valid TSInstanceRunDTO TSInstanceRunDTO, BindingResult bindingResult) {
        l.info("/ts/run");

        if (bindingResult.hasErrors()) {
            l.error("form has errors");
//            model.addAttribute("tsInstanceRun", tsInstanceService.findTestStepInstanceRunDTOById(id));
            return "tsRun";
        }

        Long tcInstanceId = TSInstanceRunDTO.getTcInstance_id();
        Long personId = securityUtils.getLoggedPersonId();
        TSInstanceRunDTO.setTesterUpdate_id(personId);
        tsInstanceService.updateTestStepInstance(TSInstanceRunDTO);

        return "redirect:/tc/show/" + tcInstanceId;
    }

    @RequestMapping(value = "/run/{id}", method = RequestMethod.GET)
    public String runTSInstancePost(Model model, TSInstanceRunDTO TSInstanceRunDTO, @PathVariable("id") long id) {
        l.info("/ts/run/" + id);

        model.addAttribute("TSInstanceRunDTO", tsInstanceService.findTestStepInstanceRunDTOById(id));
        return "tsRun";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showTSInstancePost(@PathVariable("id") long id, Model model) {
        l.info("/ts/show/" + id);
        model.addAttribute("TSInstanceRunDTO", tsInstanceService.findTestStepInstanceRunDTOById(id));
        return "tsShow";
    }

    @RequestMapping("/remove/{id}")
    public String removeTSMuster(@PathVariable("id") long id) {
        l.info("/ts/remove/" + id);
        tsMusterService.deleteTestStepMuster(id);
        return "redirect:/ts/create";
    }

    @RequestMapping("/instance/remove/{id}")
    public String removeTSInstance(@PathVariable("id") long id) {
        l.info("/ts/instance/remove/" + id);
        Long tcInstanceId = tsInstanceService.findTestStepInstanceById(id).gettCInstance().getId();
        tsInstanceService.deleteTestStepInstanceById(id);
        return "redirect:/tc/show/" + tcInstanceId;
    }

    @RequestMapping("/ts-by-tcmuster/{id}")
    public String tsByTC(@PathVariable("id") long id, Model model) {
        l.info("/ts/ts-by-tc/" + id);
        model.addAttribute("tsLists", tsMusterService.findAllTSMusterListsByTCMusterId(id));
        model.addAttribute("tcName", tcMusterService.findTestCaseMusterNameById(id));
        return "tss";
    }
}