package filePersistence;

import java.nio.file.Path;
import java.util.List;

public abstract class FileReader<T> {
    Path path;

    public FileReader(Path pPath) {
        path = pPath;
    }

    public abstract List<T> deserializeFile();
}