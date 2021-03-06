package cz.vse.mapping.custom;

import cz.vse.entity.Person;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by pcejka on 26.11.2016.
 */
@Component
public class PersonToPerson extends CustomMapper<Person, Person> {
    private final Logger l = Logger.getLogger(this.getClass());

    @Override
    public void mapAtoB(Person person, Person person2, MappingContext context) {
        l.info(person + " -> " + person2);

        l.info("hloubka: " + context.getDepth());
    }

}
