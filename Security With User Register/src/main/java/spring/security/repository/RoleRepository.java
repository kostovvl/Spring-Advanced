package spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.domain.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
