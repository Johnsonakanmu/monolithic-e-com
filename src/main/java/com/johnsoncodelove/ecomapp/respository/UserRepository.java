package com.johnsoncodelove.ecomapp.respository;

import com.johnsoncodelove.ecomapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
