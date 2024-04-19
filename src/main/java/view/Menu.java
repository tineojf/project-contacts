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
                System.out.println("--------------------");
                System.out.println("Listar contactos");
                ArrayList<ContactModel> contacts = API.get();

                int i = 1;
                for (ContactModel contact : contacts) {
                    System.out.println(i + ". " + contact);
                    i++;
                }
                break;
            case 2:
                System.out.println("--------------------");
                System.out.println("Crear contacto");

                ContactModel contact = inputContactData();
                API.post(contact);
                break;
            case 3:
                System.out.println("--------------------");
                System.out.println("Actualizar contacto");

                System.out.print("ID del contacto a actualizar: ");
                int idUpdate = scanner.nextInt();

                ContactModel contactUpdate = inputContactData();
                API.put(idUpdate, contactUpdate);
                break;
            case 4:
                System.out.println("--------------------");
                System.out.println("Eliminar contacto");

                System.out.print("ID del contacto a eliminar: ");
                int id = scanner.nextInt();
                API.delete(id);
                break;
            case 5:
                System.out.println("Salir");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    public ContactModel inputContactData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Apellido: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();

        return new ContactModel(1, name, lastName, email, phone);
    }
}
