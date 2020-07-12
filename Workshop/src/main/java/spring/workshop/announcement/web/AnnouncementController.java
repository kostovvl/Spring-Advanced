package spring.workshop.announcement.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.workshop.announcement.domain.AnnouncementBinding;
import spring.workshop.announcement.service.AnnouncementService;

import javax.validation.Valid;


@Controller
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping()
    public String getAnnouncements(Model model) {

        model.addAttribute("announcements",
                this.announcementService.getAllAnnouncements());

        return "announcement/announcements";
    }

    @GetMapping("/save")
    public String save(Model model) {

        if (model.getAttribute("newAnnouncement") == null) {
            model.addAttribute("newAnnouncement", new AnnouncementBinding());
        }

        return "announcement/new";
    }

    @PostMapping("/save")
    public String saveConfirm(@Valid @ModelAttribute("newAnnouncement")
                              BindingResult bindingResult, AnnouncementBinding announcementBinding,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newAnnouncement", announcementBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newAnnouncement", bindingResult);
            return "redirect:/announcements/save";
        }

        if (this.announcementService.exists(announcementBinding.getTitle())) {
            redirectAttributes.addFlashAttribute("newAnnouncement", announcementBinding);
            redirectAttributes.addFlashAttribute("announcementExists", true);
            return "redirect:/announcements/save";
        }

        return "redirect:/announcements";
    }

    @GetMapping("/delete{deleteId}")
    public String delete(@PathVariable("deleteId") Long id) {

        this.announcementService.deleteAnnouncement(id);

        return "redirect:/announcements";
    }
}
