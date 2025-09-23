package edu.ccrm.io;

import edu.ccrm.config.AppConfig;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Backup service copying exported files into timestamped backup folder.
 */
public class BackupService {
    private final AppConfig cfg = AppConfig.getInstance();

    public Path backupFolder(Path sourceFolder) throws IOException {
        String ts = cfg.timestamp();
        Path backupRoot = cfg.getDataFolder().resolve("backups");
        if (!Files.exists(backupRoot)) Files.createDirectories(backupRoot);
        Path dest = backupRoot.resolve("backup-" + ts);
        Files.createDirectories(dest);

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(sourceFolder)) {
            for (Path p : ds) {
                if (Files.isRegularFile(p)) {
                    Path dst = dest.resolve(p.getFileName());
                    Files.copy(p, dst, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        return dest;
    }

    public long computeSizeRecursive(Path folder) throws IOException {
        final long[] total = {0};
        Files.walkFileTree(folder, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                total[0] += attrs.size();
                return FileVisitResult.CONTINUE;
            }
        });
        return total[0];
    }
}
