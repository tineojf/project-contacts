package dotenv;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigEnv {
    private static final ConfigEnv instance = new ConfigEnv();
    private final Dotenv dotenv;

    private ConfigEnv() {
        dotenv = Dotenv.configure().directory("./config").load();
    }

    public static ConfigEnv getInstance() {
        return instance;
    }

    public String get(String key) throws RuntimeException {
        if (dotenv.get(key) == null) {
            throw new RuntimeException("Key not found " + key);
        } else {
            return dotenv.get(key);
        }
    }
}
