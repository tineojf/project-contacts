package main;

import dotenv.ConfigEnv;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Hello World!");
            ConfigEnv configEnv = ConfigEnv.getInstance();

            System.out.println(configEnv.get("DATABASE_NAMEs"));

            System.out.println("Hola");
        } catch (RuntimeException e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
        }
    }
}
