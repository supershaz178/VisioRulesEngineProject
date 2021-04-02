package com.visio.rules.engine.persistance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_ACT")
@DiscriminatorValue("ProductAction")
public class ProductAction extends RuleAction {

	private static final long serialVersionUID = -2714234379827999432L;

	@Column(name = "INTEREST_RT_ACT")
	private Boolean isInterestRateAction;

	@Column(name = "INTEREST_RT")
	private Double interestRate;

	@Column(name = "INT_RT_OP")
	private String interestRateOperation;

	@Column(name = "DISQUALIFY_ACT")
	private Boolean isDisqualifingAction;

	@Column(name = "DISQ_CHG_TO")
	private Boolean disqualifyChangeTo;

	public Boolean getIsInterestRateAction() {
		return isInterestRateAction;
	}

	public void setIsInterestRateAction(Boolean isInterestRateAction) {
		this.isInterestRateAction = isInterestRateAction;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public String getInterestRateOperation() {
		return interestRateOperation;
	}

	public void setInterestRateOperation(String interestRateOperation) {
		this.interestRateOperation = interestRateOperation;
	}

	public Boolean getIsDisqualifingAction() {
		return isDisqualifingAction;
	}

	public void setIsDisqualifingAction(Boolean isDisqualifingRule) {
		this.isDisqualifingAction = isDisqualifingRule;
	}

	public Boolean getDisqualifyChangeTo() {
		return disqualifyChangeTo;
	}

	public void setDisqualifyChangeTo(Boolean disqualifyChangeTo) {
		this.disqualifyChangeTo = disqualifyChangeTo;
	}
}
