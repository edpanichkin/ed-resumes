package edpanichkin.resumes.storage;

import edpanichkin.resumes.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

  private final List<Resume> list = new ArrayList<>();

  @Override
  protected void doUpdate(Resume r, Integer index) {
    list.set(index, r);
  }

  @Override
  protected void doSave(Resume r, Integer index) {
    list.add(r);
  }

  @Override
  protected void doDelete(Integer index) {
    list.remove(index.intValue());
  }

  @Override
  protected boolean isExist(Integer searchKey) {
    return searchKey != null;
  }

  @Override
  protected Integer getSearchKey(String uuid) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getUuid().equals(uuid)) {
        return i;
      }
    }
    return null;
  }

  @Override
  protected Resume doGet(Integer index) {
    return list.get(index);
  }

  @Override
  public void clear() {
    list.clear();

  }

  @Override
  public List<Resume> doCopyAll() {
    return new ArrayList<>(list);
  }

  @Override
  public int size() {
    return list.size();
  }
}
