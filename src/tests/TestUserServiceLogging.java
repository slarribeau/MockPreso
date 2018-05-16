package tests;

import main.SimpleUserService;
import main.User;
import main.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestUserServiceLogging {
    private UserService userService;
    private MockAuditLog mockAuditLog;
    private FakeUserStore fakeUserStore;

    @Before
    public void myCreateUserService() {
      mockAuditLog = new MockAuditLog();
      fakeUserStore = new FakeUserStore();
      userService = new SimpleUserService(mockAuditLog, fakeUserStore);
    }

    @Test
    public void testRegisterUser() {

      mockAuditLog.enable();
      mockAuditLog.expect("user", "register", "bob");
      userService.register("bob");
      mockAuditLog.verify();
    }
  }

