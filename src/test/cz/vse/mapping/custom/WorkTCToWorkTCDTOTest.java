package cz.vse.mapping.custom;

import cz.vse.dto.WorkTCDTO;
import cz.vse.entity.Person;
import cz.vse.entity.PriorityTCEnum;
import cz.vse.entity.WorkTC;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pcejk on 17.04.2017.
 */
public class WorkTCToWorkTCDTOTest {
    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private MapperFacade mapper = mapperFactory.getMapperFacade();

    WorkTC workTC;

    WorkTCDTO workTCDTOExpected;
    WorkTCDTO workTCDTOResult;

    Person assigne;

    @Before
    public void setUp() throws Exception {
        assigne = new Person();
        assigne.setName("PEtr");
        assigne.setId(7628L);
        assigne.setEnabled(true);
        assigne.setPassword("heslo");
        assigne.setUsername("petrr");

        workTC = new WorkTC();
        workTC.setPriority(PriorityTCEnum.low);
        workTC.setId(4827L);
        workTC.setAssignee(assigne);

        workTCDTOExpected = new WorkTCDTO();
        workTCDTOExpected.setPriority(PriorityTCEnum.low);
        workTCDTOExpected.setId(4827L);
        workTCDTOExpected.setAssignee_id(assigne.getId());

    }

    @Test
    public void mapAtoB() throws Exception {
        workTCDTOResult = mapper.map(workTC, WorkTCDTO.class);
        assertEquals(workTCDTOExpected, workTCDTOResult);

    }

}