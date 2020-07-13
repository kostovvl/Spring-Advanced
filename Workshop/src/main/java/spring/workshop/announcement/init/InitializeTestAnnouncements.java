package spring.workshop.announcement.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.workshop.announcement.domain.AnnouncementEntity;
import spring.workshop.announcement.repository.AnnouncementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class InitializeTestAnnouncements implements CommandLineRunner {

    private final AnnouncementRepository announcementRepository;

    public InitializeTestAnnouncements(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (announcementRepository.count() == 0) {
            AnnouncementEntity first = new AnnouncementEntity();
            first.setTitle("One");
            first.setDescription("Description of first Annonuncemet");
            first.setCreatedOn(LocalDateTime.now());
            first.setUpdatedOn(LocalDateTime.now());

            AnnouncementEntity second = new AnnouncementEntity();
            second.setTitle("Two");
            second.setDescription("Description of second Annonuncemet");
            second.setCreatedOn(LocalDateTime.now());
            second.setUpdatedOn(LocalDateTime.now());

            this.announcementRepository.saveAll(List.of(first, second));

        }
    }
}
