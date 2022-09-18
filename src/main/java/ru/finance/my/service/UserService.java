package ru.finance.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.finance.my.configuration.DefaultValueConfiguration;
import ru.finance.my.dto.RegistrationRequestDTO;
import ru.finance.my.entity.User;
import ru.finance.my.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private ExpenseCategoryService expenseCategoryService;

  @Autowired
  private IncomeCategoryService incomeCategoryService;

  @Autowired
  private DefaultValueConfiguration defaultValueConfiguration;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails userDetails = userRepository.getByLogin(username);
    if (userDetails == null) throw new UsernameNotFoundException("User not found");
    return userDetails;
  }

  @Transactional(rollbackFor = Exception.class)
  public void createNew(RegistrationRequestDTO registrationRequestDTO) {
    User user = getUser(registrationRequestDTO);
    userRepository.createNew(user);
    expenseCategoryService.createDefaultForNewUser(defaultValueConfiguration.getExpenseCategory(), user.getId());
    incomeCategoryService.createDefaultForNewUser(defaultValueConfiguration.getIncomeCategory(), user.getId());
  }

  public boolean existByEmail(String email) {
    return userRepository.existByEmail(email);
  }

  private User getUser(RegistrationRequestDTO registrationRequestDTO) {
    return User.builder()
        .email(registrationRequestDTO.getEmail())
        .name(registrationRequestDTO.getName())
        .login(registrationRequestDTO.getLogin())
        .password(bCryptPasswordEncoder.encode(registrationRequestDTO.getPassword()))
        .build();
  }
}
