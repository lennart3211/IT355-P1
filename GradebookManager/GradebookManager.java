import java.util.Scanner;

/**
 * Runs the gradebook program and handles user input.
 */
public class GradebookManager {
    private GradeRepository repo;
    private Gradebook gradebook;

    /**
     * Creates the gradebook manager.
     */
    public GradebookManager() {
        repo = new GradeRepository();
        gradebook = new Gradebook(repo);
    }

    /**
     * Runs the gradebook menu.
     */
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
                System.out.print("Enter student name: ");
                String name = scan.nextLine();

                try {
                    gradebook.addStudent(name);
                    System.out.println("Student added!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                System.out.print("Enter student name: ");
                String studentName = scan.nextLine();

                System.out.print("Enter grade: ");
                String gradeInput = scan.nextLine();

                try {
                    float grade = Float.parseFloat(gradeInput);
                    gradebook.addGrade(studentName, grade);
                    System.out.println("Grade added!");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid grade.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
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
        // EXP52-J: Use braces for the body of if, for, or while statements.
        if (repo != null) {
            // FIO14-J: Perform proper cleanup before the program terminates.
            repo.close();
        }
    }

    public static void main(String[] args) {
        GradebookManager manager = new GradebookManager();
        manager.run();
        manager.quit();
    }
}