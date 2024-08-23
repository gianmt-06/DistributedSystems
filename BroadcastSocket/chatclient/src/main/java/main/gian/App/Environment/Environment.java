package main.gian.App.Environment;

import java.io.FileInputStream;
import java.util.Properties;

public class Environment {
    private static Environment instance;
    public String HOST;
    public int PORT;
    
    private Environment(){
        loadVariables();
    }

    private String getPathProperties(){
        return "conf/client.properties";
    }

    private void loadVariables(){
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(getPathProperties())) {
            properties.load(fileInputStream);

            this.HOST = properties.get("HOST").toString();
            this.PORT = Integer.parseInt(properties.get("PORT").toString());
            
        } catch (Exception exception) {
            //Logger.getLogger("Server").log(Level.WARNING, exception.getMessage(), exception);
            System.out.println("error al leer properties");
        }
    }

    public static Environment getInstance() {
        if (Environment.instance == null) Environment.instance = new Environment();
        return Environment.instance;
    }
}

