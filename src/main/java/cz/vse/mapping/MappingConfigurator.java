package cz.vse.mapping;

import cz.vse.dto.DefectCommentDTO;
import cz.vse.dto.DefectDTO;
import cz.vse.dto.PersonDTO;
import cz.vse.dto.ProjectDTO;
import cz.vse.entity.Defect;
import cz.vse.entity.DefectComment;
import cz.vse.entity.Person;
import cz.vse.entity.Project;
import ma.glasnost.orika.MapperFactory;
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

//    private MapperFactory factory;

//    private ApplicationContext applicationContext;
//
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
