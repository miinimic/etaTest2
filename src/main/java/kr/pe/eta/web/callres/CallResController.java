package kr.pe.eta.web.callres;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.pe.eta.common.Page;
import kr.pe.eta.common.Search;
import kr.pe.eta.domain.Blacklist;
import kr.pe.eta.domain.Call;
import kr.pe.eta.domain.ShareReqPassenger;
import kr.pe.eta.domain.User;
import kr.pe.eta.service.callres.CallResService;

@Controller
@RequestMapping("/callres/*")
public class CallResController {

	@Autowired
	private CallResService callResService;

	public CallResController() {
		System.out.println(this.getClass());
	}

	@Value("${search.pageUnit}")
	private int pageUnit;

	@Value("${search.pageSize}")
	private int pageSize;

	@GetMapping("getRecordPassenger")
	public String getRecordPassenger(@RequestParam("callNo") int callNo, Model model) throws Exception {

		System.out.println("pgrpCont");
		System.out.println(callNo);
		// Business Logic
		Call call = callResService.getCallByNo(callNo);
		System.out.println("call 끝");
		User user = callResService.getUserByCallNop(callNo);
		System.out.println("user 끝");
		List<ShareReqPassenger> shares = callResService.getSharesByCallNop(callNo);
		System.out.println("shares:" + shares);
		model.addAttribute("call", call);
		model.addAttribute("user", user);
		model.addAttribute("share", shares);
		return "/callres/getRecord.jsp";
	}

	@GetMapping("getRecordDriver")
	public String getRecordDriver(@RequestParam("callNo") int callNo, Model model) throws Exception {

		System.out.println("dgrpCont");
		System.out.println(callNo);
		// Business Logic
		Call call = callResService.getCallByNo(callNo);
		User user = callResService.getUserByCallNop(callNo);
		List<ShareReqPassenger> shares = callResService.getSharesByCallNod(callNo);
		int passengerNo = callResService.getMatchByCallnod(callNo);
		Blacklist blacklist = callResService.getBlacklistByCallNod(callNo);
		// Model 과 View 연결
		model.addAttribute("call", call);
		model.addAttribute("user", user);
		model.addAttribute("share", shares);
		model.addAttribute("passengerNo", passengerNo);
		model.addAttribute("blacklist", blacklist);

		return "/callres/getRecord.jsp";
	}

	@PostMapping("callEnd")
	public String callEnd(@RequestBody Call call, Model model) throws Exception {
		call.setCallStateCode("운행후");
		System.out.println("callEnd C s");
		// Business Logic
		callResService.updateCallStateCode(call);
		System.out.println("callEnd C m");
		callResService.updateEndXY(call);
		// 실결제 service 들어와야됌
		// return feedback
		System.out.println("callEnd C e");
		return "forward:/callres/home.jsp";
	}

	@GetMapping("callAccept")
	public String callAccept(@RequestParam("callNo") int callNoString, Model model, HttpSession session)
			throws Exception {// String callNoString으로 바꾸기 나중에
		int driverNo = 1002;// session으로 사용할것
		// int callNo = Integer.parseInt(callNoString);
		int callNo = callNoString;

		Call call = callResService.getCallByNo(callNo);
		// Business Logic
		if (call.getCallCode().equals("N") || call.getCallCode().equals("D") || call.getCallCode().equals("S")) {
			call.setCallStateCode("운행중");
			callResService.updateCallStateCode(call);
			System.out.println("updatecallstatecode");
			callResService.updateMatchDriver(callNo, driverNo);
			System.out.println("updatematchdriver");
			int passengerNo = callResService.getMatchByCallnod(callNo);
			System.out.println("getmatchbycallnod");
			model.addAttribute("call", callResService.getRecordDriver(callNo));
			model.addAttribute("passengerNo", passengerNo);
			model.addAttribute("callNo", callNo);
			return "forward:/callres/driving.jsp";
		} else {
			call.setCallStateCode("예약중");
			callResService.updateCallStateCode(call);
			callResService.updateMatchDriver(callNo, driverNo);
			return "forward:/callres/getReservationList.jsp";
		}
		// 선결제 service 들어와야됌
	}

	@GetMapping(value = "getRecordList")
	public String getRecordList(@ModelAttribute Search search, Model model) throws Exception {
		System.out.println("crContRL");
		int userNo = 1004;

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		search.setSearchCondition(null);

		System.out.println(search);

		Map<String, Object> map = callResService.getRecordList(search, userNo);
		System.out.println("search::" + search);
		System.out.println("MAP:: " + map);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/callres/getRecordList.jsp";
	}

	@GetMapping(value = "getReservationList")
	public String getReservationList(@ModelAttribute Search search, Model model) throws Exception {
		System.out.println("crContRL");
		int userNo = 1004;

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		search.setSearchCondition(null);

		System.out.println(search);

		Map<String, Object> map = callResService.getReservationList(search, userNo);
		System.out.println("search::" + search);
		System.out.println("MAP:: " + map);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/callres/getResevationList.jsp";
	}

	@GetMapping(value = "getCallResList")
	public String getCallResList(@ModelAttribute Search search, Model model) throws Exception {
		System.out.println("crContRL");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		search.setSearchCondition(null);

		System.out.println(search);

		Map<String, Object> map = callResService.getCallResList(search);
		System.out.println("search::" + search);
		System.out.println("MAP:: " + map);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/callres/getCallResList.jsp";
	}

}
