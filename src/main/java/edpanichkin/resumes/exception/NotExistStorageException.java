package edpanichkin.resumes.exception;

public class NotExistStorageException extends StorageException {

  public NotExistStorageException(String message, String uuid) {
    super(message, uuid);
  }

  public NotExistStorageException(String uuid) {
    super(uuid);
  }
}
