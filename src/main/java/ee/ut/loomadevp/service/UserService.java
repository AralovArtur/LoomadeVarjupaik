package ee.ut.loomadevp.service;

import ee.ut.loomadevp.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);
}
