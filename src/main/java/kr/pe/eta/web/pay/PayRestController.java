package kr.pe.eta.web.pay;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.eta.service.pay.PayService;

@RestController
@RequestMapping("/pay/json/*")
public class PayRestController {

	@Autowired
	private PayService payService;

	public PayRestController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value = "addCharge", method = RequestMethod.POST)
	public Map<String, Object> addCharge(@RequestParam("userNo") int userNo, @RequestParam("Tpay") String Tpay)
			throws Exception {
		Map<String, Object> result = new HashMap<>();
		try {
			int tpay = Integer.parseInt(Tpay);

			payService.addCharge(userNo, tpay); // addCharge()
			System.out.println("addCharge UserNo: " + userNo + ", Tpay: " + Tpay);
			int myMoney = payService.getMyMoney(userNo); // getMyMoney()

			int updateMyMoney = myMoney + tpay;
			payService.updateMyMoney(userNo, updateMyMoney); // updateMyMoney()

			result.put("success", true);
			result.put("message", "충전이 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", "충전 중 오류가 발생했습니다. " + e.getMessage());
		}

		return result;

	}

}
