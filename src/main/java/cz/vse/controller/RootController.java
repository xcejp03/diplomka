package cz.vse.controller;

import cz.vse.dto.TCMusterCopyDTO;
import cz.vse.entity.Defect;
import cz.vse.entity.DefectStatusEnum;
import cz.vse.repository.*;
import cz.vse.service.*;
import cz.vse.utils.SecurityUtils;
import cz.vse.utils.excelexport.ExcelBuilderDefects;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
public class RootController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @Autowired
    SuiteService suiteService;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private TSInstanceRepository tsInstanceRepository;

    @Autowired
    private TCInstanceRepository tcInstanceRepository;

    @Autowired
    private WorkTCService workTCService;

    @Autowired
    private WorkListService workListService;

    @Autowired
    private DefectService defectService;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WorkListRepository workListRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TCMusterRepository tcMusterRepository;

    @Autowired
    private WorkTCRepository workTCRepository;


    /**
     * Handle request to download an Excel document
     */
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
        l.warn("stahování excelu");
        // create some sample data
        List<Defect> defects = new ArrayList<>();

        defects.add(defectService.findDefect(110L));
        defects.add(defectService.findDefect(112L));
        defects.add(defectService.findDefect(114L));


        // return a view which will be resolved by an excel view resolver
        return new ModelAndView(new ExcelBuilderDefects(), "defects", defects);
    }

    @RequestMapping(value = "/manualTest")
    public String manualTest(@ModelAttribute("project") TCMusterCopyDTO tcMusterCopyDTO, Model model) {
        l.warn("manualTest: " + model);
        l.warn("manualTest2: " + tcMusterCopyDTO);

        return "redirect:/tc/tcs?filter=25";
    }



    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        l.info("request mapping root/index");
        return "redirect:dashboard";
    }


    @RequestMapping(value = "/dashboard")
    public String dashboard(Model model) {
        Collection<? extends GrantedAuthority> loggedPersonAuthorities = securityUtils.getLoggedPersonAuthorities();
        l.warn("Moje role: " + loggedPersonAuthorities);

        if (loggedPersonAuthorities.contains(new SimpleGrantedAuthority("TESTER"))) {
            l.warn("role je tester");
            l.warn("findTestXXX()" + personRepository.findXXX());
            model.addAttribute("workListsToday", workListService.findAllWorkListDTOByMemberToday(securityUtils.getLoggedPerson()));
            model.addAttribute("workListsTomorrow", workListService.findAllWorkListDTOByMemberTomorrow(securityUtils.getLoggedPerson()));
            model.addAttribute("myOpenTC", workTCService.getMyOpenWorkTCDTO(securityUtils.getLoggedPerson()));
            model.addAttribute("myAssignedOpenDefects", defectService.findAllDefectDTOByAssigneeAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
            model.addAttribute("myOpenDefects", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
            model.addAttribute("MyProjectsStat", projectService.getMyProjectsWithStatistics(securityUtils.getLoggedPerson()));
        }

        if (loggedPersonAuthorities.contains(new SimpleGrantedAuthority("ANALYTIC"))) {
            l.warn("role je analytik");
            model.addAttribute("myProjectsStatistics", projectService.getMyProjectsWithStatistics(securityUtils.getLoggedPerson()));
            model.addAttribute("myAssignedOpenDefects", defectService.findAllDefectDTOByAssigneeAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open)); // DODĚLAT
            model.addAttribute("myOpenDefects", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
        }


        if (loggedPersonAuthorities.contains(new SimpleGrantedAuthority("MANAGER"))) {
            l.warn("role je manager");
            model.addAttribute("myAssignedOpenDefects", defectService.findAllDefectDTOByAssigneeAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
            model.addAttribute("myOpenDefects", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
            model.addAttribute("myProjectsStatistics", projectService.getMyProjectsWithStatistics(securityUtils.getLoggedPerson()));
            model.addAttribute("workListsToday", workListService.findAllWorkListDTOByMemberToday(securityUtils.getLoggedPerson()));
            model.addAttribute("workListsTomorrow", workListService.findAllWorkListDTOByMemberTomorrow(securityUtils.getLoggedPerson()));
            model.addAttribute("workListsLastThreeDays", workListService.findAllWorkListDTOByMemberLastThreeDays(securityUtils.getLoggedPerson()));
        }

        return "dashboard";
    }


}



