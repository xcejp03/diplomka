package cz.vse.mapping.custom;

import cz.vse.entity.PriorityTCEnum;
import cz.vse.entity.TCMuster;
import cz.vse.entity.WorkTC;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by pcejk on 16.04.2017.
 */
public class TCMusterToWorkTCTest {
    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private MapperFacade mapper = mapperFactory.getMapperFacade();

    private TCMuster tcMuster;
    private TCMuster tcMusterExpected;
    private TCMuster tcMusterResult;

    private WorkTC workTC;

    private WorkTC workTCResult;
    private WorkTC workTCExpected;

    @Before
    public void setUp() throws Exception {

        tcMuster = new TCMuster();
        tcMuster.setName("muster1");
        tcMuster.setId(7890L);
        tcMuster.setNote("note1");
        tcMuster.setPriority(PriorityTCEnum.high);
        tcMuster.setPrerequisite("prerequisite1");

        workTCExpected = new WorkTC();
        workTCExpected.setId(7890L);
        workTCExpected.setPriority(PriorityTCEnum.high);

        workTC = new WorkTC();
        workTC.setPriority(PriorityTCEnum.low);
        workTC.setId(4983L);

        tcMusterExpected = new TCMuster();
        tcMusterExpected.setPriority(PriorityTCEnum.low);
        tcMusterExpected.setId(4983L);
    }

    @Test
    public void mapAtoB() throws Exception {
        workTCResult = mapper.map(tcMuster, WorkTC.class);
        assertEquals(workTCExpected, workTCResult);

    }

    @Test
    public void mapBtoA() throws Exception {
        tcMusterResult = mapper.map(workTC, TCMuster.class);
        assertEquals(tcMusterExpected, tcMusterResult);
    }

}