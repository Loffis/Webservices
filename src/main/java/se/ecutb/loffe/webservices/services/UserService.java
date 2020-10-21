package se.ecutb.loffe.webservices.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.ecutb.loffe.webservices.entities.User;
import se.ecutb.loffe.webservices.repositories.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    //private Logger log = LoggerFactory(UserService.class);
    //@Autowired
    //private UserRepository userRepository;
    private final UserRepository userRepository;

    public List<User> findAll(String name, boolean sort) {
        log.info("Request to find all users.");
        //return userRepository.findAll();
        var users = userRepository.findAll();
        if (name != null) {
            users = users.stream().filter(user -> user.getFirstname().startsWith(name) ||
                    user.getLastname().startsWith(name) ||
                    user.getUsername().startsWith(name))
                    .sorted((user1, user2) -> user1.getBirthdate().compareTo(user2.getBirthdate()))
                    .collect(Collectors.toList());
        }

        if (sort) {
            //users.sort((user1, user2) -> user1.getBirthdate().compareTo(user2.getBirthdate()));
            users.sort(Comparator.comparing(User::getBirthdate));
        }
        return users;
    }

    public User findById(String id) {
        /* Olika sätt att kolla om user existerar
        var userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new RuntimeException();
        }
        return userOptional.get();

        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new RuntimeException();

        // 3
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
         */

        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Could not find the user by id %s.", id)));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void update(String id, User user) {
        if (!userRepository.existsById(id)) {
            log.error(String.format("Could not find the user by id %s.", id));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        /*
        User.builder()
                .id()
                .phone("Tel: " + user.getPhone())
                .build();
        */
        userRepository.save(user);
    }

    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the user by id %s.", id));
        }
        userRepository.deleteById(id);
    }
}
