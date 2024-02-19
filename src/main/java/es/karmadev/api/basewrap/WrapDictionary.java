package es.karmadev.api.basewrap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Represents a BaseWrap dictionary
 */
public final class WrapDictionary {

    private final Path dictionaryPath;

    /**
     * Initialize the dictionary path
     * @param path the path
     * @throws NoSuchFileException if the path is not a file
     */
    public WrapDictionary(final Path path) throws NoSuchFileException {
        this.dictionaryPath = path;
        if (Files.isDirectory(path)) {
            throw new NoSuchFileException("Cannot create a dictionary from path " + path + " because is not a file");
        }
    }

    /**
     * Get the dictionary path
     *
     * @return the dictionary path
     */
    public Path getDictionaryPath() {
        return this.dictionaryPath;
    }

    /**
     * Save the dictionary
     *
     * @param dictionary the dictionary to save
     * @throws IOException if the dictionary fails to save
     */
    public void saveDictionary(final String[] dictionary) throws IOException {
        Path parent = this.dictionaryPath.toAbsolutePath().getParent();
        if (!Files.exists(parent)) {
            Files.createDirectories(parent);
        }

        try (ObjectOutputStream ous = new ObjectOutputStream(Files.newOutputStream(dictionaryPath,
                StandardOpenOption.CREATE))) {
            ous.writeObject(dictionary);
            ous.flush();
        }
    }

    /**
     * Read the dictionary
     *
     * @return the dictionary
     * @throws IOException if the dictionary fails to read
     */
    public String[] readDictionary() throws IOException {
        if (!Files.exists(this.dictionaryPath)) return null;

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(dictionaryPath,
                StandardOpenOption.CREATE))) {
            Object obj = ois.readObject();
            if (obj == null) return null;

            if (!obj.getClass().isArray()) return null;
            Object[] arrayObject = (Object[]) obj;
            String[] raw = new String[arrayObject.length];

            for (int i = 0; i < arrayObject.length; i++) {
                Object value = arrayObject[i];
                if (!(value instanceof String)) throw new IOException("Found non-string in dictionary");

                raw[i] = (String) value;
            }

            return raw;
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }
}
