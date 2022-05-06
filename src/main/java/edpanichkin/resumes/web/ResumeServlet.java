package edpanichkin.resumes.web;

import edpanichkin.resumes.Config;
import edpanichkin.resumes.model.*;
import edpanichkin.resumes.storage.Storage;
import edpanichkin.resumes.util.DateUtil;
import edpanichkin.resumes.util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/resume")
public class ResumeServlet extends HttpServlet {
  private Storage storage; // = Config.get().getStorage();

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    storage = Config.get().getStorage();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String uuid = req.getParameter("uuid");
    String fullName = req.getParameter("fullName");
    final boolean isCreate = (uuid == null || uuid.length() == 0);
    Resume r;
    if (isCreate) {
      r = new Resume(fullName);
    } else {
      r = storage.get(uuid);
      r.setFullName(fullName);
    }

    for (ContactType type : ContactType.values()) {
      String value = req.getParameter(type.name());
      if (HtmlUtil.isEmpty(value)) {
        r.getContacts().remove(type);
      } else {
        r.setContact(type, value);
      }
    }
    for (SectionType type : SectionType.values()) {
      String value = req.getParameter(type.name());
      String[] values = req.getParameterValues(type.name());
      if (HtmlUtil.isEmpty(value) && values.length < 2) {
        r.getSections().remove(type);
      } else {
        switch (type) {
          case OBJECTIVE, PERSONAL -> r.setSection(type, new TextSection(value));
          case ACHIEVEMENT, QUALIFICATIONS -> r.setSection(type, new ListSection(value.split("\\n")));
          case EDUCATION, EXPERIENCE -> {
            List<Organization> orgs = new ArrayList<>();
            String[] urls = req.getParameterValues(type.name() + "url");
            for (int i = 0; i < values.length; i++) {
              String name = values[i];
              if (!HtmlUtil.isEmpty(name)) {
                List<Organization.Position> positions = new ArrayList<>();
                String pfx = type.name() + i;
                String[] startDates = req.getParameterValues(pfx + "startDate");
                String[] endDates = req.getParameterValues(pfx + "endDate");
                String[] titles = req.getParameterValues(pfx + "title");
                String[] descriptions = req.getParameterValues(pfx + "description");
                for (int j = 0; j < titles.length; j++) {
                  if (!HtmlUtil.isEmpty(titles[j])) {
                    positions.add(new Organization.Position(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                  }
                }
                orgs.add(new Organization(new Link(name, urls[i]), positions));
              }
            }
            r.setSection(type, new OrganizationSection(orgs));
          }
        }
      }
    }
    if (isCreate) {
      storage.save(r);
    } else {
      storage.update(r);
    }
    resp.sendRedirect("resume");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String uuid = req.getParameter("uuid");
    String action = req.getParameter("action");
    if (action == null) {
      req.setAttribute("resumes", storage.getAllSorted());
      req.getRequestDispatcher("jsp/list.jsp").forward(req, resp);
      return;
    }
    Resume r;
    switch (action) {
      case "delete" -> {
        storage.delete(uuid);
        resp.sendRedirect("resume");
        return;
      }
      case "view" -> r = storage.get(uuid);
      case "add" -> r = Resume.EMPTY;
      case "edit" -> {
        r = storage.get(uuid);
        for (SectionType type : SectionType.values()) {
          Section section = r.getSection(type);
          switch (type) {
            case OBJECTIVE:
            case PERSONAL:
              if (section == null) {
                section = TextSection.EMPTY;
              }
              break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
              if (section == null) {
                section = ListSection.EMPTY;
              }
              break;
            case EXPERIENCE:
            case EDUCATION:
              OrganizationSection orgSection = (OrganizationSection) section;
              List<Organization> emptyFirstOrganizations = new ArrayList<>();
              emptyFirstOrganizations.add(Organization.EMPTY);
              if (orgSection != null) {
                for (Organization org : orgSection.getOrganizations()) {
                  List<Organization.Position> emptyFirstPositions = new ArrayList<>();
                  emptyFirstPositions.add(Organization.Position.EMPTY);
                  emptyFirstPositions.addAll(org.getPositions());
                  emptyFirstOrganizations.add(new Organization(org.getHomePage(), emptyFirstPositions));
                }
              }
              section = new OrganizationSection(emptyFirstOrganizations);
              break;
          }
          r.setSection(type, section);
        }
      }
      default -> throw new IllegalArgumentException("Action " + action + " is illegal");
    }
    req.setAttribute("resume", r);
    req.getRequestDispatcher(
            ("view".equals(action) ? "jsp/view.jsp" : "jsp/edit.jsp")
    ).forward(req, resp);
  }
}