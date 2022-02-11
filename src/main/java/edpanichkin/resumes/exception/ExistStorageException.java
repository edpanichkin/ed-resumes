package edpanichkin.resumes.exception;

public class ExistStorageException extends StorageException {

  public ExistStorageException(String message, String uuid) {
    super(message, uuid);
  }

  public ExistStorageException(String uuid) {
    super(uuid);
  }
}
