package test.java.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty extends Properties {

        private static ReadProperty instance = null;

        private ReadProperty() {
        }

        public static ReadProperty getInstance() {
            if (instance == null) {
                try {
                    instance = new ReadProperty();
                    System.out.println(System.getProperty("env"));
                    FileInputStream in = new FileInputStream("src/test/resources/"+System.getProperty("env")+"/test.properties");
                    instance.load(in);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return instance;
        }
    }
