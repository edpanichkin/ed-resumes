package edpanichkin.resumes.storage;

import edpanichkin.resumes.storage.serializer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {

  public ObjectPathStorageTest() {
    super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
  }
}