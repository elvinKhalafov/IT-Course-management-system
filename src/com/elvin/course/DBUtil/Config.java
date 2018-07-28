

package com.elvin.course.DBUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Khal_vh04
 */
public class Config {
    private static Properties properties;
    static{
        try{
            properties=new Properties();
            properties.load(new FileInputStream("app.properties"));
            
        }catch(IOException e){
            e.printStackTrace();
            
        }
    }
    public static String getUsername(){
        return properties.getProperty("username");
    }
    public static String getPassword(){
        return properties.getProperty("password");
    }
}
