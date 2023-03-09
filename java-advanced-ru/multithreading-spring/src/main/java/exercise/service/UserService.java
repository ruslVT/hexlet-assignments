package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> updateUserById(Long id, User user) {
        return userRepository.findById(id)
                .map(updatedUser -> {
                    updatedUser.setId(id);
                    updatedUser.setFirstName(user.getFirstName());
                    updatedUser.setLastName(user.getLastName());
                    updatedUser.setEmail(user.getEmail());
                    return updatedUser;
                }).flatMap(userRepository::save);
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }
    // END
}
