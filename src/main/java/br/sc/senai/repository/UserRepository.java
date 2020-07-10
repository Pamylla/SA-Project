package br.sc.senai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.sc.senai.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
}