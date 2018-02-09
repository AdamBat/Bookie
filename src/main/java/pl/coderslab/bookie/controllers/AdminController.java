package pl.coderslab.bookie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.bookie.entities.Sport;
import pl.coderslab.bookie.repositories.SportRepository;
import pl.coderslab.bookie.service.SportService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	

	@RequestMapping("/addSport")
	public String addSport(Model model) {
		model.addAttribute("sport", new Sport());
		return "admin/add-sport";
	}
}
