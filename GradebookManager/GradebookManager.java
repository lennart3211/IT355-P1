import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;
/**
 * Runs the gradebook program and handles user input.
 */
public class GradebookManager {
    private GradeRepository repo;
    private Gradebook gradebook;
    private final BackupManager backups;
    private final Path backupFile = Path.of("gradebook-backup.ser");
    private final Path backupMetadataFile = Path.of("gradebook-backup.meta");
    private BackupInfo backupInfo;

    /**
     * Creates the gradebook manager.
     */
    public GradebookManager() {
        repo = new GradeRepository();
        gradebook = new Gradebook(repo);
        backups = new BackupManager(repo);
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
                System.out.print("Enter student name: ");
                String searchName = scan.nextLine();
                
                Optional<Student> student = repo.findStudentsByName(searchName);
                
                if (student.isPresent()) {
                    Student foundStudent = student.get();
                    System.out.println("Student found:");
                    System.out.println("Name: " + foundStudent.getName()+ " ID: " + foundStudent.getId());
                    System.out.println("Grades: " + foundStudent.getGrades());
                } else {
                        System.out.println("Student not found.");
                    }
                break;
            case 4:
                System.out.print("Enter student name: ");
                String averageName = scan.nextLine();
                Optional<Student> averageStudent = repo.findStudentsByName(averageName);
                if (averageStudent.isPresent()) {
                    Student foundStudent = averageStudent.get();
                    System.out.println("Grades: " + foundStudent.getGrades());
                    double average = GradeStats.average(foundStudent.getGrades());
                    int highest = GradeStats.highest(foundStudent.getGrades());
                    int lowest = GradeStats.lowest(foundStudent.getGrades());
                    
                    System.out.printf("Average: %.2f%n", average);
                    System.out.println("Highest: " + highest);
                    System.out.println("Lowest: " + lowest);
                } else {
                        System.out.println("Student not found.");
                    }  
                break;
            case 5:
                System.out.print("Enter path to CSV file: ");
                String csvPath = scan.nextLine();
                try {
                    CsvImporter importer = new CsvImporter(repo);
                    int count = importer.importFile(Path.of(csvPath));
                    System.out.println("Imported " + count + " bytes from CSV file.");
                } catch (GradebookException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 6:
                System.out.print("Enter path to report file: ");
                String reportPath = scan.nextLine();
                try {
                    ReportExporter exporter = new ReportExporter(repo);
                    exporter.export(Path.of(reportPath));
                    System.out.println("Report exported to " + reportPath);
                } catch (GradebookException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 7:
                try {
                    backupInfo = backups.createBackup(backupFile);
                    backupInfo.save(backupMetadataFile);

                    System.out.println("Backup saved successfully.");
                } catch (GradebookException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Backup file was saved successfully, but failed to save metadata.");
                }
                break;
            case 8:
                try {
                    if (backupInfo == null) {
                        backupInfo = BackupInfo.load(backupMetadataFile);
                    }

                    int restoredCount = backups.restoreBackup(backupFile, backupInfo).size();

                    System.out.println("Restored " + restoredCount + " students from backup.");
                    
                } catch (GradebookException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Failed to read backup metadata.");
                }
                break;
            case 9:
                System.out.print("Enter path to report file: ");
                String viewPath = scan.nextLine();

                System.out.println("Choose a viewer:");
                System.out.println("1. Notepad");
                System.out.println("2. Less");
                System.out.println("3. Cat");

                try {
                    int viewerChoice = Integer.parseInt(scan.nextLine());

                    ReportViewer.Viewer viewer = null;

                    switch (viewerChoice) {
                        case 1:
                            viewer = ReportViewer.Viewer.NOTEPAD;
                            break;
                        case 2:
                            viewer = ReportViewer.Viewer.LESS;
                            break;
                        case 3:
                            viewer = ReportViewer.Viewer.CAT;
                            break;
                        default:
                            System.out.println("Invalid viewer choice.");
                            break;
                    }

                    if (viewer != null) {
                        ReportViewer reportViewer = new ReportViewer();
                        reportViewer.open(Path.of(viewPath), viewer);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid viewer choice.");
                } catch (GradebookException e) {
                    System.out.println("Could not open the report.");
                }
                break;
            case 10:
                System.out.println("Exiting Gradebook Manager");
                System.out.println("Closing database and releasing resources.");
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
