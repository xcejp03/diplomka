package cz.vse.mapping.custom;

import cz.vse.entity.Person;
import cz.vse.entity.PriorityTCEnum;
import cz.vse.entity.WorkTC;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pcejk on 17.04.2017.
 */
public class WorkTCToWorkTCTest {
    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private MapperFacade mapper = mapperFactory.getMapperFacade();

    WorkTC workTC1;

    WorkTC workTCExpected;
    WorkTC workTCResult;

    Person assigne;

    @Before
    public void setUp() throws Exception {
        assigne = new Person();
        assigne.setName("PEtr");
        assigne.setId(7628L);
        assigne.setEnabled(true);
        assigne.setPassword("heslo");
        assigne.setUsername("petrr");

        workTC1 = new WorkTC();
        workTC1.setPriority(PriorityTCEnum.low);
        workTC1.setId(4827L);
        workTC1.setAssignee(assigne);


        workTCExpected = new WorkTC();
        workTCExpected.setPriority(PriorityTCEnum.low);
        workTCExpected.setId(4827L);
        workTCExpected.setAssignee(assigne);

    }

    @Test
    public void mapAtoB() throws Exception {
        workTCResult = mapper.map(workTC1, WorkTC.class);
        assertEquals(workTCExpected, workTCResult);

    }

}