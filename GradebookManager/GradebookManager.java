import java.util.Scanner;

public class GradebookManager {
    public void run() {
        Scanner scan = new Scanner(System.in);
        String input;
        int choice = 0;
        String menu = "Menu:\n" +
        "  1. Add Student\n" +
        "  2. Add Grade\n" +
        "  3. Search Student\n" +
        "  4. Show Average\n" +
        "  5. Import grades\n" +
        "  6. Export report\n" +
        "  7. Back up gradebook\n" +
        "  8. Restore from backup\n" +
        "  9. View report\n" +
        "  10. Quit\n";
    
        do {
            System.out.println(menu);
            input = scan.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = 0;
            }

            switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                System.out.println("Invalid Input");
            }
        } while (choice != 10);
    }

    public void quit() {

    }

    public static void main(String[] args) {
        GradebookManager manager = new GradebookManager();
        manager.run();
        manager.quit();
    }
}