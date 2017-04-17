package cz.vse.controller;

import cz.vse.dto.DefectCommentDTO;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
@RequestMapping("/comment")
public class DefectCommentController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @Autowired
    DefectServiceImpl defectService;

    @Autowired
    DefectCommentService defectCommentService;

    @Autowired
    SecurityUtils securityUtils;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createDefect(Model model, DefectCommentDTO defectCommentDTO) {
        l.info("request mapping comment/create");
        model.addAttribute("commentDTO", new DefectCommentDTO());
        model.addAttribute("defects", defectService.findAllDefects());
        model.addAttribute("persons", personService.findAllPersons());

        return "commentCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(Model model, @Valid DefectCommentDTO defectCommentDTO, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            l.error("form has errors");
            Long defectId = defectCommentDTO.getDefect_id();
            model.addAttribute("defectDTO", defectService.findDefectDTO(defectId));
            model.addAttribute("loggedPersonName", securityUtils.getLoggedPersonName());
            model.addAttribute("defectCommentsByDefect", defectCommentService.findAllDefectCommentDTOByDefectId(defectId));

            model.addAttribute("personNames", personService.findAllPersonNames());
            model.addAttribute("listDefectCommentByDefectDTO", defectCommentService.findAllDefectCommentDTOByDefectId(defectId));
            return "defect";
        }

        if (defectCommentDTO.getId() == null) {
            //new person, add it
            defectCommentService.createComment(defectCommentDTO);
        } else {
            //existing person, call update
            defectCommentService.updateComment(defectCommentDTO);

        }

        Long defectId = defectCommentDTO.getDefect_id();
        model.addAttribute("defectDTO", defectService.findDefectDTO(defectId));
        model.addAttribute("loggedPersonName", securityUtils.getLoggedPersonName());
        model.addAttribute("defectCommentsByDefect", defectCommentService.findAllDefectCommentDTOByDefectId(defectId));

        model.addAttribute("personNames", personService.findAllPersonNames());
        model.addAttribute("listDefectCommentByDefectDTO", defectCommentService.findAllDefectCommentDTOByDefectId(defectId));

//        String referer = request.getHeader("Referer");
//        return "redirect:" + referer;
        return "defect";
    }


    @RequestMapping("/edit/{id}")
    public String editComment(@PathVariable("id") long id, Model model) {
        l.info("/edit/{id}" + id);
        DefectCommentDTO commentDTO = defectCommentService.findCommentDTOById(id);
        model.addAttribute("commentDTO", commentDTO);
        model.addAttribute("defects", defectService.findAllDefects());
        model.addAttribute("persons", personService.findAllPersons());
        model.addAttribute("defect", defectService.findDefectDTO(commentDTO.getDefect_id()));
//        model.addAttribute("person", personService.findPersonById(commentDTO.getAuthor_id()));

        return "commentCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeComment(@PathVariable("id") long id) {
        defectCommentService.deleteComment(id);
        return "redirect:/comment/create";
    }

}

