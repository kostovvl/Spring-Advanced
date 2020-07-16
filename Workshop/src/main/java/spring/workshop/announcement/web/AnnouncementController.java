package spring.workshop.announcement.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.workshop.announcement.domain.AnnouncementBinding;
import spring.workshop.announcement.domain.AnnouncementDto;
import spring.workshop.announcement.service.AnnouncementService;

import javax.validation.Valid;


@Controller
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final ModelMapper mapper;

    public AnnouncementController(AnnouncementService announcementService, ModelMapper mapper) {
        this.announcementService = announcementService;
        this.mapper = mapper;
    }

    @GetMapping()
    public String getAnnouncements(Model model) {

        model.addAttribute("announcements",
                this.announcementService.getAllAnnouncements());

        return "announcement/announcements";
    }

    @GetMapping("/new")
    public String save(Model model) {

        if (model.getAttribute("newAnnouncement") == null) {
            model.addAttribute("newAnnouncement", new AnnouncementBinding());
        }

        return "announcement/new";
    }

    @PostMapping("/save")
    public String saveConfirm(@Valid @ModelAttribute("newAnnouncement")
                               AnnouncementBinding announcementBinding, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newAnnouncement", announcementBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newAnnouncement", bindingResult);
            return "redirect:/announcements/new";
        }

        if (this.announcementService.exists(announcementBinding.getTitle())) {
            redirectAttributes.addFlashAttribute("newAnnouncement", announcementBinding);
            redirectAttributes.addFlashAttribute("announcementExists", true);
            return "redirect:/announcements/new";
        }

        this.announcementService.addNewAnnouncement(this.mapper.map(announcementBinding, AnnouncementDto.class));

        return "redirect:/announcements";
    }

    @PostMapping("/delete{deleteId}")
    public String delete(@PathVariable("deleteId") Long id) {


        this.announcementService.deleteAnnouncement(id);

        return "redirect:/announcements";
    }

//    @ExceptionHandler({Error.class, Exception.class})
//    public String exceptionHandle() {
//        return "exception/exception-announcement";
//    }

}
