package edpanichkin.resumes.storage;

import edpanichkin.resumes.storage.serializer.XmlStreamSerializer;

public class XMLPathStorageTest extends AbstractStorageTest {

  public XMLPathStorageTest() {
    super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
  }
}