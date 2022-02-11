package edpanichkin.resumes.storage;

import edpanichkin.resumes.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

  @Override
  protected void fillDeletedElement(int index) {
    storage[index] = storage[size - 1];
  }

  @Override
  protected void insertElement(Resume r, int index) {
    storage[size] = r;
  }

  protected Integer getSearchKey(String uuid) {
    for (int i = 0; i < size; i++) {
      Resume r = storage[i];
      if (uuid.equals(r.getUuid())) {
        return i;
      }
    }
    return -1;
  }
}
