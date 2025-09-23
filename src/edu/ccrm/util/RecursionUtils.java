package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * Recursive utilities: print directory tree with max depth.
 */
public class RecursionUtils {
    public static void printTree(Path root, int maxDepth) throws IOException {
        try (Stream<Path> walk = Files.walk(root, maxDepth)) {
            walk.forEach(p -> System.out.println(root.relativize(p).toString()));
        }
    }
}
