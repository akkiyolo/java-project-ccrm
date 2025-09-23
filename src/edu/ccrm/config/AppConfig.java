package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Singleton AppConfig - demonstrates Singleton pattern.
 */
public final class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private final Path dataFolder;
    private final DateTimeFormatter tsFormatter;
    private final int maxCreditsPerSemester;

    private AppConfig() {
        this.dataFolder = Paths.get(System.getProperty("user.home"), "ccrm-data");
        this.tsFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        this.maxCreditsPerSemester = 24; // business rule
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public Path getDataFolder() {
        return dataFolder;
    }

    public DateTimeFormatter getTsFormatter() {
        return tsFormatter;
    }

    public String timestamp() {
        return LocalDateTime.now().format(tsFormatter);
    }

    public int getMaxCreditsPerSemester() {
        return maxCreditsPerSemester;
    }
}
