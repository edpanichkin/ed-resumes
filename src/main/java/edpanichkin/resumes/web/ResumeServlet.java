package edpanichkin.resumes.web;

import edpanichkin.resumes.Config;
import edpanichkin.resumes.model.ContactType;
import edpanichkin.resumes.model.Resume;
import edpanichkin.resumes.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resume")
public class ResumeServlet extends HttpServlet {
  private Storage storage; // = Config.get().getStorage();

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);

//    storage = new SqlStorage("jdbc:postgresql://ec2-52-209-185-5.eu-west-1.compute.amazonaws.com:5432/db4j5oijaqe2h5",
//            "wnljsbxoztrpzj", "1e56fcdcb8e30c836fe38746edb8cf6522a8764f18440ca3744d57ce1e4f0686");
    storage = Config.get().getStorage();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String uuid = req.getParameter("uuid");
    String fullName = req.getParameter("fullName");
    Resume r = storage.get(uuid);
    r.setFullName(fullName);
    for (ContactType type : ContactType.values()) {
      String value = req.getParameter(type.name());
      if (value != null && value.trim().length() != 0) {
        r.addContact(type, value);
      } else {
        r.getContacts().remove(type);
      }
    }
    storage.update(r);
    resp.sendRedirect("resume");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String uuid = req.getParameter("uuid");
    String action = req.getParameter("action");
    if (action == null) {
      req.setAttribute("resumes", storage.getAllSorted());
      req.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(req, resp);
      return;
    }
    Resume r;
    switch (action) {
      case "delete": {
        storage.delete(uuid);
        resp.sendRedirect("resume");
        return;
      }
      case "view":
      case "edit":
        r = storage.get(uuid);
        break;
      default:
        throw new IllegalArgumentException("Action " + action + " is illegal");
    }
    req.setAttribute("resume", r);
    req.getRequestDispatcher("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp").forward(req, resp);

  }
}