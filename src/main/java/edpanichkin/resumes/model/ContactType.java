package edpanichkin.resumes.model;

public enum ContactType {
  PHONE("Тел."),
  MOBILE("Мобильный"),
  HOME_PHONE("Домашний тел."),
  SKYPE("Skype"),
  MAIL("Почта"),
  LINKEDIN("Профиль linkedIn"),
  GITHUB("Профиль GITHUB"),
  STACKOVERFLOW("Профиль stackoverflow"),
  HOME_PAGE("Домашняя страница");
  private final String title;

  ContactType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public String toHtml(String value) {
    return title + ": " + value;
  }
}