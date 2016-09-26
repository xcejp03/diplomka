package cz.vse;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by pcejka on 23.09.2016.
 */
@Component
@Transactional("transactionManager")
public class Pokusna {

    @PersistenceContext
    EntityManager em;

    @PersistenceContext(unitName = "tutorialUnit")
    private EntityManager entityManager;

    public void zavolejNejaka() {
        em.toString();
    }
}
