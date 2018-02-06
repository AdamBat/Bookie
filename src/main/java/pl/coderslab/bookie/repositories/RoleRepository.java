package pl.coderslab.bookie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.bookie.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);

}
