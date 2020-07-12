package spring.workshop.announcement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.workshop.announcement.domain.AnnouncementEntity;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {

    List<AnnouncementEntity> findAll();

    AnnouncementEntity findByTitle(String title);

}
