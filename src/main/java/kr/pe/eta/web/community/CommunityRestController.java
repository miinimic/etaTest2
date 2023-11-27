package kr.pe.eta.web.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.eta.service.community.CommunityService;

@Controller
@RequestMapping("/community/json/*")
public class CommunityRestController {

	@Autowired
	private CommunityService communityService;

	public CommunityRestController() {
		System.out.println(this.getClass());
	}

}
