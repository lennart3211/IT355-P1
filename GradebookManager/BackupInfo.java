import java.io.Serializable;

public final class BackupInfo implements Serializable {
    public BackupInfo(long size, FileTime modified, Object fileKey) {}
    
    public boolean matches(Path file) throws IOException {}
}
