package space.nurik.preproject3.task3.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.nurik.preproject3.task3.model.Role;
import space.nurik.preproject3.task3.model.User;
import space.nurik.preproject3.task3.repositorie.RoleRepository;
import space.nurik.preproject3.task3.repositorie.UserRepository;
import space.nurik.preproject3.task3.security.UserDetailsImp;
import space.nurik.preproject3.task3.util.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(int id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException(Integer.toString(id));
        }
        return foundUser.get();
    }

    @Transactional
    public void save(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Default role 'USER' not found"));
            user.setRoles(Set.of(defaultRole));
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        if (updatedUser.getRoles() == null || updatedUser.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Default role 'USER' not found"));
            updatedUser.setRoles(Set.of(defaultRole));
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodedPassword = passwordEncoder.encode(updatedUser.getPassword());
        updatedUser.setPassword(encodedPassword);
        userRepository.save(updatedUser);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException("Пользователь не найден");
        System.out.println("Пользователь аутентифицировался" + user.get());
        for (Role role : user.get().getRoles()) {
            System.out.println("Роль:" + role.getAuthority());
        }

        return new UserDetailsImp(user.get());
    }
}
