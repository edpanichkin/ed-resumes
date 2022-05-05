package edpanichkin.resumes.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    PrintWriter printWriter = resp.getWriter();

    try {
      Class.forName("org.postgresql.Driver");
      printWriter.write("LOAD...");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    printWriter.write(System.getProperty("java.class.path") + "<br>" + System.getProperty("java.version"));

    printWriter.close();
  }
}
