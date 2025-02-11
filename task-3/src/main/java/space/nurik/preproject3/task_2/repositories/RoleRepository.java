package space.nurik.preproject3.task_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.nurik.preproject3.task_2.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Optional<Role> findByName(String name);
}
