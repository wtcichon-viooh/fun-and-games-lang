package net.trollheim.lang.impl.visitor;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {
    public static Stream<BigInteger> openFile(String filename) {
        try {
            var f = new File(".");
            return Files.readAllLines(Paths.get(filename)).stream().map(BigInteger::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
