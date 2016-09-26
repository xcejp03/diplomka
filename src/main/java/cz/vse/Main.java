package cz.vse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pcejka on 20.09.2016.
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("/ApplicationContext.xml");
        System.exit(1);

//        PopulateDatabase pd = (PopulateDatabase) context.getBean("populateDatabase");
//        pd.createTestingData();
    }

}
