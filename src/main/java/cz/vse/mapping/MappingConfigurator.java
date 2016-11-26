package cz.vse.mapping;

import cz.vse.dto.*;
import cz.vse.entity.*;
import cz.vse.mapping.converter.DateConverter;
import cz.vse.mapping.custom.TSMusterToTSInstance;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 10.10.2016.
 */
@Component
public class MappingConfigurator extends ConfigurableMapper {   // implements ApplicationContextAware {
    //    public MappingConfigurator()    {
//        super(false);
//    }
//

//    private ApplicationContext applicationContext;

    private CustomMapperMap customMappers;      //asi bude potřeba

/*    @Override
    protected void configure(final MapperFactory factory) {
        this.factory = factory;
        configureConverters(applicationContext);
        configureCustomMappers();
    }*/

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//
//    }

    protected void configure(MapperFactory factory) {
        ConverterFactory converterFactory = factory.getConverterFactory();
        converterFactory.registerConverter("myDateConverter", new DateConverter());
        factory.getConverterFactory().registerConverter(new DateConverter());
        TSMusterToTSInstance customMapper = new TSMusterToTSInstance();

        factory.classMap(Project.class, ProjectDTO.class)
                .field("projectOwner.id", "projectOwner_id")
                .field("personMembers{id}", "projectMembers_id{}")
                .field("testSuites{id}", "suites_id{}")
                .field("tcMusters{id}", "tcMusters_id{}")
                .byDefault()
                .register();
        factory.classMap(ProjectDTO.class, Project.class)
                .field("projectOwner_id", "projectOwner.id")
                .field("projectMembers_id{}", "personMembers{id}")
                .field("suites_id{}", "testSuites{id}")
                .field("tcMusters_id{}", "tcMusters{id}")
                .byDefault()
                .register();
        factory.classMap(DefectCommentDTO.class, DefectComment.class)
                .field("defect_id", "defect.id")
                .field("author_id", "author.id")
                .fieldMap("createdDateTime", "createdDateTime").converter("DateConverter").add()
                .field("createdDateTime", "createdDateTime")
                .byDefault()
                .register();
        factory.classMap(DefectComment.class, DefectCommentDTO.class)
                .field("defect.id", "defect_id")
                .field("author.id", "author_id")
                .byDefault()
                .register();
        factory.classMap(Defect.class, DefectDTO.class)
                .byDefault()
                .register();
        factory.classMap(DefectDTO.class, Defect.class)
                .byDefault()
                .register();
        factory.classMap(Person.class, PersonDTO.class)
                .byDefault()
                .register();
        factory.classMap(TSMuster.class, TSMusterDTO.class)
                .field("author.id", "author_id")
                .field("tcMuster.id", "tcMuster_id")
                .byDefault()
                .register();
        factory.classMap(TSMusterDTO.class, TSMuster.class)
                .field("author_id", "author.id")
                .field("tcMuster_id", "tcMuster.id")
                .byDefault()
                .register();
        factory.classMap(TCMuster.class, TCMusterDTO.class)
                .field("project.id", "project_id")
                .field("tsMusters{id}", "tsMusters_id{}")
                .field("tcInstances{id}", "tcInstances_id{}")
//                .fieldMap("createdDateTime", "createdDateTime").converter("DateConverter").add()
//                .field("createdDateTime", "createdDateTime")
                .byDefault()
                .register();
        factory.classMap(TCMusterDTO.class, TCMuster.class)
                .field("project_id", "project.id")
                .field("tsMusters_id{}", "tsMusters{id}")
                .field("tcInstances_id{}", "tcInstances{id}")
//                .fieldMap("createdDateTime", "createdDateTime").converter("DateConverter").add()
//                .field("createdDateTime", "createdDateTime")
                .byDefault()
                .register();
        factory.classMap(TestSuite.class, TestSuiteDTO.class)
                .field("project.id", "project_id")
                .field("tcMusters{id}", "tcMusters_id{}")
                .byDefault()
                .register();
        factory.classMap(TestSuiteDTO.class, TestSuite.class)
                .field("project_id", "project.id")
                .field("tcMusters_id{}", "tcMusters{id}")
                // datum?
                .byDefault()
                .register();
        factory.classMap(TSMuster.class, TSInstance.class)
                .field("action", "action")
                .field("expected", "expected")
                .customize((Mapper) customMapper)
//                .fieldMap("action", "tsMuster").converter("TSMusterConverter").add()
//                .field("tsMuster")
//        .exclude("tsMuster")
                .register();
        factory.classMap(TCInstance.class, TCInstanceRunDTO.class)
                .field("id", "id")
                .field("name", "name")
                .field("tcMuster.id", "tcMusters_id")
                .field("tsInstances{id}", "tsInstances_id{}")
                .field("id", "tcInstance_id")
                .register();
    }

    //   private void configureClassMaps() {
/*        factory.classMap(Academy.class, AcademyForm.class)
                .field("company", "companyId")
                .field("guarantor", "guarantorId")
                .customize((Mapper<Academy, AcademyForm>) customMappers.get(Academy.class, AcademyForm.class))
                .byDefault()
                .register();

        factory.classMap(LessonParticipation.class, LessonRatingDTO.class)
                .field("lesson.date", "date")
                .field("rating.rating", "rating")
                .field("rating.verbalRating", "verbalRating")
                .byDefault()
                .register();*/

//        factory.classMap(Project.class, ProjectDTO.class)
//                .field("name", "name")
////                .field("company", "companyId")
////                .field("guarantor", "guarantorId")
//                .byDefault()
//                .register();
//
//    }

}
