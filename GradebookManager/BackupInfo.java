import java.io.Serializable;
import java.nio.file.attribute.FileTime;

public final class BackupInfo implements Serializable {
    private final long size;
    private final FileTime modified;
    private final Object fileKey;

    public BackupInfo(long size, FileTime modified, Object fileKey) {
        this.size = size;
        this.modified = modified;
        this.fileKey = fileKey;
    }

    public boolean matches(Path file) throws IOException {
        return false;
    }
}
