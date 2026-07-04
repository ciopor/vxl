package io.github.ciopor.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

public class ResourceLoader {
    public ResourceLoader() {};
    
    public static String loadText(String path) throws IOException {
        try (InputStream in = ResourceLoader.class.getResourceAsStream(path)) {
            if (in == null) {
                throw new FileNotFoundException(path);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
