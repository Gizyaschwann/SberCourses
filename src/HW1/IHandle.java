package HW1;

import java.io.File;

public interface IHandle {
    void start();
    void stopThreads();
    void handleFile(File file);
}
