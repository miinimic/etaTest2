package kr.pe.eta.web.callres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.eta.service.callres.CallResService;

@Controller
@RequestMapping("/callres/*")
public class CallResController {

	@Autowired
	private CallResService callResService;

	public CallResController() {
		System.out.println(this.getClass());
	}

}
