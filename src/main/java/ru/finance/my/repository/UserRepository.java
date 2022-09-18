package ru.finance.my.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.finance.my.entity.User;
import ru.finance.my.repository.mapper.UserMapper;

@Repository
public class UserRepository {

  @Autowired
  private UserMapper userMapper;

  public User getByLogin(String login) {
    return userMapper.getByLogin(login);
  }

  public void createNew(User user) {
    userMapper.createNew(user);
  }

  public boolean existByEmail(String email) {
    return userMapper.existByEmail(email);
  }
}
