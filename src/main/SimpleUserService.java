package main;

import java.util.List;

public class SimpleUserService implements UserService {
  private AuditLog auditLog;
  private UserStore userStore;

  public SimpleUserService(AuditLog auditLog, UserStore userStore) {
    this.auditLog = auditLog;
    this.userStore = userStore;
  }
  @Override
  public List<User> users() {
    return userStore.findAll();
  }

  @Override
  public void register(String username) {
    if (hasUser(username)) {
      auditLog.log("user", "duplicateregister", username);
    } else {
      auditLog.log("user", "register", username);
      userStore.store(new User(username));
    }
  }


  @Override
  public User find(String username ) {
    List<User> allUsers = userStore.findAll();
    for (User user : allUsers ) {
      if (username.equals(user.getUsername()))
        return user;
    }
    return null;
  }

  private boolean hasUser(String username) {
    User user = find(username);
    return user != null;
  }
}
