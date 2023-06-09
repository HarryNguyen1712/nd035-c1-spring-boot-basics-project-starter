package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.model.UserModel;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

  private final HashService hashService;

  private final UserMapper userMapper;

  public UsersService(HashService hashService, UserMapper userMapper) {
    this.hashService = hashService;
    this.userMapper = userMapper;
  }

  private boolean isUsernameAvailable(String username) {
    int result = userMapper.checkUserExist(username);
    return result != 1;
  }

  public String signUpUser(UserModel user) {
    if (isUsernameAvailable(user.username())) {
      SecureRandom random = new SecureRandom();
      byte[] salt = new byte[16];
      random.nextBytes(salt);
      String encodedSalt = Base64.getEncoder().encodeToString(salt);
      String hashedPassword = hashService.getHashedValue(user.password(), encodedSalt);
      int result = userMapper.insert(
          new User(null, user.username(), encodedSalt, hashedPassword, user.firstName(),
              user.lastName()));
      if (result == 0) {
        return "There was an error signing you up. Please try again.";
      } else {
        return null;
      }
    }
    return "The username already exists.";
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userMapper.getUser(username);
  }
}
