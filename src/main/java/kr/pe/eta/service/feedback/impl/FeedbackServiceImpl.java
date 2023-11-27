package kr.pe.eta.service.feedback.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.eta.service.feedback.FeedbackDao;
import kr.pe.eta.service.feedback.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;

	public FeedbackServiceImpl() {
		System.out.println(this.getClass());
	}

}
