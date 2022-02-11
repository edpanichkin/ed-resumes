package edpanichkin.resumes.storage;


import edpanichkin.resumes.Config;

public class SqlStorageTest extends AbstractStorageTest {

  public SqlStorageTest() {
    super((Config.get().getStorage()));
  }
}