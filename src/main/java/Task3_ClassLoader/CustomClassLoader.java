package Task3_ClassLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {
    private final String classPath;

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filePath = classPath + name.replace('.', '/') + ".class";
        File classFile = new File(filePath);

        if (!classFile.exists()) {
            throw new ClassNotFoundException("Class not found: " + name);
        }

        try (InputStream inputStream = new FileInputStream(classFile)) {
            byte[] classBytes = new byte[(int) classFile.length()];
            inputStream.read(classBytes);

            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class: " + name, e);
        }
    }
}
