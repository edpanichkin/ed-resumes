package edpanichkin.resumes.storage;

import edpanichkin.resumes.exception.StorageException;
import edpanichkin.resumes.model.Resume;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer>{

  @Override
  protected boolean isExist(Integer index) {
    return  index >= 0;
  }

  protected static final int STORAGE_LIMIT = 10000;
  protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int size = 0;

  public int size() {
    return size;
  }

  public void clear() {
    Arrays.fill(storage, 0, size, null);
    //IntStream.range(0, size).forEach(i -> storage[i] = null);
    size = 0;
  }

  @Override
  public void doUpdate(Resume r, Integer index) {
    storage[ index] = r;
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  @Override
  public List<Resume> doCopyAll() {
    return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
  }

  @Override
  protected void doSave(Resume r, Integer index) {
    if (size == STORAGE_LIMIT) {
      throw new StorageException("STORAGE OVERFLOW", r.getUuid());
    } else {
      //System.out.println(r.getUuid() + " saved");
      insertElement(r,  index);
      size++;
    }
  }

  @Override
  public void doDelete(Integer index) {
    fillDeletedElement( index);
    storage[size - 1] = null;
    size--;
  }
  @Override
  public Resume doGet(Integer index) {
    return storage[ index];
  }

  protected abstract void fillDeletedElement(int index);

  protected abstract void insertElement(Resume r, int index);

  protected abstract Integer getSearchKey(String uuid);
}
