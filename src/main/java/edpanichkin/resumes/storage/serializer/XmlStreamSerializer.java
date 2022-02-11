package edpanichkin.resumes.storage.serializer;

import edpanichkin.resumes.model.*;
import edpanichkin.resumes.util.XmlParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements StreamSerializer {

  private XmlParser xmlParser;

  public XmlStreamSerializer() {
    xmlParser = new XmlParser(
        Resume.class, Organization.class, Link.class,
        OrganizationSection.class, TextSection.class, ListSection.class,
        Organization.Position.class);
  }

  @Override
  public void doWrite(Resume r, OutputStream os) throws IOException {
    try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
      xmlParser.marshall(r, w);
    }
  }

  @Override
  public Resume doRead(InputStream is) throws IOException {
    try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
      return xmlParser.unmarshall(r);
    }
  }
}
