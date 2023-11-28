package kr.pe.eta.web.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.pe.eta.domain.Call;
import kr.pe.eta.service.community.CommunityService;

@Controller
@RequestMapping("/community/*")
public class CommunityController {

	@Autowired
	private CommunityService communityService;

	public CommunityController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value = "addReservation", method = RequestMethod.POST)
	public String addReservation(@RequestParam("callCode") String callCode, @ModelAttribute("call") Call call,
			Model model) throws Exception {

		System.out.println("/addReservation POST");
		System.out.println(call);
		model.addAttribute("call", call);

		return "/community/addReservation.jsp";
	}

	@RequestMapping(value = "addReservationReq", method = RequestMethod.GET)
	public String addReservationReq(@ModelAttribute("call") Call call, Model model) throws Exception {

		System.out.println("/addReservationReq POST");

		communityService.addReservation(call);

		return "/community/addReservation.jsp";
	}
}
