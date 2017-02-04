package cz.vse.controller;

import cz.vse.dto.TSMusterForm;
import cz.vse.dto.old.TSInstanceRunDTO;
import cz.vse.service.*;
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
    public String createTS(Model model, @RequestParam(required = false, value = "tcmuster") Long tcmusterId) {
        l.info("/ts/create");
        TSMusterForm tsMusterForm = new TSMusterForm();
        tsMusterForm.setTcMuster_id(tcmusterId);

        model.addAttribute("tsMusterForm", tsMusterForm);
//        model.addAttribute("listTSMusters", tsMusterService.findAllTestStepMustersDTO());
        model.addAttribute("persons", personService.findAllPersonNames());
        model.addAttribute("loggedPerson", securityUtils.getLoggedPerson());
        model.addAttribute("tcMusters", tcMusterService.findAllTestCaseMustersNames());
        return "tsCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTSPost(@ModelAttribute("ts") TSMusterForm tsMusterForm, HttpServletRequest request) {
        l.info("/ts/create");
        if (tsMusterForm.getId() == null) {
            tsMusterService.createTestStepMuster(tsMusterForm);
        } else {
            tsMusterService.updateTestStepMuster(tsMusterForm);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/edit/{id}")
    public String editTSMuster(@PathVariable("id") long id, Model model, @RequestParam(required = false, value = "tcmuster") Long tcmusterId) {
        l.info("/ts/edit/{id}" + id);

        model.addAttribute("tsMusterForm", tsMusterService.findTestStepMusterFormById(id));
        model.addAttribute("persons", personService.findAllPersonNames());
        model.addAttribute("tcMusters", tcMusterService.findAllTestCaseMustersNames());
        return "tsCreate";
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public String runTSInstance(@ModelAttribute("ts") TSInstanceRunDTO tsInstanceRunDTO) {
        l.info("/ts/run");
        Long tcInstanceId = tsInstanceRunDTO.getTcInstance_id();
        Long personId = securityUtils.getLoggedPersonId();
        tsInstanceRunDTO.setTesterUpdate_id(personId);
        tsInstanceService.updateTestStepInstance(tsInstanceRunDTO);

        return "redirect:/tc/show/" + tcInstanceId;
    }

    @RequestMapping(value = "/run/{id}", method = RequestMethod.GET)
    public String runTSInstancePost(@PathVariable("id") long id, Model model) {
        l.info("/ts/run/" + id);
        model.addAttribute("tsInstanceRun", tsInstanceService.findTestStepInstanceRunDTOById(id));
        return "tsRun";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showTSInstancePost(@PathVariable("id") long id, Model model) {
        l.info("/ts/show/"+id);
        model.addAttribute("tsInstanceRun", tsInstanceService.findTestStepInstanceRunDTOById(id));
        return "tsShow";
    }

    @RequestMapping("/remove/{id}")
    public String removeTSMuster(@PathVariable("id") long id) {
        l.info("/ts/remove/"+id);
        tsMusterService.deleteTestStepMuster(id);
        return "redirect:/ts/create";
    }

    @RequestMapping("/instance/remove/{id}")
    public String removeTSInstance(@PathVariable("id") long id) {
        l.info("/ts/instance/remove/"+id);
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