package org.thiki.lark.foundation.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by xubt on 4/19/16.
 */
public class EnvProperties {

    public static void loadPro() {
        //InputStream in = Object.class.getResourceAsStream("/pirate.properties");
        Properties properties = new Properties();
        InputStream in = properties.getClass().getClassLoader().getResourceAsStream("pirate.properties");
        try {
            properties.load(in);

            System.out.println("```````````````````````````````````````````````````");
            System.out.println(properties.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
