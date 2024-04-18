package view;

import logic.api.API;
import persistence.models.ContactModel;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("Bienvenido a la agenda de contactos");
        System.out.println("1. Listar contactos");
        System.out.println("2. Crear contacto");
        System.out.println("3. Actualizar contacto");
        System.out.println("4. Eliminar contacto");
        System.out.println("5. Salir");

        int option = this.readOption(5);
        this.executeAction(option);
    }

    public int readOption(int limit) {
        int option;
        boolean incorrectOption = true;

        do {
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            if (option >= 1 && option <= limit) {
                incorrectOption = false;
            } else {
                System.out.println("Opción incorrecta, intente de nuevo");
            }

        } while (incorrectOption);

        return option;
    }


    public void executeAction(int option) {
        switch (option) {
            case 1:
                System.out.println("Listar contactos");
                ArrayList<ContactModel> contacts = API.getAllContacts();

                int i = 1;
                for (ContactModel contact : contacts) {
                    System.out.println(i + ". " + contact);
                    i++;
                }
                break;
            case 2:
                System.out.println("Crear contacto");
                break;
            case 3:
                System.out.println("Actualizar contacto");
                break;
            case 4:
                System.out.println("Eliminar contacto");
                break;
            case 5:
                System.out.println("Salir");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
