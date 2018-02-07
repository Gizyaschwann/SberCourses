package HW1;

import java.io.File;

public interface IHandle {

    void start();
    void handleFile(File item);
    void stopThreads();
}
