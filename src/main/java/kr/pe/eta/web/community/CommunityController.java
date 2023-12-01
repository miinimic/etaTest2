package kr.pe.eta.web.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import kr.pe.eta.domain.Call;
import kr.pe.eta.domain.DealReq;
import kr.pe.eta.domain.ShareReq;
import kr.pe.eta.domain.User;
import kr.pe.eta.service.callreq.CallReqService;
import kr.pe.eta.service.community.CommunityService;
import kr.pe.eta.service.pay.PayService;

@Controller
@RequestMapping("/community/*")
public class CommunityController {

	@Autowired
	private CommunityService communityService;

	@Autowired
	private CallReqService callReqService;

	@Autowired
	private PayService payService;

	public CommunityController() {
		System.out.println(this.getClass());
	}

	@Value("${search.pageUnit}")
	int pageUnit;
	@Value("${search.pageSize}")
	int pageSize;

	@RequestMapping(value = "addReservation", method = RequestMethod.POST)
	public String addReservation(@ModelAttribute("call") Call call, Model model) throws Exception {

		System.out.println("/addReservation POST");

		model.addAttribute("call", call);

		return "/community/addReservation.jsp";
	}

	@RequestMapping(value = "addReservationReq", method = RequestMethod.POST)
	public String addReservationReq(@ModelAttribute("call") Call call, HttpSession session,
			@ModelAttribute("callTime") String callTime, Model model) throws Exception {

		System.out.println("/addReservationReq POST");

		int userNo = ((User) session.getAttribute("user")).getUserNo();
		call.setCallDate(call.getCallDate() + " " + callTime + ":00");
		communityService.addReservation(call);
		int callNo = communityService.getCallNo(userNo, call.getCallCode());
		model.addAttribute("call", call);
		model.addAttribute("callNo", callNo);

		return "forward:/callreq/searchCall.jsp";
	}

	@RequestMapping(value = "addDeal", method = RequestMethod.POST)
	public String addDeal(@ModelAttribute("call") Call call, HttpSession session, Model model) throws Exception {

		System.out.println("/addDeal POST");

		int userNo = ((User) session.getAttribute("user")).getUserNo();
		call.setUserNo(userNo);
		callReqService.addCall(call);
		int callNo = communityService.getCallNo(userNo, call.getCallCode());
		int money = call.getRealPay();

		model.addAttribute("callNo", callNo);
		model.addAttribute("money", money);

		return "/community/addDeal.jsp";
	}

	@RequestMapping(value = "addDealReq", method = RequestMethod.POST)
	public String addDealReq(@ModelAttribute("dealReq") DealReq dealReq, HttpSession session, Model model)
			throws Exception {

		System.out.println("/addDealReq POST");

		int userNo = ((User) session.getAttribute("user")).getUserNo();
		dealReq.setUserNo(userNo);
		communityService.addDealReq(dealReq);
		communityService.updateDealCode(userNo);
		Call call = communityService.getCall(dealReq.getCallNo());

		model.addAttribute("call", call);
		model.addAttribute("dealReq", dealReq);

		return "/community/getDeal.jsp";
	}

	@RequestMapping(value = "getDealReq", method = RequestMethod.POST)
	public String getDealReq(HttpSession session, Model model) throws Exception {

		System.out.println("/getDealReq POST");

		int userNo = ((User) session.getAttribute("user")).getUserNo();
		DealReq dealReq = communityService.getDeal(userNo);
		Call call = communityService.getCall(dealReq.getCallNo());

		model.addAttribute("call", call);
		model.addAttribute("dealReq", dealReq);

		return "/community/getDeal.jsp";
	}

	@RequestMapping(value = "deleteDealReq", method = RequestMethod.POST)
	public String deleteDealReq(HttpSession session, Model model) throws Exception {

		System.out.println("/deleteDealReq POST");

		int userNo = ((User) session.getAttribute("user")).getUserNo();
		communityService.deleteDealReq(userNo);
		communityService.updateDealCode(userNo);
		// 신청한 driver 삭제

		return "/index.jsp";
	}

	@RequestMapping(value = "addShare", method = RequestMethod.POST)
	public String addShare(@ModelAttribute("call") Call call, HttpSession session, Model model) throws Exception {

		System.out.println("/addShare POST");

		int userNo = ((User) session.getAttribute("user")).getUserNo();
		call.setUserNo(userNo);
		callReqService.addCall(call);
		int callNo = communityService.getCallNo(userNo, call.getCallCode());

		int maxShareCount = 4;
		if (call.getCarOpt().equals("중형")) {
			maxShareCount = 6;
		} else if (call.getCarOpt().equals("대형")) {
			maxShareCount = 7;
		}

		model.addAttribute("callNo", callNo);
		model.addAttribute("maxShareCount", maxShareCount);

		return "/community/addShare.jsp";
	}

	@RequestMapping(value = "addShareReq", method = RequestMethod.POST)
	public String addShareReq(@ModelAttribute("shareReq") ShareReq shareReq, HttpSession session, Model model)
			throws Exception {

		System.out.println("/addShareReq POST");

		int userNo = ((User) session.getAttribute("user")).getUserNo();
		shareReq.setFirstSharePassengerNo(userNo);
		communityService.addShareReq(shareReq);
		communityService.updateShareCode(userNo);
		Call call = communityService.getCall(shareReq.getCallNo());

		model.addAttribute("call", call);
		model.addAttribute("shareReq", shareReq);

		return "/community/getShareList";
	}

	@RequestMapping(value = "deleteShareReq", method = RequestMethod.POST)
	public String deleteShareReq(HttpSession session, Model model) throws Exception {

		System.out.println("/deleteShareReq POST");

		int userNo = ((User) session.getAttribute("user")).getUserNo();
		communityService.deleteShareReq(userNo);
		communityService.updateShareCode(userNo);
		// 신청한 passenger 삭제

		return "/community/getShareList";
	}

}
