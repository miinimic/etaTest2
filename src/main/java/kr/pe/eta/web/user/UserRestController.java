package kr.pe.eta.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.eta.service.user.UserService;

@Controller
@RequestMapping("/user/json/*")
public class UserRestController {

	@Autowired
	private UserService userService;

	public UserRestController() {
		System.out.println(this.getClass());
	}

}
