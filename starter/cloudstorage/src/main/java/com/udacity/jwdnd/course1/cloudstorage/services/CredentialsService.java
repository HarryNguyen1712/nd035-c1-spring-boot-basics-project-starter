package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.model.CredentialModel;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {

  private final CredentialMapper credentialMapper;
  private final UserMapper userMapper;

  private final EncryptionService encryptionService;

  public CredentialsService(CredentialMapper credentialMapper, UserMapper userMapper,
                            EncryptionService encryptionService) {
    this.credentialMapper = credentialMapper;
    this.userMapper = userMapper;
    this.encryptionService = encryptionService;
  }

  public String insertOrUpdateCredential(CredentialModel credentialModel) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    int userId = userMapper.getUserId(username);
    SecureRandom random = new SecureRandom();
    byte[] key = new byte[16];
    random.nextBytes(key);
    String encodedKey = Base64.getEncoder().encodeToString(key);
    String encryptedPassword =
        encryptionService.encryptValue(credentialModel.password(), encodedKey);
    int result;
    Credential credential;
    credential = new Credential(
        credentialModel.credentialId(),
        credentialModel.url(),
        credentialModel.username(),
        encodedKey,
        encryptedPassword,
        userId);
    if (Objects.isNull(credentialModel.credentialId())) {
      result = credentialMapper.insert(credential);
    } else {
      result = credentialMapper.updateCredential(credential);
    }
    if (result == 1) {
      return null;
    } else {
      return "Add or update credential failed";
    }
  }

  public List<Credential> getListCredential() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    int userId = userMapper.getUserId(username);
    return credentialMapper.getListCredential(userId);
  }

  public String deleteCredential(Integer credentialId) {
    int result = credentialMapper.deleteCredential(credentialId);
    if (result == 1) {
      return null;
    } else {
      return "Delete credential failed";
    }
  }
}
