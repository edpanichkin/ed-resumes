package edpanichkin.resumes.model;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {

  private String name;
  private String url;

  public Link() {
  }

  public Link(String name, String url) {
    Objects.requireNonNull(name, "name must be not null");

    this.name = name;
    this.url = url == null ? "" : url;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    return "Link{" +
        "name='" + name + '\'' +
        ", url='" + url + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Link link = (Link) o;

    if (!name.equals(link.name)) return false;
    return Objects.equals(url, link.url);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + (url != null ? url.hashCode() : 0);
    return result;
  }
}
