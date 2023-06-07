package com.udacity.jwdnd.course1.cloudstorage.web.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.web.model.CredentialModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CredentialModelMapper {

  public CredentialModel convertEntityToModel(Credential credential) {
    return new CredentialModel(credential.credentialId(), credential.url(),
        credential.username(), credential.key(),
        credential.password());
  }

  public List<CredentialModel> convertEntityToModelList(List<Credential> credentialList) {
    List<CredentialModel> credentialModelList = new ArrayList<>();
    for (Credential credential : credentialList) {
      credentialModelList.add(convertEntityToModel(credential));
    }
    return credentialModelList;
  }
}
