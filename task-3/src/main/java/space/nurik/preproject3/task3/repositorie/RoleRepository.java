package space.nurik.preproject3.task3.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import space.nurik.preproject3.task3.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Optional<Role> findByName(String name);
}
