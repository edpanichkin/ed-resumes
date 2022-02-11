package edpanichkin.resumes.storage;

import edpanichkin.resumes.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

  public DataPathStorageTest() {
    super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
  }
}