package xyz.chichistudy.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PageController {
	@GetMapping("/")
	public String index() {
		return "psi_tables";
	}
}
