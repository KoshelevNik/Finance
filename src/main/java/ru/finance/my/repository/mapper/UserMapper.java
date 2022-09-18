package ru.finance.my.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.finance.my.entity.User;

@Mapper
public interface UserMapper {

  User getByLogin(String login);

  void createNew(User user);

  boolean existByEmail(String email);
}
