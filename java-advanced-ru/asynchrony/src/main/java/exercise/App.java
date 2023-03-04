package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletionException;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String file1, String file2, String resultFile) {

        CompletableFuture<String> file1Future = CompletableFuture.supplyAsync(() -> {
            return readFile(file1);
        });

        CompletableFuture<String> file2Future = CompletableFuture.supplyAsync(() -> {
            return readFile(file2);
        });

         return file1Future.thenCombine(file2Future, (f1, f2) -> {
            String content = f1 + f2;
             try {
                 Files.writeString(getFullPath(resultFile), content);
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
             return content;
         }).exceptionally(ex -> {
             System.out.println("We have an exception " + ex.getMessage());
             return ex.getMessage();
         });
    }

    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        String file1 = "src/main/resources/file1.txt";
        String file2 = "src/main/resources/file2.txt";
        String resultFile = "src/main/resources/result.txt";

        unionFiles(file1, file2, resultFile).get();

        String result = Files.readString(getFullPath(resultFile));
        System.out.println(result);
        // END
    }

    public static String readFile(String filePath) {
        String content = "";
        try {
            content = Files.readString(getFullPath(filePath));
        } catch (IOException e) {
            throw new CompletionException(e);
        }
        return content;
    }

    public static Path getFullPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }
}

