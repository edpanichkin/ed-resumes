package edpanichkin.resumes.storage;


import edpanichkin.resumes.exception.StorageException;
import edpanichkin.resumes.model.Resume;
import org.junit.Assert;
import org.junit.Test;


public class AbstractArrayStorageTest extends AbstractStorageTest {

  public AbstractArrayStorageTest(Storage storage) {
    super(storage);
  }

  @Test(expected = StorageException.class)
  public void saveOverflow() {
    try {
      for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
        storage.save(new Resume("Name" + i));
      }
    } catch (StorageException e) {
      Assert.fail();
    }
    System.out.println(storage.size());
    storage.save(new Resume("OverFlow"));
  }
}