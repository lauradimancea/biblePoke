package com.example.biblePoke.service;

import com.example.biblePoke.error.UserAlreadyExistException;
import com.example.biblePoke.model.CreateAccountRequest;
import com.example.biblePoke.model.Privilege;
import com.example.biblePoke.model.Role;
import com.example.biblePoke.model.User;
import com.example.biblePoke.repository.PrivilegeRepository;
import com.example.biblePoke.repository.RoleRepository;
import com.example.biblePoke.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CreateAccountService {

    private final UserRepository userRepository ;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public CreateAccountService(UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    public List<CreateAccountRequest> getAccounts() {
        return null;
    }


    public User createAccount(CreateAccountRequest createAccountRequest){
        if (emailExists(createAccountRequest.getEmail())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email address:"
                            + createAccountRequest.getEmail());
        }
        User newUser = new User();
        newUser.setUsername(createAccountRequest.getUsername());
        newUser.setPassword(encoder.encode(createAccountRequest.getPassword()));
        newUser.setEmail(createAccountRequest.getEmail());
        Role role = roleRepository.findByName("USER_DEFAULT");
        Privilege privilege = privilegeRepository.findByName("ALL");
        if (privilege == null) {
            privilege = new Privilege("ALL");
            privilegeRepository.save(privilege);
        }
        if (role == null) {
            role = new Role("USER_DEFAULT");
            role.setPrivileges(Arrays.asList(privilege));
            roleRepository.save(role);
        } else {
            role.setPrivileges(Arrays.asList(privilege));
        }

        newUser.setRoles(Arrays.asList(roleRepository.findByName("USER_DEFAULT")));


        return userRepository.save(newUser);
    }
    public void deleteAll() {
        userRepository.deleteAll();
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }


}
