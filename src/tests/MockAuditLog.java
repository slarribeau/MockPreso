package tests;
import main.AuditLog;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MockAuditLog implements AuditLog {
  private String expectedLogType;
  private String expectedSubtype;
  private String expectedData;
  private boolean logCalled;
  private boolean enableAssertions;

  public void expect(String logType, String subtype, String data) {
    expectedLogType = logType;
    expectedSubtype = subtype;
    expectedData = data;
  }
  public void enable() {
    enableAssertions = true;
  }

  @Override
  public void log(String logType, String subtype, String data) {
    System.out.println(logType + " " + subtype + " " + data);
    if (enableAssertions) {
      assertEquals(expectedLogType, logType);
      assertEquals(expectedSubtype, subtype);
      assertEquals(expectedData, data);
    }
    logCalled = true;
  }
  public void verify() {
    assertTrue(logCalled);
  }



}
