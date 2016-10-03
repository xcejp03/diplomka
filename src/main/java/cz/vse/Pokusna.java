//package cz.vse;
//
//import cz.vse.dao.PersonDao;
//import cz.vse.entity.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.time.LocalDateTime;
//
///**
// * Created by pcejka on 23.09.2016.
// */
//@Component
//@Transactional("transactionManager")
//public class Pokusna {
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Autowired
//    private PersonDao personDao;
//
//    public void zavolejNejaka() {
//        em.toString();
//    }
//
//    public void delej() {
//        Person person = new Person();
//        person.setName(LocalDateTime.now().toString());
//        person.setCreatedDate(LocalDateTime.now());
//        person.setLogin("sda");
////        em.persist(person);
//        personDao.savePerson(person);
//    }
//}
