package edu.todobot.database.repository;

import edu.todobot.database.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

}
