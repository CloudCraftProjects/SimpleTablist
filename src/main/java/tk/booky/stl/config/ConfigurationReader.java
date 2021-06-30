package tk.booky.stl.config;
// Created by booky10 in SimpleTablist (15:23 30.06.21)

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigurationReader<T extends ConfigurationSavable> {

    @SuppressWarnings("unchecked")
    public T read(Path folder, String file, T def) {
        File tomlFile = folder.resolve(file).toFile();

        if (tomlFile.exists()) {
            return new Toml().read(tomlFile).to((Class<T>) def.getClass());
        } else {
            return writeObject(def, tomlFile);
        }
    }

    public <O> O writeObject(O object, File file) {
        try {
            file.getParentFile().mkdirs();
            new TomlWriter().write(object, file);
            return object;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}