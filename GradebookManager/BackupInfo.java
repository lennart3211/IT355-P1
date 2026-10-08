import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.Objects;

public final class BackupInfo {
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
               modified.equals(attributes.lastModifiedTime())
               && (fileKey == null || Objects.equals(fileKey, attributes.fileKey()));
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

    /**
     * Saves this backup info to the given file.
     *
     * @param metadataFile the file to save the backup info to
     * @throws IOException if writing to the file fails
     */
    public void save(Path metadataFile) throws IOException {
        Instant timestamp = modified.toInstant();

        try (DataOutputStream output = new DataOutputStream(Files.newOutputStream(metadataFile))) {
            output.writeLong(size);
            output.writeLong(timestamp.getEpochSecond());
            output.writeInt(timestamp.getNano());
        }
    }

    /**
     * Loads a backup info object from the given file.
     *
     * @param metadataFile the file to load the backup info from
     * @return the backup info
     * @throws IOException if reading the file fails
     */
    public static BackupInfo load(Path metadataFile) throws IOException {
        try (DataInputStream input = new DataInputStream(Files.newInputStream(metadataFile))) {
            long savedSize = input.readLong();
            long seconds = input.readLong();
            int nanos = input.readInt();

            if (savedSize < 0 || nanos < 0 || nanos > 999_999_999) {
                throw new IOException("Invalid backup info data");
            }

            FileTime savedModified = FileTime.from(Instant.ofEpochSecond(seconds, nanos));

            return new BackupInfo(savedSize, savedModified, null);
        } catch (DateTimeException e) {
            throw new IOException("Invalid backup info data", e);
        }
    }
}
