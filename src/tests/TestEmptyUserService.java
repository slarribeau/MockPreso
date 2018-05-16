package tests;

import main.SimpleUserService;
import main.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestEmptyUserService {
  private UserService userService;
  private MockAuditLog mockAuditLog;
  private FakeUserStore fakeUserStore;

  @Before
  public void createUserService() {
    mockAuditLog = new MockAuditLog();
    fakeUserStore = new FakeUserStore();
    userService = new SimpleUserService(mockAuditLog, fakeUserStore);
  }


  @Test
  public void hello() {
  }
  @Test
  public void testDefaultUserSrviceHasNoUsers() {
    assertEquals(0, userService.users().size());
  }
  @Test
  public void testFindingNonExistantUser() {
    assertNull(userService.find("bob"));
  }
}
