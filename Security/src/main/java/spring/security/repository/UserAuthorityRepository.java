package spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.domain.entity.UserAuthorityEntity;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthorityEntity, Long> {
}
