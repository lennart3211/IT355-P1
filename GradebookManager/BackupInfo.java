import java.io.Serializable;
import java.nio.file.attribute.FileTime;

public final class BackupInfo implements Serializable {
    private final long size;
    private final FileTime modified;
    private final Object fileKey;

    /**
     * Creates a new backup info object.
     *
     * @param size     the size of the file
     * @param modified the last modified time of the file
     * @param fileKey  the file key of the file
     */
    public BackupInfo(long size, FileTime modified, Object fileKey) {
        this.size = size;
        this.modified = modified;
        this.fileKey = fileKey;
    }

    public boolean matches(Path file) throws IOException {
        return false;
    }
}
