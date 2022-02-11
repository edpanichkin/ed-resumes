package edpanichkin.resumes.storage;

import static edpanichkin.resumes.TestData.*;
import static org.junit.Assert.assertEquals;

import edpanichkin.resumes.Config;
import edpanichkin.resumes.exception.ExistStorageException;
import edpanichkin.resumes.exception.NotExistStorageException;
import edpanichkin.resumes.model.Resume;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {

  protected Storage storage;
  protected static final File STORAGE_DIR = Config.get().getStorageDir();

  public AbstractStorageTest(Storage storage) {
    this.storage = storage;
  }

  @Before
  public void setUp() {
    storage.clear();
    storage.save(R1);
    storage.save(R2);
    storage.save(R3);
  }

  @Test
  public void size() {
    System.out.println(storage.size());
    assertSize(3);
  }

  @Test
  public void get() {
   // assertGet(R1);
    assertGet(R2);
    assertGet(R3);
  }

  @Test(expected = NotExistStorageException.class)
  public void getNotExist() {
    storage.get("dummy");
  }

  @Test
  public void clear() {
    storage.clear();
    assertSize(0);
  }

  @Test
  public void getAllSorted() {
    List<Resume> list = storage.getAllSorted();
    assertEquals(3, list.size());
    assertEquals(list, new ArrayList<>(Arrays.asList(R1, R2, R3)));

  }

  @Test
  public void save() {
    storage.save(R4);
    assertSize(4);
    assertGet(R4);
  }


  @Test
  public void update() {
    Resume newResume = new Resume(UUID_1, "NewName");
    storage.update(newResume);
    assertEquals(newResume, storage.get(UUID_1));
  }

  @Test(expected = ExistStorageException.class)
  public void saveExist() {
    storage.save(R1);
  }

  @Test(expected = NotExistStorageException.class)
  public void delete() {
    storage.delete(UUID_1);
    assertSize(2);
    assertEquals(R4, storage.get(UUID_4));
  }

  private void assertSize(int size) {
    assertEquals(size, storage.size());
  }

  private void assertGet(Resume r) {
    assertEquals(r, storage.get(r.getUuid()));
  }
}