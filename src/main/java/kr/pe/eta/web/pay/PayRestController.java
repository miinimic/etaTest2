package kr.pe.eta.web.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.eta.service.pay.PayService;

@Controller
@RequestMapping("/pay/json/*")
public class PayRestController {

	@Autowired
	private PayService payService;

	public PayRestController() {
		System.out.println(this.getClass());
	}

}
