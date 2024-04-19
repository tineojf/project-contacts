import view.Menu;


public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Project: Agenda de contactos");
            Menu menu = new Menu();
            menu.showMenu();

        } catch (RuntimeException e) {
            System.err.println("Error app: " + e.getMessage());
        }
    }
}