package kr.pe.eta.web.callreq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.eta.service.callreq.CallReqService;

@Controller
@RequestMapping("/callreq/json/*")
public class CallReqRestController {

	@Autowired
	private CallReqService callReqService;

	public CallReqRestController() {
		System.out.println(this.getClass());
	}

}
