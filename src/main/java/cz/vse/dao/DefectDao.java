package cz.vse.dao;

import cz.vse.entity.Defect;
import cz.vse.entity.Person;

import java.util.List;

/**
 * Created by pcejka on 03.10.2016.
 */
public interface DefectDao {

    public void addDefect(Defect defect);       //vrací ID uložené osoby nebo -1 při chybě
    public void deleteDefect(Defect defect);        //vrací true při úspěchu, false při chybě
    public void updateDefect(Defect defect);        //vrací true při úspěchu, false při chybě
    public List<Defect> getAllDefects();
    public Defect getDefectById(long id);
}
