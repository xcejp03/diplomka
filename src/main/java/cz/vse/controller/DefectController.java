package cz.vse.controller;

import cz.vse.dto.DefectCommentDTO;
import cz.vse.dto.DefectDTO;
import cz.vse.dto.DefectForm;
import cz.vse.entity.DefectStatusEnum;
import cz.vse.service.DefectCommentService;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.impl.DefectServiceImpl;
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
@RequestMapping("/defect")
public class DefectController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @Autowired
    DefectServiceImpl defectService;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private DefectCommentService defectCommentService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createDefect(Model model, DefectForm defectForm) {
        l.info("request mapping defect/create");
        model.addAttribute("persons", personService.findAllPersonNames());

        return "defectCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createDefect(Model model, @Valid DefectForm defectForm, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            l.error("form has errors");
            model.addAttribute("persons", personService.findAllPersonNames());
            return "defectCreate";
        }

        if (defectForm.getId() == null) {
            defectForm.setReporter_id(securityUtils.getLoggedPersonId());
            defectService.createDefect(defectForm);
        } else {
            defectService.updateDefect(defectForm);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/edit/{id}")
    public String editDefect(@PathVariable("id") long id, Model model) {
        l.info("/edit/{id}" + id);
        model.addAttribute("defectForm", defectService.findDefectForm(id));
        model.addAttribute("personNames", personService.findAllPersonNames());
        return "defectCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeDefect(@PathVariable("id") long id, Model model) {
        defectService.deleteDefect(id);
        return "redirect:/defect/create";

    }

    @RequestMapping(value = "/defects", method = RequestMethod.GET)
    public String defectShowByUser(Model model, @RequestParam(required = false, defaultValue = "open", value = "filter") String filter) {
        l.info("request mapping defect/defects");
        DefectStatusEnum filterStatus = DefectStatusEnum.valueOf(filter);
        model.addAttribute("defectListByReporter", defectService.findAllDefectListsByReporterAndStatus(securityUtils.getLoggedPerson(), filterStatus));
        model.addAttribute("defectListByAssignee", defectService.findAllDefectListsByAssigneeAndStatus(securityUtils.getLoggedPerson(), filterStatus));
        return "defects";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String defectShowById(@PathVariable("id") long id, Model model) {
        l.info("request mapping defect/" + id);
        model.addAttribute("defectDTO", defectService.findDefectDTO(id));

        model.addAttribute("personNames", personService.findAllPersonNames());
        model.addAttribute("listDefectCommentByDefectDTO", defectCommentService.findAllDefectCommentDTOByDefectId(id));
        model.addAttribute("defectCommentsByDefect", defectCommentService.findAllDefectCommentDTOByDefectId(id));
        model.addAttribute("commentDTO", new DefectCommentDTO());
        model.addAttribute("defectCommentDTO", new DefectCommentDTO());
        model.addAttribute("loggedPersonName", securityUtils.getLoggedPersonName());
        return "defect";
    }

    @RequestMapping(value = "/writeAssigneeChange", method = RequestMethod.POST)
    public String writeAssigneeChange(DefectDTO defectDTO) {
        String redirectSite;
        defectService.changeDefectAssignee(defectDTO, securityUtils.getLoggedPerson());
        redirectSite = "redirect:/defect/" + defectDTO.getId();
        return redirectSite;
    }

    @RequestMapping(value = "/writeStatusChange", method = RequestMethod.POST)
    public String writeStatusChange(DefectDTO defectDTO) {
        l.info("/writeStatusChange");
        String redirectSite;
        defectService.changeDefectStatus(defectDTO, securityUtils.getLoggedPerson());
        redirectSite = "redirect:/defect/" + defectDTO.getId();

        return redirectSite;
    }

}
