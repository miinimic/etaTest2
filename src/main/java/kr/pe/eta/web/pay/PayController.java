package kr.pe.eta.web.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.eta.service.pay.PayService;

@Controller
@RequestMapping("/pay/*")
public class PayController {

	@Autowired
	private PayService payService;

	public PayController() {
		System.out.println(this.getClass());
	}

}
