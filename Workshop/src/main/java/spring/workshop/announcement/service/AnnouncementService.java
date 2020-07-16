package spring.workshop.announcement.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.workshop.announcement.domain.AnnouncementDto;
import spring.workshop.announcement.domain.AnnouncementEntity;
import spring.workshop.announcement.domain.AnnouncementView;
import spring.workshop.announcement.repository.AnnouncementRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final ModelMapper mapper;

    public AnnouncementService(AnnouncementRepository announcementRepository, ModelMapper mapper) {
        this.announcementRepository = announcementRepository;
        this.mapper = mapper;
    }

    public void addNewAnnouncement(AnnouncementDto announcementDto) {
        AnnouncementEntity announcementEntity = this.mapper.map(announcementDto, AnnouncementEntity.class);
        announcementEntity.setCreatedOn(LocalDateTime.now());
        announcementEntity.setUpdatedOn(LocalDateTime.now());
        this.announcementRepository.saveAndFlush(announcementEntity);
    }

    public List<AnnouncementView> getAllAnnouncements() {
        return this.announcementRepository.findAll()
                .stream()
                .map(a -> this.mapper.map(a, AnnouncementView.class))
                .collect(Collectors.toList());
    }

    public void deleteAnnouncement(Long id) {
        this.announcementRepository.deleteById(id);
    }

    public Boolean exists(String title) {
        return this.announcementRepository.findByTitle(title) != null;
    }
}
