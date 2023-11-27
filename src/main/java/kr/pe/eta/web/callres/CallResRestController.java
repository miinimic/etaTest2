package kr.pe.eta.web.callres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.eta.service.callres.CallResService;

@Controller
@RequestMapping("/callres/json/*")
public class CallResRestController {

	@Autowired
	private CallResService callResService;

	public CallResRestController() {
		System.out.println(this.getClass());
	}

}
