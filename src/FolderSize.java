
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FolderSize {
    public static String input;
    public static String inputFolderPath;
    public static String FOLDERPATH_REGEX = "\\/?.+\\/?.+";
    public static double mg;
    public static double gg;
    public static String s;
    public static String contentFile;
    public static double sumKb;
    public static double size;
    public static Path path;
    public static double step = 1024;

    public static void readFolderPath(){
        System.out.println("Enter folder path: ");
        input = new Scanner(System.in).nextLine();
        if (input.matches(FOLDERPATH_REGEX)){
            inputFolderPath = input;
        } else {
            System.err.println("Folder path error!");
            readFolderPath();
        }
    }

    public static void calculateFolderSize() {
        System.out.println("Enter folder path: ");
        input = new Scanner(System.in).nextLine();
        if (input.matches(FOLDERPATH_REGEX)) {
            inputFolderPath = input;
            path = Paths.get(inputFolderPath);
            try {
                size = Files.walk(path).filter(p -> p.toFile().isFile()).mapToDouble(p -> p.toFile().length()).sum();
                convertMbtoGb();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Error");
        }
    }

    public static void convertMbtoGb() {

        if (size > 1073741824) {
            size = size / step;
            System.out.println(path + "  Size: " + size + " Gb");
        } else if (size > 1048576 && size < 1073741824) {
            size = size / step;
            System.out.println(path + "  Size: " + size + " Mb");
        } else if (size > step) {
            size = size / step;
            System.out.println(path + "  Size: " + size + " Kb");
        } else {
            System.out.println(path + "  Size: " + size + " bytes");
        }
    }

    public static void copyFolder() {
        System.out.println("Enter folder path: ");
        input = new Scanner(System.in).nextLine();
        if (input.matches(FOLDERPATH_REGEX)) {
            inputFolderPath = input;
        } else {
            System.err.println("Folder path error!");
            readFolderPath();
        }
        try {
            System.out.print("Enter path for copy file: " + "\n");
            String path = new Scanner(System.in).nextLine();
            Path newPath = Path.of(path);
            Path copyPath = Path.of(inputFolderPath);
            Files.walk(copyPath).forEach(file -> {
                try {
                    Files.copy(file, newPath.resolve(copyPath.relativize(file)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void walk() {
        System.out.println("Enter folder path: ");
        input = new Scanner(System.in).nextLine();
        if (input.matches(FOLDERPATH_REGEX)) {
            inputFolderPath = input;
        } else {
            System.err.println("Folder path error!");
            readFolderPath();
        }
    }
}
