package cz.vse.controller;

import cz.vse.dto.TSInstanceRunDTO;
import cz.vse.dto.TSMusterDTO;
import cz.vse.entity.Person;
import cz.vse.service.*;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        l.info("request mapping ts/create");
        TSMusterDTO tsDTO = new TSMusterDTO();
        tsDTO.setTcMuster_id(tcmusterId);

        model.addAttribute("tsDTO", tsDTO);
        model.addAttribute("listTSMusters", tsMusterService.findAllTestStepMustersDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("loggedPerson", securityUtils.getLoggedPerson());
        model.addAttribute("listTCMusters", tcMusterService.findAllTestCaseMusters());
        return "tsCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTSPost(@ModelAttribute("ts") TSMusterDTO tsMusterDTO, HttpServletRequest request) {
        if (tsMusterDTO.getId() == null) {
            tsMusterService.createTestStepMuster(tsMusterDTO);
        } else {
            tsMusterService.updateTestStepMuster(tsMusterDTO);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/edit/{id}")
    public String editTSMuster(@PathVariable("id") long id, Model model, @RequestParam(required = false, value = "tcmuster") Long tcmusterId) {
        l.info("/edit/{id}" + id);

        model.addAttribute("tsDTO", tsMusterService.findTestStepMusterDTOById(id));
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listTCMusters", tcMusterService.findAllTestCaseMusters());
        return "tsCreate";
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public String runTSInstance(@ModelAttribute("ts") TSInstanceRunDTO tsInstanceRunDTO) {
        Long tcInstanceId = tsInstanceRunDTO.getTcInstance_id();
        l.info("/run/ - post");
        Long personId = securityUtils.getLoggedPersonId();
        tsInstanceRunDTO.setTesterUpdate_id(personId);
        tsInstanceService.updateTestStepInstance(tsInstanceRunDTO);

        return "redirect:/tc/show/" + tcInstanceId;
    }

    @RequestMapping(value = "/run/{id}", method = RequestMethod.GET)
    public String runTSInstancePost(@PathVariable("id") long id, Model model) {
        l.info("/run/{id}" + id);
        model.addAttribute("tsDTO", tsInstanceService.findTestStepInstanceRunDTOById(id));
        return "tsRun";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showTSInstancePost(@PathVariable("id") long id, Model model) {
        l.info("/run/{id}" + id);
        model.addAttribute("tsDTO", tsInstanceService.findTestStepInstanceRunDTOById(id));
        return "tsShow";
    }

    @RequestMapping("/remove/{id}")
    public String removeTSMuster(@PathVariable("id") long id) {
        tsMusterService.deleteTestStepMuster(id);
        return "redirect:/ts/create";
    }

    @RequestMapping("/instance/remove/{id}")
    public String removeTSInstance(@PathVariable("id") long id) {
        Long tcInstanceId = tsInstanceService.findTestStepInstanceById(id).gettCInstance().getId();
        tsInstanceService.deleteTestStepInstanceById(id);
        return "redirect:/tc/show/" + tcInstanceId;
    }

    @RequestMapping("/ts-by-tcmuster/{id}")
    public String tsByTC(@PathVariable("id") long id, Model model) {
        l.info("/ts-by-tc/{id} - " + id);
        model.addAttribute("listTSDTO", tsMusterService.findAllTSMustersDTOByTCMusterId(id));
        model.addAttribute("tc", tcMusterService.findTestCaseMusterById(id));
        return "tss";
    }
}