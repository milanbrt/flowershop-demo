package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    private static final String CONFIG_FILE = "/config/config.properties";

    private static final Config INSTANCE = new Config();

    private final Properties properties = new Properties();

    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());

    private Config() {
        try (InputStream ris = getClass().getResourceAsStream(CONFIG_FILE)) {
            properties.load(ris);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to read config file", ex);
            throw new SocialsException("Unable to load configuration.");
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public static Config getInstance() {
        return INSTANCE;
    }

}
