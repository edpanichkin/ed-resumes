package edpanichkin.resumes.storage;

import edpanichkin.resumes.model.Resume;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {

  private final Map<String, Resume> map = new HashMap<>();

  @Override
  protected String getSearchKey(String uuid) {
    return uuid;
  }

  @Override
  protected void doUpdate(Resume r, String uuid) {
    map.put(uuid, r);
  }

  @Override
  protected void doSave(Resume r, String uuid) {
    map.put(uuid, r);
  }

  @Override
  protected void doDelete(String uuid) {
    map.remove(uuid);
  }

  @Override
  protected boolean isExist(String uuid) {
    return map.containsKey(uuid);
  }

  @Override
  protected List<Resume> doCopyAll() {
    return new ArrayList<>(map.values());
  }

  @Override
  protected Resume doGet(String uuid) {
    return map.get(uuid);
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public int size() {
    return map.size();
  }
}
