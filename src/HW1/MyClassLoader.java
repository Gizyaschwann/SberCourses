package HW1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class getClass(String name){
        String file = name + ".class";
        byte[] b = null;
        try {
            b = loadClassFileData(file);
            Class c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private byte[] loadClassFileData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(
                name);
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }

    public Class loadClass(String name) throws ClassNotFoundException {

        System.out.println("Loading Class '" + name + "'");
        if (name.startsWith("Handler")) {
            System.out.println("Loading Class using MyLoader");
            return getClass(name);
        }
        return super.loadClass(name);
    }

}
