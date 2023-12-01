package kr.pe.eta.web.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import kr.pe.eta.common.Search;
import kr.pe.eta.domain.User;
import kr.pe.eta.service.feedback.FeedbackService;
import kr.pe.eta.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private FeedbackService feedback;

	@Value("${search.pageSize}")
	private int pageSize;

	public UserController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv) throws Exception {

		System.out.println("/user/login : GET");

		mv.setViewName("redirect:/user/loginView.jsp");

		return mv;

	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user, HttpSession session) throws Exception {

		System.out.println("/user/login : POST");
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("user" + user);
		User db = userService.getUser(user.getEmail());

		System.out.println(db);
		System.out.println("code" + db.isBlockCode());

		if (user.getPwd().equals(db.getPwd()) && user.isBlockCode() == false) {
			session.setAttribute("user", db);
		}

		modelAndView.setViewName("redirect:/home.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "addUser", method = RequestMethod.GET)
	public ModelAndView addUser() throws Exception {
		System.out.println("/user/addUser : GET");
		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/user/addUserView.jsp");

		return model;
	}

	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("user") User user, HttpSession session) throws Exception {
		System.out.println("/user/addUser: POST");

		ModelAndView model = new ModelAndView();

		User newuser = userService.addUser(user);
		session.setAttribute("user", user);

		model.setViewName("redirect:/user/loginView.jsp");
		model.addObject("user", newuser);
		return model;

	}

	@RequestMapping(value = "getUser", method = RequestMethod.GET)
	public ModelAndView getUser(@RequestParam("email") String eamil) throws Exception {
		System.out.println("/user/getUser : POST");
		ModelAndView model = new ModelAndView();

		User user = userService.getUser(eamil);

		model.setViewName("forward:/home.jsp");
		model.addObject("user", user);
		return model;

	}

	@RequestMapping(value = "updateUser", method = RequestMethod.GET)
	public ModelAndView updateUserView(@RequestParam("email") String eamil, HttpSession session) throws Exception {
		System.out.println("/user/updateUser : POST");
		ModelAndView model = new ModelAndView();

		User User = userService.getUser(eamil);

		model.setViewName("forward:/user/updateUserView.jsp");
		model.addObject("user", User);
		return model;
	}

	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("user") User user, HttpSession session) throws Exception {
		System.out.println("/user/updateUser : POST");
		ModelAndView model = new ModelAndView();

		User newuser = userService.updateUser(user);

		int sessionNo = ((User) session.getAttribute("user")).getUserNo();
		if (sessionNo == newuser.getUserNo()) {
			session.setAttribute("user", newuser);
		}

		model.setViewName("forward:/user/updateUserView.jsp");
		model.addObject("user", newuser);
		return model;
	}

	@RequestMapping(value = "updatePwd", method = RequestMethod.GET)
	public ModelAndView updatePwdView(@RequestParam("email") String eamil, HttpSession session) throws Exception {
		System.out.println("/user/updatePwd : POST");
		ModelAndView model = new ModelAndView();

		User user = userService.getUser(eamil);

		model.setViewName("forward:/user/updatePwd.jsp");
		model.addObject("user", user);
		return model;

	}

	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	public ModelAndView updatePwd(@ModelAttribute("user") User user, HttpSession session) throws Exception {
		System.out.println("/user/updatePwd : POST");
		ModelAndView model = new ModelAndView();

		User newUser = userService.updatePwd(user.getPwd());

		int sessionNo = ((User) session.getAttribute("user")).getUserNo();
		if (sessionNo == user.getUserNo()) {
			session.setAttribute("user", newUser);
		}
		model.setViewName("forward:/user/updatePwd.jsp");
		model.addObject("user", newUser);
		return model;

	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpSession session) throws Exception {
		System.out.println("/user/logout : POST");
		ModelAndView model = new ModelAndView();
		session.invalidate();

		model.setViewName("redirect:/home.jsp");
		return model;
	}

	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public ModelAndView getUserList(Search search) throws Exception {
		System.out.println("/user/getLustUSer : POST");
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		userService.getUserList(search);
		ModelAndView model = new ModelAndView();

		Map<String, Object> map = userService.getUserList(search);
		System.out.println("map");
		User user = new User();

		System.out.println("code" + user.isBlockCode());
		model.addObject("list", map.get("list"));
		model.setViewName("forward:/home.jsp");
		model.addObject("search", search);

		return model;

	}

	@RequestMapping(value = "deleteUserView", method = RequestMethod.GET)
	public ModelAndView deleteUserVuew(ModelAndView model) throws Exception {
		System.out.println("/user/deleteUser : GET");

		model.setViewName("redirect:/user/deleteView.jsp");
		return null;

	}

	@RequestMapping(value = "deleteUser", method = RequestMethod.POST)
	public ModelAndView deleteUser(@ModelAttribute("detailPwd") int detailPwd, ModelAndView model, HttpSession session)
			throws Exception {

		System.out.println("/user/deleteUser : GET");

		User pwd = userService.deleteUser(detailPwd);
		model.setViewName("redirect:/user/deleteView.jsp");
		return null;

	}
}
