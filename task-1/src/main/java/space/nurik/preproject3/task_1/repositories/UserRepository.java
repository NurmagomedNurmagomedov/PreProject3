package space.nurik.preproject3.task_1.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.nurik.preproject3.task_1.models.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByName(String name);
}
