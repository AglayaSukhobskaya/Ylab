package com.edu.ulab.app.repository;

import com.edu.ulab.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
