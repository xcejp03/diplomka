package cz.vse.dao.impl;

import cz.vse.dao.DefectDao;
import cz.vse.entity.Defect;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
@Repository
public class DefectDaoImpl implements DefectDao {
    private final Logger l = Logger.getLogger(this.getClass());
    @Override
    public void addDefect(Defect defect) {

    }

    @Override
    public void deleteDefect(Defect defect) {

    }

    @Override
    public void updateDefect(Defect defect) {

    }

    @Override
    public List<Defect> getAllDefects() {
        return null;
    }

    @Override
    public Defect getDefectById(long id) {
        return null;
    }
}
