package me.hao0.basic.properties;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Author: haolin
 * On: 3/6/15
 */
public class PropertiesChineseTest {

    public static void main(String[] args) throws IOException {
        System.out.println("current charset: " + Charset.defaultCharset().name());

        Properties props = new Properties();
        props.load(PropertiesChineseTest.class.getClassLoader().getResourceAsStream("test.properties"));
        printProps(props);

        props = new Properties();
        props.load(new InputStreamReader(PropertiesChineseTest.class.getClassLoader().getResourceAsStream("test.properties")));
        printProps(props);
    }

    private static void printProps(Properties props) {
        System.out.println(props.get("app"));
        System.out.println(props.get("name"));
        System.out.println(props.get("location"));
    }
}
