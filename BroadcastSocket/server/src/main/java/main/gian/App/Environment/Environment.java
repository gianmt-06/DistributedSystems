package main.gian.App.Environment;

import java.io.FileInputStream;
import java.util.Properties;

public class Environment {
    private static Environment instance;
    public int PORT;
    public int AMOUNT_CLIENTS;
    
    private Environment(){
        loadVariables();
    }

    private String getPathProperties(){
        return "server/conf/server.properties";
    }

    private void loadVariables(){
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(getPathProperties())) {
            properties.load(fileInputStream);

            this.PORT = Integer.parseInt(properties.get("PORT").toString());
            this.AMOUNT_CLIENTS = Integer.parseInt(properties.get("AMOUNT_CLIENTS").toString());
            
        } catch (Exception exception) {
            System.out.println(exception);
            System.out.println("error al leer properties");
        }
    }

    public static Environment getInstance() {
        if (Environment.instance == null) Environment.instance = new Environment();

        return Environment.instance;
    }
}
