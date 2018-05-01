package tests;

import main.SimpleUserService;
import main.User;
import main.UserService;
import tests.FakeUserStore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUserService {
  private UserService userService;
  private MockAuditLog mockAuditLog;
  private FakeUserStore fakeUserStore;

  @Before
  public void myCreateUserService() {
    System.out.println("before");

    mockAuditLog = new MockAuditLog();
    fakeUserStore = new FakeUserStore();
    userService = new SimpleUserService(mockAuditLog, fakeUserStore);
    mockAuditLog.enable();
  }

  @Test
  public void hello() {
    System.out.println("hi");
  }
  @Test
  public void testFindingUserByName() { //18.33
    System.out.println("hi");

    System.out.println("before");

    mockAuditLog = new MockAuditLog();
    fakeUserStore = new FakeUserStore();
    userService = new SimpleUserService(mockAuditLog, fakeUserStore);

    userService.register("bob");
    System.out.println("hi");

    User user = userService.find("bob");
    System.out.println("hi");

    assertNotNull(user);
    assertEquals("bob", user.getUsername());
  }

  @Test
  public void testRegisterTwoUsers() {
    mockAuditLog.enable();

    mockAuditLog.expect("user", "register", "bob");
    userService.register("bob");
    mockAuditLog.verify();
    assertEquals(1, userService.users().size());

    mockAuditLog.expect("user", "register", "alice");
    userService.register("alice");
    mockAuditLog.verify();
    assertEquals(2, userService.users().size());

    User user = userService.find("alice");
    assertEquals("alice", user.getUsername());
  }
}

