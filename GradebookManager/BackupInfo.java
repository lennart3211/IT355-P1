import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
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

    /**
     * Checks if the given file matches this backup info.
     *
     * @param file the file to check
     * @return true if the file matches, false otherwise
     * @throws IOException if reading the file attributes fails
     */
    public boolean matches(Path file) throws IOException {
        if (!Files.exists(file)) {
            return false;
        }
        BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
        return size == attributes.size() &&
               modified.equals(attributes.lastModifiedTime()) &&
               fileKey.equals(attributes.fileKey());
    }

    /**
     * Creates a new backup info object from the given file.
     *
     * @param file the file to create the backup info from
     * @return the backup info
     * @throws IOException if reading the file attributes fails
     */
    public static BackupInfo from(Path file) throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
        
        return new BackupInfo( attributes.size(), attributes.lastModifiedTime(), attributes.fileKey());
}
}
