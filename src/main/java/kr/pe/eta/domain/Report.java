package kr.pe.eta.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Report {

	private int reportNo;
	private String reportCategory;
	private String reportDetail;
	private boolean reportCode;
	private int reportUserNo;
	private int badCallNo;
	private Date reportDate;

}
