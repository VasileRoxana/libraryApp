package net.javaguides.springboot.web;

import net.javaguides.springboot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	//@GetMapping("/")
	//public String home() {
	//	return "index";
	//}
	@GetMapping("/")
	public RedirectView redirectWithUsingRedirectView(
			RedirectAttributes attributes) {
		attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
		//attributes.addAttribute("attribute", "redirectWithRedirectView");
		return new RedirectView("/books/all");
	}

}
