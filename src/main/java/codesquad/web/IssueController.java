package codesquad.web;

import codesquad.dto.IssueDto;
import codesquad.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/issues")
public class IssueController {
	private static final Logger log = LoggerFactory.getLogger(IssueController.class);

	@Resource(name = "issueService")
	private IssueService issueService;

	@GetMapping("/form")
	public String form() {
		return "/issue/form";
	}

	@PostMapping("")
	public String create(IssueDto issueDto) {
		issueService.add(issueDto);
		return "redirect:/issues";
	}

	@GetMapping("")
	public String showAll(Model model) {
		model.addAttribute("issue", issueService.findAll());
		return "index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable long id, Model model) {
		model.addAttribute("issue", issueService.findById(id));
		return "issue/show";
	}
}