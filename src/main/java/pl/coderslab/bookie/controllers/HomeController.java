package pl.coderslab.bookie.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {		
	@RequestMapping("/")
	public String home() {
		return "index";
	}
@ResponseBody
@GetMapping("/sprawdz")
public String loginConfirm(Principal principal) {
	if(principal.getName().contains("admin")){
		return "admin i chuj";
	}
	else {
		return "zwykly user i chuj";
	}
}
	

}
