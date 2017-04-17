package cz.vse.utils;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by pcejka on 04.12.2016.
 */
public class DiplomkaUtils {

    private static String activeProfiles;


    @Value("${spring.profiles.active}")
    public void setActiveProfiles(String activeProfiles) {
        DiplomkaUtils.activeProfiles = activeProfiles;
    }

    public static boolean isDev() {
        return activeProfiles.equals("dev");
    }
}
