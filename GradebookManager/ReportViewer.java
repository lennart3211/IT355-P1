public class ReportViewer {
    public enum Viewer { NOTEPAD, LESS, CAT }
    public void open(Path report, Viewer viewer) throws GradebookException {}
    public static List<Viewer> availableViewers() {}
}