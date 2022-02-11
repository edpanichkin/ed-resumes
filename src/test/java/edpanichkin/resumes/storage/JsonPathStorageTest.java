package edpanichkin.resumes.storage;

import edpanichkin.resumes.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {
  //You can fix this issue on your own.
  //
  //add --add-opens java.base/java.time=ALL-UNNAMED as start-argument and it'll work.

  public JsonPathStorageTest() {
    super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
  }
}