package com.exam.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.Models.Role;

public interface RoleRepositry extends JpaRepository<Role, Long> {

}
