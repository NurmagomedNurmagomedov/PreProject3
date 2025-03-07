package space.nurik.preproject3.task3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.nurik.preproject3.task3.model.Role;
import space.nurik.preproject3.task3.repositorie.RoleRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findRoleByName(String roleName) {
        Optional<Role> findRole = roleRepository.findByName(roleName);
        return findRole.orElse(null);
    }
}
