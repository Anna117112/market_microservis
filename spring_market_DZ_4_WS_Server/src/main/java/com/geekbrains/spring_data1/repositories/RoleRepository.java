package com.geekbrains.spring_data1.repositories;


import com.geekbrains.spring_data1.entites.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
