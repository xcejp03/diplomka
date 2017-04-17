package cz.vse.controller;

import cz.vse.dto.*;
import cz.vse.entity.Defect;
import cz.vse.entity.DefectStatusEnum;
import cz.vse.entity.Project;
import cz.vse.repository.*;
import cz.vse.service.*;
import cz.vse.utils.SecurityUtils;
import cz.vse.utils.excelexport.ExcelBuilderDefects;
import cz.vse.utils.excelexport.ExcelBuilderProjects;
import cz.vse.utils.excelexport.ExcelBuilderTC;
import cz.vse.utils.excelexport.ExcelBuilderTCHistory;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private TCService tcService;

    @Autowired
    TCInstanceService tcInstanceService;


    /**
     * Handle request to download an Excel document
     */
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
        l.warn("stahování excelu");
        // create some sample data
        List<Defect> defects = new ArrayList<>();

        defects.add(defectService.findDefect(160L));
        defects.add(defectService.findDefect(161L));
        defects.add(defectService.findDefect(520L));


        // return a view which will be resolved by an excel view resolver
        return new ModelAndView(new ExcelBuilderDefects(), "defects", defects);
    }

    @RequestMapping(value = "/downloadExcelProjects", method = RequestMethod.GET)
    public ModelAndView downloadExcelProjects() {
        l.warn("stahování excelu projekty");
        Long loggedPersonId = securityUtils.getLoggedPersonId();

        List<Project> projects = projectService.findAllTestProjectByUserId(loggedPersonId);
        List<ProjectStatsDTO> projectStatsDTOS = mapper.mapAsList(projects, ProjectStatsDTO.class);

        // return a view which will be resolved by an excel view resolver
        return new ModelAndView(new ExcelBuilderProjects(), "projects", projectStatsDTOS);
    }

    @RequestMapping(value = "/downloadExcelTC/{id}", method = RequestMethod.GET)
    public ModelAndView downloadExcelTC(@PathVariable("id") long id) {
        l.warn("stahování excelu TC");
        List<TCMusterList> tcMusterLists = tcService.findAllTCMusterListsBySuiteId(id);
        return new ModelAndView(new ExcelBuilderTC(), "tcs", tcMusterLists);
    }

    @RequestMapping(value = "/downloadExcelTCHistory/{id}", method = RequestMethod.GET)
    public ModelAndView downloadExcelTCHistory(@PathVariable("id") long id) {
        l.warn("stahování excelu TC History");
        List<TCInstanceList> tcInstanceLists = tcInstanceService.findAllTCInstanceListsByTCMusterId(id);
        return new ModelAndView(new ExcelBuilderTCHistory(), "tcInstanceLists", tcInstanceLists);
    }

    @RequestMapping(value = "/downloadExcelDefects", method = RequestMethod.GET)
    public ModelAndView downloadExcelDefects() {
        l.warn("stahování excelu Defekty ");
        List<DefectList> defectLists = defectService.findAllDefectLists();
        return new ModelAndView(new ExcelBuilderDefects(), "defects", defectLists);
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
            model.addAttribute("workListsToday", workListService.findAllWorkListDTOByMemberToday(securityUtils.getLoggedPerson()));
            model.addAttribute("workListsTomorrow", workListService.findAllWorkListDTOByMemberTomorrow(securityUtils.getLoggedPerson()));
            model.addAttribute("myOpenTC", workTCService.getMyOpenWorkTCDTO(securityUtils.getLoggedPerson()));
            model.addAttribute("myAssignedOpenDefects", defectService.findAllDefectDTOByAssigneeAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
            model.addAttribute("myOpenDefects", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
            model.addAttribute("workListsLastThreeDays", workListService.findAllWorkListDTOByMemberLastThreeDays(securityUtils.getLoggedPerson()));
            model.addAttribute("myProjectsStatistics", projectService.getMyProjectsWithStatistics(securityUtils.getLoggedPerson()));
        } else if (loggedPersonAuthorities.contains(new SimpleGrantedAuthority("ANALYTIC"))) {
            l.warn("role je analytik");
            List<DefectDTO> emptyListDefectDTO = new ArrayList<>();
            model.addAttribute("myProjectsStatistics", projectService.getMyProjectsWithStatistics(securityUtils.getLoggedPerson()));
            model.addAttribute("myAssignedOpenDefects", defectService.findAllDefectDTOByAssigneeAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open)); // DODĚLAT
            model.addAttribute("myOpenDefects", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));

            model.addAttribute("workListsToday", new ArrayList<WorkListDTO>());
            model.addAttribute("workListsTomorrow", new ArrayList<WorkListDTO>());
            model.addAttribute("workListsLastThreeDays", new ArrayList<WorkListDTO>());
            model.addAttribute("myOpenTC", new ArrayList<WorkTCDTO>());
        } else if (loggedPersonAuthorities.contains(new SimpleGrantedAuthority("MANAGER"))) {
            l.warn("role je manager");
            model.addAttribute("myAssignedOpenDefects", defectService.findAllDefectDTOByAssigneeAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
            model.addAttribute("myOpenDefects", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
            model.addAttribute("myProjectsStatistics", projectService.getMyProjectsWithStatistics(securityUtils.getLoggedPerson()));
            model.addAttribute("workListsToday", new ArrayList<WorkListDTO>());
            model.addAttribute("workListsTomorrow", new ArrayList<WorkListDTO>());
            model.addAttribute("workListsLastThreeDays", new ArrayList<WorkListDTO>());
            model.addAttribute("myOpenTC", new ArrayList<WorkTCDTO>());
        } else if (loggedPersonAuthorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            l.warn("role je admin");
            model.addAttribute("myAssignedOpenDefects", new ArrayList<DefectDTO>());
            model.addAttribute("myOpenDefects", new ArrayList<DefectDTO>());
            model.addAttribute("myProjectsStatistics", new ArrayList<ProjectStatsDTO>());
            model.addAttribute("workListsToday", new ArrayList<WorkListDTO>());
            model.addAttribute("workListsTomorrow", new ArrayList<WorkListDTO>());
            model.addAttribute("workListsLastThreeDays", new ArrayList<WorkListDTO>());
            model.addAttribute("myOpenTC", new ArrayList<WorkTCDTO>());
        } else {
            l.warn("role je žádná");
            model.addAttribute("myAssignedOpenDefects", new ArrayList<DefectDTO>());
            model.addAttribute("myOpenDefects", new ArrayList<DefectDTO>());
            model.addAttribute("myProjectsStatistics", new ArrayList<ProjectStatsDTO>());
            model.addAttribute("workListsToday", new ArrayList<WorkListDTO>());
            model.addAttribute("workListsTomorrow", new ArrayList<WorkListDTO>());
            model.addAttribute("workListsLastThreeDays", new ArrayList<WorkListDTO>());
            model.addAttribute("myOpenTC", new ArrayList<WorkTCDTO>());
        }

        return "dashboard";
    }


}



