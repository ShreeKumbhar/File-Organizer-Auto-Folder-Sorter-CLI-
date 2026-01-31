import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileOrganizer {

    private static Map<String, String> extensionMap = new HashMap<>();
    private static boolean enableLogging = false;
    private static boolean recursive = false;
    private static String logPath = "logs/log.txt";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("--help")) {
            printHelp();
            return;
        }

        String directory = args[0];
        for (String arg : args) {
            if (arg.equals("--log"))
                enableLogging = true;
            if (arg.equals("--recursive"))
                recursive = true;
        }

        initializeExtensions();

        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║      File Organizer CLI Tool v1.0          ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        System.out.println("Directory: " + directory);
        System.out.println("Recursive: " + (recursive ? "Yes" : "No"));
        System.out.println("Logging:   " + (enableLogging ? "Yes" : "No"));
        System.out.println("\nStarting organization...\n");

        try {
            organizeFiles(new File(directory), new File(directory));
            System.out.println("\n✓ Organization complete!");
            if (enableLogging)
                System.out.println("✓ Log saved to: " + logPath);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void initializeExtensions() {
        extensionMap.put("jpg", "Images");
        extensionMap.put("jpeg", "Images");
        extensionMap.put("png", "Images");
        extensionMap.put("gif", "Images");
        extensionMap.put("bmp", "Images");
        extensionMap.put("svg", "Images");

        extensionMap.put("pdf", "Documents");
        extensionMap.put("doc", "Documents");
        extensionMap.put("docx", "Documents");
        extensionMap.put("txt", "Documents");
        extensionMap.put("xls", "Documents");
        extensionMap.put("xlsx", "Documents");
        extensionMap.put("ppt", "Documents");
        extensionMap.put("pptx", "Documents");

        extensionMap.put("mp3", "Music");
        extensionMap.put("wav", "Music");
        extensionMap.put("flac", "Music");
        extensionMap.put("aac", "Music");

        extensionMap.put("mp4", "Videos");
        extensionMap.put("avi", "Videos");
        extensionMap.put("mkv", "Videos");
        extensionMap.put("mov", "Videos");

        extensionMap.put("zip", "Archives");
        extensionMap.put("rar", "Archives");
        extensionMap.put("7z", "Archives");
        extensionMap.put("tar", "Archives");
        extensionMap.put("gz", "Archives");

        extensionMap.put("java", "Code");
        extensionMap.put("py", "Code");
        extensionMap.put("js", "Code");
        extensionMap.put("html", "Code");
        extensionMap.put("css", "Code");
        extensionMap.put("cpp", "Code");
        extensionMap.put("c", "Code");
    }

    private static void organizeFiles(File current, File root) {
        File[] files = current.listFiles();
        if (files == null)
            return;

        for (File file : files) {
            if (file.getName().startsWith(".") || file.getName().equals("logs"))
                continue;

            if (file.isDirectory()) {
                if (recursive && !isCreatedFolder(file, root)) {
                    organizeFiles(file, root);
                }
            } else {
                moveFile(file, root);
            }
        }
    }

    private static boolean isCreatedFolder(File folder, File root) {
        return folder.getParentFile().equals(root) &&
                extensionMap.containsValue(folder.getName());
    }

    private static void moveFile(File file, File root) {
        try {
            String extension = getExtension(file.getName());
            String category = extensionMap.getOrDefault(extension, "Others");

            File categoryFolder = new File(root, category);
            if (!categoryFolder.exists()) {
                categoryFolder.mkdir();
            }

            Path source = file.toPath();
            Path target = new File(categoryFolder, file.getName()).toPath();

            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

            System.out.println(file.getName() + " → " + category);
            log(file.getName() + " → " + category);

        } catch (Exception e) {
            System.err.println("Error moving " + file.getName() + ": " + e.getMessage());
        }
    }

    private static String getExtension(String filename) {
        int dot = filename.lastIndexOf('.');
        return (dot > 0) ? filename.substring(dot + 1).toLowerCase() : "";
    }

    private static void log(String message) {
        if (!enableLogging)
            return;

        try {
            File logFile = new File(logPath);
            logFile.getParentFile().mkdirs();

            String timestamp = LocalDateTime.now().format(formatter);
            String entry = "[" + timestamp + "] " + message + "\n";

            Files.write(logFile.toPath(), entry.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.err.println("Log error: " + e.getMessage());
        }
    }

    private static void printHelp() {
        System.out.println("Usage: java FileOrganizer <directory> [--recursive] [--log]");
        System.out.println("\nOptions:");
        System.out.println("  --recursive  Scan subdirectories");
        System.out.println("  --log        Enable logging to logs/log.txt");
        System.out.println("\nExample:");
        System.out.println("  java FileOrganizer /Users/shree/Downloads --log");
    }
}
