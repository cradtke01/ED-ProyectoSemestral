package dci.ufro.cl.ps.services.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    /**
     * Reads a plain text file given the files relative path.
     *
     * @param filePath
     * @return fileContent
     */
    public static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            System.out.println("File could not be read.");
            return "";
        }
    }

    /**
     * Overwrites a plain text file content given the file's relative path and new content.
     *
     * @param filePath
     * @param fileContent
     */
    public static void writeFile(String filePath, String fileContent) {
        try {
            Files.write(Paths.get(filePath), fileContent.getBytes());
        } catch (Exception e) {
            System.out.println("File could not be written.");
        }
    }

    /**
     * Deletes a file given it's relative path.
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        if (new File(filePath).delete()) {
        } else System.out.println("File could not be deleted.");
    }
}