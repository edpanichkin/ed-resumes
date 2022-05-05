package edpanichkin.resumes.model;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
    return Arrays.toString(title.getBytes(StandardCharsets.UTF_8)) + ": " + value;
  }
}