package dotenv;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigEnv {
    private static final ConfigEnv instance = new ConfigEnv();
    public Dotenv dotenv;

    private ConfigEnv() {
        dotenv = Dotenv.configure().directory("./config").load();
    }

    public static ConfigEnv getInstance() {
        return instance;
    }

    public String get(String key) {
        return dotenv.get(key);
    }
}
