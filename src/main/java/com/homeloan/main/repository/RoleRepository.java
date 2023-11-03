package com.homeloan.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.homeloan.main.model.role.Role;
import com.homeloan.main.model.role.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(RoleName name);

}
