package kr.pe.eta.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	public MainController() {
		System.out.println(this.getClass());
	}

	@GetMapping("/")
	public String home() {

		System.out.println(":: MainController.home()");

		return "home.jsp";
	}
}
