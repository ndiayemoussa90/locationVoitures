package org.sid.auth;


import org.sid.datas.RegistrationRequest;
import org.sid.datas.UserResponse;
import org.sid.entities.User;
import org.sid.entities.Authenticate;

import java.util.List;

public interface AuthenticateService {

    short count();

    Authenticate save(RegistrationRequest registrationRequest);

    Authenticate save(String username, String password, String roles, User user);

    List<UserResponse> listClients();

    List<UserResponse> listUsers();

    List<UserResponse> listUsers(String roles);

    List<UserResponse> listUsers(String roles, User user);

    List<UserResponse> listUsers(User user);

    boolean updateById(long id, RegistrationRequest registrationRequest);

    void deleteById(long id);

    boolean isAuthenticated();

    Authenticate getAuthenticate();

    void login(Authenticate authenticate);

}
