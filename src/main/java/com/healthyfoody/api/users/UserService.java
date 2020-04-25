package com.healthyfoody.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    public UserAccount findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public UserAccount register(UserAccount requestBody) {
        requestBody.setEmailValidated(true);
        requestBody.setStatusCode(1);
        if(requestBody.getId() == null) {
            //requestBody.setPassword(passwordEncoder.encode(requestBody.getPassword()));
        }else {
            UserAccount account = userRepository.findById(requestBody.getId()).get();
            if(account.getEmail() == requestBody.getEmail()) {
                account.setName(requestBody.getName());
            }else {
                return null;
            }
        }
        requestBody = userRepository.save(requestBody);
        requestBody.setPassword("*******************");
        return requestBody;
    }

}
