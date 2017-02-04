package cz.vse.mapping;

import cz.vse.dto.*;
import cz.vse.dto.DefectCommentDTO;
import cz.vse.dto.DefectDTO;
import cz.vse.dto.ProjectDTO;
import cz.vse.dto.ProjectStatsDTO;
import cz.vse.dto.TCInstanceDTO;
import cz.vse.dto.TCInstanceRunDTO;
import cz.vse.dto.TSInstanceRunDTO;
import cz.vse.dto.TSMusterDTO;
import cz.vse.dto.TestSuiteDTO;
import cz.vse.dto.WorkListDTO;
import cz.vse.dto.WorkTCDTO;
import cz.vse.entity.*;
import cz.vse.mapping.utils.CustomMapperMap;
import cz.vse.repository.TSMusterRepository;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by pcejka on 10.10.2016.
 */
@Component
public class MappingConfigurator extends ConfigurableMapper implements ApplicationContextAware {
    public MappingConfigurator() {
        super(false);
    }

    private MapperFactory factory;

    private ApplicationContext applicationContext;

    private CustomMapperMap customMappers;

    @Override
    protected void configure(final MapperFactory factory) {
        this.factory = factory;
        configureConverters(applicationContext);
        configureCustomMappers();
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.init();
        configureClassMaps();
    }

    private void configureConverters(final ApplicationContext applicationContext) {
        final Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
        for (final Converter converter : converters.values()) {
            addConverter(converter);
        }
    }

    public void addConverter(final Converter<?, ?> converter) {
        factory.getConverterFactory().registerConverter(converter);
    }

    private void configureCustomMappers() {
        final Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
        customMappers = new CustomMapperMap(mappers.values());
    }

    @Autowired
    TSMusterRepository tsMusterRepository;

    private void configureClassMaps() {

        factory.classMap(Project.class, ProjectDTO.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("projectOwner", "projectOwner_id")
//                .field("personMembers{id}", "projectMembers_id{}")
                .field("personMembers", "projectMembers_id")
                .field("testSuites", "suites_id")
                .field("tcMusters", "tcMusters_id")
                .byDefault()
                .register();
        factory.classMap(Project.class, ProjectForm.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("projectOwner", "projectOwner_id")
                .field("personMembers", "projectMembers_id")
                .field("testSuites", "suites_id")
                .field("tcMusters", "tcMusters_id")
                .byDefault()
                .register();
        factory.classMap(DefectCommentDTO.class, DefectComment.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("defect_id", "defect.id")
                .field("author_id", "author.id")
                .byDefault()
                .register();
        factory.classMap(Defect.class, DefectDTO.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("assignee", "assignee_id")
                .field("reporter", "reporter_id")
                .field("projectSource", "projectSource_id")
                .field("tcInstanceSource", "tcInstanceSource_id")
                .field("tsInstanceSource", "tsInstanceSource_id")
                .byDefault()
                .register();
        factory.classMap(Defect.class, DefectForm.class)
                .mapNullsInReverse(false).mapNulls(false)
                .field("assignee", "assignee_id")
                .field("reporter", "reporter_id")
                .field("projectSource", "projectSource_id")
                .field("tcInstanceSource", "tcInstanceSource_id")
                .field("tsInstanceSource", "tsInstanceSource_id")
                .byDefault()
                .register();
        factory.classMap(Defect.class, DefectList.class)
                .mapNullsInReverse(false).mapNulls(false)
                .field("assignee", "assignee_id")
                .byDefault()
                .register();
        factory.classMap(Person.class, PersonForm.class)
                .mapNullsInReverse(false).mapNulls(false)
                .customize((Mapper<Person, PersonForm>) customMappers.get(Person.class, PersonForm.class))
//                .field("userRole", "userRolesEnum")
                .byDefault()
                .register();
        factory.classMap(Person.class, PersonForm.class)
                .mapNullsInReverse(false).mapNulls(false)
//                .customize((Mapper<Person, PersonForm>) customMappers.get(Person.class, PersonForm.class))
//                .field("userRole", "userRolesEnum")
                .byDefault()
                .register();
        factory.classMap(Person.class, PersonName.class)
                .mapNullsInReverse(false).mapNulls(false)
                .byDefault()
                .register();
        factory.classMap(TCMuster.class, TCMusterDTO.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("project", "project_id")
                .field("tsMusters", "tsMusters_id")
                .field("tcInstances", "tcInstances_id")
                .field("testSuites", "testSuite_id")
                .field("author", "author_id")
                .customize((Mapper<TCMuster, TCMusterDTO>) customMappers.get(TCMuster.class, TCMusterDTO.class))
                .byDefault()
                .register();
        factory.classMap(TCMuster.class, TCMusterList.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("project", "project_id")
                .field("tsMusters", "tsMusters_id")
                .field("tcInstances", "tcInstances_id")
                .field("testSuites", "testSuite_id")
                .field("author", "author_id")
//                .customize((Mapper<TCMuster, TCMusterDTO>) customMappers.get(TCMuster.class, TCMusterDTO.class))
                .byDefault()
                .register();
        factory.classMap(TCMuster.class, TCMusterName.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .byDefault()
                .register();
        factory.classMap(TCMuster.class, TCMusterForm.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("project", "project_id")
                .field("tsMusters", "tsMusters_id")
                .field("tcInstances", "tcInstances_id")
                .field("testSuites", "testSuite_id")
                .field("author", "author_id")
                .byDefault()
                .register();
        factory.classMap(TSMuster.class, TSMusterDTO.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("author", "author_id")
                .field("tCMuster", "tcMuster_id")
                .byDefault()
                .register();
        factory.classMap(TSMuster.class, TSMusterForm.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("author", "author_id")
                .field("tCMuster", "tcMuster_id")
                .byDefault()
                .register();
        factory.classMap(TSMuster.class, TSMusterList.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("author", "author_id")
                .field("tCMuster", "tcMuster_id")
                .byDefault()
                .register();
        factory.classMap(TestSuite.class, TestSuiteDTO.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("tcMusters", "tcMusters_id")
                .field("project", "project_id")
                .field("tcMusters", "tcMusters_id")
                .byDefault()
                .register();
        factory.classMap(TestSuite.class, TestSuiteForm.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("tcMusters", "tcMusters_id")
                .field("project", "project_id")
                .field("tcMusters", "tcMusters_id")
                .byDefault()
                .register();
        factory.classMap(TestSuite.class, TestSuiteList.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("tcMusters", "tcMusters_id")
                .field("project", "project_id")
                .field("tcMusters", "tcMusters_id")
                .byDefault()
                .register();
        factory.classMap(TSInstance.class, TSMuster.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .customize((Mapper<TSInstance, TSMuster>) customMappers.get(TSInstance.class, TSMuster.class))
                .byDefault()
                .register();
        factory.classMap(TCInstance.class, TCInstanceRunDTO.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("tCMuster", "tcMusters_id")
                .field("tsInstances", "tsInstances_id")
                .field("id", "tcInstance_id")
                .field("tester", "tester_id")
                .byDefault()
                .register();
        factory.classMap(TSInstance.class, TSInstanceRunDTO.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("tCInstance", "tcInstance_id")
                .field("testerUpdate", "testerUpdate_id")
                .byDefault()
                .register();
        factory.classMap(TSInstance.class, TSInstanceList.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
                .field("tCInstance", "tcInstance_id")
//                .field("testerUpdate", "testerUpdate_id")
                .byDefault()
                .register();
        factory.classMap(TCInstance.class, TCInstanceDTO.class)
                .mapNullsInReverse(false)
                .mapNulls(false)
//                .field("tCInstance", "tcInstance_id")
                .field("tester", "tester_id")
                .field("tCMuster", "tcMuster_id")
                .customize((Mapper<TCInstance, TCInstanceDTO>) customMappers.get(TCInstance.class, TCInstanceDTO.class))
                .byDefault()
                .register();
        factory.classMap(Project.class, ProjectName.class)
                .field("projectOwner.name", "projectOwnerName")
                .field("testSuites", "suiteIdList")
                .customize((Mapper<Project, ProjectName>) customMappers.get(Project.class, ProjectName.class))
                .byDefault()
                .register();
        factory.classMap(WorkList.class, WorkListDTO.class)
                .mapNulls(false).mapNullsInReverse(false)
                .field("project", "project_id")
                .field("author", "author_id")
                .customize((Mapper<WorkList, WorkListDTO>) customMappers.get(WorkList.class, WorkListDTO.class))
                .byDefault()
                .register();
        factory.classMap(WorkList.class, WorkListForm.class)
                .mapNulls(false).mapNullsInReverse(false)
                .field("project", "project_id")
                .field("author", "author_id")
                .customize((Mapper<WorkList, WorkListForm>) customMappers.get(WorkList.class, WorkListForm.class))
                .byDefault()
                .register();
        factory.classMap(WorkList.class, WorkListList.class)
                .mapNulls(false).mapNullsInReverse(false)
                .field("project", "project_id")
                .field("author", "author_id")
                .byDefault()
                .register();
        factory.classMap(WorkTC.class, WorkTCDTO.class)
                .mapNulls(false).mapNullsInReverse(false)
                .field("assignee", "assignee_id")
                .field("tcMuster", "tcMuster_id")
                .field("workList", "workList_id")
                .customize((Mapper<WorkTC, WorkTCDTO>) customMappers.get(WorkTC.class, WorkTCDTO.class))
                .byDefault()
                .register();
        factory.classMap(WorkTC.class, WorkTC.class)
                .mapNulls(false).mapNullsInReverse(false)
                .exclude("assignee")
                .customize((Mapper<WorkTC, WorkTC>) customMappers.get(WorkTC.class, WorkTC.class))
                .byDefault()
                .register();
        factory.classMap(Person.class, Person.class)
                .mapNulls(false).mapNullsInReverse(false)
                .customize((Mapper<Person, Person>) customMappers.get(Person.class, Person.class))
                .register();
        factory.classMap(Project.class, ProjectStatsDTO.class)
                .mapNulls(false).mapNullsInReverse(false)
                .customize((Mapper<Project, ProjectStatsDTO>) customMappers.get(Project.class, ProjectStatsDTO.class))
                .register();
    }
}