package com.tpo.shopandpack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpo.shopandpack.model.User;

public interface UserRepository extends JpaRepository<User, Long>{}
