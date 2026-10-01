public class BackupManager {
    public BackupManager(GradeRepository repo) {}
    public BackupInfo createBackup(Path file) throws GradebookException {}
    public List<Student> restoreBackup(Path file, BackupInfo expected) throws GradebookException {}
}

public final class BackupInfo implements Serializable {
    public BackupInfo(long size, FileTime modified, Object fileKey) {}
    public boolean matches(Path file) throws IOException {}
}