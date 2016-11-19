package cz.vse.mapping;

import cz.vse.dto.*;
import cz.vse.entity.*;
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

//    private CustomMapperMap customMappers;      //asi bude potřeba

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
        converterFactory.registerConverter("myDateConverter",new DateConverter());
        factory.getConverterFactory().registerConverter(new DateConverter());
        factory.classMap(Project.class, ProjectDTO.class)
                .field("projectOwner.id", "projectOwner_id")
                .field("personMembers{id}", "projectMembers_id{}")
                .byDefault()
                .register();
        factory.classMap(ProjectDTO.class, Project.class)
                .field("projectOwner_id", "projectOwner.id")
                .field("projectMembers_id{}", "personMembers{id}")
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
                .byDefault()
                .register();
        factory.classMap(TSMusterDTO.class, TSMuster.class)
                .field("author_id", "author.id")
                .byDefault()
                .register();
        factory.classMap(TCMuster.class, TCMusterDTO.class)
                .field("project.id", "project_id")
                .exclude("createdDateTime")
                .byDefault()
                .register();
        factory.classMap(TCMusterDTO.class, TCMuster.class)
                .field("project_id", "project.id")
                .exclude("createdDateTime")
                .byDefault()
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
