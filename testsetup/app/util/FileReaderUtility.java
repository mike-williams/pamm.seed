package util;

import play.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderUtility {

    private final Logger.ALogger logger = Logger.of(this.getClass());
    public String readFile(String fileName) throws IOException {
        try (
                final BufferedReader br = new BufferedReader(new FileReader(fileName))
        ) {
            final StringBuilder sb = new StringBuilder();
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
