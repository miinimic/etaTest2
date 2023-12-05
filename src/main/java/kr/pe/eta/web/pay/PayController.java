package kr.pe.eta.web.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.pe.eta.domain.Pay;
import kr.pe.eta.service.pay.PayService;

@Controller
@RequestMapping("/pay/*")
public class PayController {

	@Autowired
	private PayService payService;

	public PayController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value = "TpayList", method = RequestMethod.GET)
	public String TpayList(@RequestParam("userNo") int userNo, Model model) throws Exception {

		System.out.println("/pay/TpayList");
		System.out.println("userNo : " + userNo);

		List<Pay> TpayList = payService.getTpayList(userNo);
		int myMoney = payService.getMyMoney(userNo);

		model.addAttribute("TpayList", TpayList);
		model.addAttribute("myMoney", myMoney);

		return "forward:/pay/TpayList.jsp";
	}

	@RequestMapping(value = "addPay", method = RequestMethod.POST)
	public String addPay(@ModelAttribute("pay") Pay pay, Model model) throws Exception {
		// 선결제, 선결제 취소, 실결제 test

		System.out.println("/pay/addPay");
		System.out.println("pay : " + pay);
		int userNo = pay.getUserNo();
		int myMoney = payService.getMyMoney(userNo);

		String payType = pay.getPayType();

		int updateMyMoney = 0;
		if (payType.equals("선결제") || payType.equals("실결제")) {
			updateMyMoney = myMoney - pay.getMoney();
			payService.updateMyMoney(userNo, updateMyMoney);
			payService.addPay(pay);
		} else if (payType.equals("선결제 취소")) {
			updateMyMoney = myMoney + pay.getMoney();
			payService.updateMyMoney(userNo, updateMyMoney);
			payService.addPay(pay);
		}

		int lastMyMoney = payService.getMyMoney(userNo);
		List<Pay> TpayList = payService.getTpayList(userNo);

		model.addAttribute("myMoney", lastMyMoney);
		model.addAttribute("TpayList", TpayList);

		return "forward:/pay/TpayList.jsp";
	}
}
