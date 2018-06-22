package codesquad.web;

import codesquad.domain.Milestone;
import codesquad.domain.User;
import codesquad.dto.MilestoneDto;
import codesquad.security.LoginUser;
import codesquad.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static support.domain.Entity.MILESTONE;
import static support.domain.Entity.getMultipleEntityName;

@Controller
@RequestMapping("/milestones")
public class MilestoneController {

    @Autowired
    private MilestoneService milestoneService;

    @GetMapping("/form")
    public String form(@LoginUser User user) {
        return "/milestone/form";
    }

    @PostMapping
    public String create(@LoginUser User user, MilestoneDto milestoneDto) {
        Milestone milestone = milestoneService.create(user, milestoneDto);
        return milestone.generateRedirectUri();
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute(getMultipleEntityName(MILESTONE), milestoneService.get());
        return "/milestone/list";
    }
}