package cz.vse.mapping;

import cz.vse.dto.TestProjectDTO;
import cz.vse.entity.TestProject;
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
        factory.classMap(TestProject.class, TestProjectDTO.class)
                .field("projectOwner.id", "projectOwner_id")
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

//        factory.classMap(TestProject.class, TestProjectDTO.class)
//                .field("name", "name")
////                .field("company", "companyId")
////                .field("guarantor", "guarantorId")
//                .byDefault()
//                .register();
//
//    }

}
