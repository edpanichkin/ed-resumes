package edpanichkin.resumes.model;

import edpanichkin.resumes.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static edpanichkin.resumes.util.DateUtil.NOW;
import static edpanichkin.resumes.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
  public static final Organization EMPTY = new Organization("", "", Position.EMPTY);
  private Link homePage;
  private List<Position> positions;

  public Organization() {
  }

  public Organization(String name, String homePage, Position... positions) {
    this(new Link(name, homePage), Arrays.asList(positions));
  }

  public Organization(Link homePage, List<Position> positions) {
    this.homePage = homePage;
    this.positions = positions;
  }

  public Link getHomePage() {
    return homePage;
  }

  public List<Position> getPositions() {
    return positions;
  }

  @Override
  public String toString() {
    return "Organization{" +
            "homePage=" + homePage +
            ", positions=" + positions +
            '}';
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  public static class Position implements Serializable {
    public static final Position EMPTY = new Position();

    private static final long serialVersionUID = 1L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String title;
    private String description;

    public Position() {
    }

    public Position(int startYear, Month startMonth, String title,
                    String description) {
      this(of(startYear, startMonth), NOW, title, description);
    }

    public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title,
                    String description) {
      this(of(startYear, startMonth), of(endYear, endMonth), title, description);
    }

    public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
      Objects.requireNonNull(startDate, "startDate must be not null");
      Objects.requireNonNull(endDate, "endDate must be not null");
      Objects.requireNonNull(title, "title must be not null");
      this.startDate = startDate;
      this.endDate = endDate;
      this.title = title;
      this.description = description == null ? "" : description;
    }

    public LocalDate getStartDate() {
      return startDate;
    }

    public LocalDate getEndDate() {
      return endDate;
    }

    public String getTitle() {
      return title;
    }

    public String getDescription() {
      return description;
    }
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Position position = (Position) o;
      return Objects.equals(startDate, position.startDate) &&
              Objects.equals(endDate, position.endDate) &&
              Objects.equals(title, position.title) &&
              Objects.equals(description, position.description);
    }

    @Override
    public int hashCode() {
      return Objects.hash(startDate, endDate, title, description);
    }

    @Override
    public String toString() {
      return "Position(" + startDate + ',' + endDate + ',' + title + ',' + description + ')';
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Organization that = (Organization) o;

    if (!Objects.equals(homePage, that.homePage)) {
      return false;
    }
    return Objects.equals(positions, that.positions);
  }

  @Override
  public int hashCode() {
    int result = homePage != null ? homePage.hashCode() : 0;
    result = 31 * result + (positions != null ? positions.hashCode() : 0);
    return result;
  }
}
