package spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
