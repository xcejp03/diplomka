package cz.vse.mapping.custom;

import cz.vse.entity.TSInstance;
import cz.vse.entity.TSMuster;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pcejk on 17.04.2017.
 */
public class TSInstanceToTSMusterTest {
    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private MapperFacade mapper = mapperFactory.getMapperFacade();
    TSMuster tsMuster;

    TSInstance tsInstanceExpected;
    TSInstance tsInstanceResult;


    @Before
    public void setUp() throws Exception {
        tsMuster = new TSMuster();
        tsMuster.setExpected("sdfsdf");
        tsMuster.setId(56254L);
        tsMuster.setAction("fsdtwe");

        tsInstanceExpected = new TSInstance();
        tsInstanceExpected.setExpected("sdfsdf");
        tsInstanceExpected.setId(56254L);
        tsInstanceExpected.setAction("fsdtwe");
        tsInstanceExpected.setTsMuster(tsMuster);

    }

    @Test
    public void mapBtoA() throws Exception {
        tsInstanceResult = mapper.map(tsMuster, TSInstance.class);

        assertEquals(tsInstanceExpected, tsInstanceResult);

    }

}