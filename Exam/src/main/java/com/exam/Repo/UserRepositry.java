package com.exam.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.Models.User;

public interface UserRepositry extends JpaRepository<User, Long> {

	public User findByUserName(String username);
}
