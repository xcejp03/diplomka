package cz.vse.mapping.custom;

import cz.vse.dto.TCInstanceDTO;
import cz.vse.entity.StatusEnum;
import cz.vse.entity.TCInstance;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pcejk on 15.04.2017.
 */
public class TCInstanceToTCInstanceDTOTest {
    private final Logger l = Logger.getLogger(this.getClass());
    TCInstance tcInstance;
    TCInstanceDTO tcInstanceDTO;
    TCInstanceDTO tcInstanceDTOExpected;

    TCInstanceDTO tcInstanceDTOResult;
    TCInstance tcInstanceResult;

    @Before
    public void setUp() throws Exception {
        TCInstance tcInstance = new TCInstance();
        tcInstance.setName("name2");
        tcInstance.setId(2550L);
        tcInstance.setNote("note2");
        tcInstance.setStatus(StatusEnum.PASSED);

        TCInstanceDTO tcInstanceDTOExpected = new TCInstanceDTO();
        tcInstanceDTOExpected.setName("name2");
        tcInstanceDTOExpected.setId(2550L);
        tcInstanceDTOExpected.setNote("note2");
        tcInstanceDTOExpected.setStatus(StatusEnum.PASSED);


        tcInstance.setName("name1");
        tcInstance.setId(1550L);
        tcInstance.setNote("note1");
        tcInstance.setStatus(StatusEnum.FAILED);

        TCInstance tcInstanceExpected = new TCInstance();
        tcInstanceExpected.setName("name1");
        tcInstanceExpected.setId(1550L);
        tcInstanceExpected.setNote("note1");
        tcInstanceExpected.setStatus(StatusEnum.FAILED);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void mapAtoB() throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        tcInstanceDTOResult = mapper.map(tcInstance, TCInstanceDTO.class);

        assertEquals(tcInstanceDTOExpected, tcInstanceDTOResult);
    }

    @Test
    public void mapBtoA() throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        tcInstanceResult = mapper.map(tcInstanceDTO, TCInstance.class);

        assertEquals(tcInstanceDTOExpected, tcInstanceDTOResult);
    }

}