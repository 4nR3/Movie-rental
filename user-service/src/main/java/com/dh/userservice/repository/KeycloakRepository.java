package com.dh.userservice.repository;

import com.dh.userservice.model.User;

import com.dh.userservice.model.UserDTO;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class KeycloakRepository {
    @Autowired
    private Keycloak keycloak;
    @Value("${keycloak.realm}")
    private String realm;

    public List<UserDTO> findAll(){

        List<GroupRepresentation> groupRepresentations = keycloak
                .realm(realm)
                .groups()
                .groups();

        List<UserRepresentation> allUserRepresentations = new ArrayList<>();
        List<UserRepresentation> adminUserRepresentations = new ArrayList<>();

        allUserRepresentations=keycloak
                .realm(realm)
                .users()
                .list();

        for(GroupRepresentation g: groupRepresentations) {
            if (g.getName().contains("admin"))
                {adminUserRepresentations=keycloak.realm(realm)
                        .groups()
                        .group(g.getId())
                        .members();
                    break;
                }
        }

        List<UserRepresentation> finalUser = allUserRepresentations;

        for(int f=0; f<allUserRepresentations.size();f++)
            for(int i=0;i<adminUserRepresentations.size();i++)
                if(allUserRepresentations.get(f).getId().contains(adminUserRepresentations.get(i).getId()))
                    finalUser.remove(f);


        List<UserDTO> userDTOList = finalUser.stream().map(this::fromRepresentationToDTO).collect(Collectors.toList());

        return userDTOList;
    }

    public User findByUsername(String username){

        List <UserRepresentation> user = keycloak
                .realm(realm)
                .users()
                .search(username);

        return fromRepresentationToEntity(user.get(0));
    }

    private UserDTO fromRepresentationToDTO(UserRepresentation userRepresentation) {
        return new UserDTO(userRepresentation.getUsername(),userRepresentation.getEmail());
    }

    private User fromRepresentationToEntity(UserRepresentation userRepresentation) {
        return new User(userRepresentation.getUsername(),userRepresentation.getFirstName(),userRepresentation.getLastName(),userRepresentation.getEmail());
    }

}


