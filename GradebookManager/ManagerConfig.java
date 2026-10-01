public final class AppConfig {
    public static final String DB_URL = "jdbc:sqlite:gradebook.db";
    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 100;
    public static final long AUTOSAVE_INTERVAL_MS = 30_000;
    private AppConfig() {}
}