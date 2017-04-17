package cz.vse.utils;

import org.apache.log4j.Logger;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class StartupHousekeeper {
    private final Logger l = Logger.getLogger(this.getClass());

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        System.out.println("OOOOOOOO po staru");
        l.info("QQOOOOOOOOOQQQQQ - with log4j");
        playSoundAfterStart();
        l.info(".");
        l.info(".");
    }

    public void playSoundAfterStart() {
        l.info("prehravam zvuk");
        final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        if (runnable != null) runnable.run();
    }
}