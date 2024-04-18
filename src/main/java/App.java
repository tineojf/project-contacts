import view.Menu;


public class App {
    public static void main(String[] args) {
        try {
            Menu menu = new Menu();
            menu.showMenu();

        } catch (RuntimeException e) {
            System.err.println("Error app: " + e.getMessage());
        }
    }
}