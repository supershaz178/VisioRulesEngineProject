package com.visio.rules.engine.persistance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_COND")
@DiscriminatorValue("ProductCond")
public class ProductCondition extends RuleCondition {

	private static final long serialVersionUID = 1833897094077119369L;
	
	@Column(name = "NAME_RULE")
	private Boolean isNameRule;

	@Column(name = "NAME")
	private String name;

	@Column(name = "NAME_OP")
	private String nameOperation;

	@Column(name = "INTEREST_RT_RULE")
	private Boolean isInterestRateRule;

	@Column(name = "INTEREST_RT")
	private Double interestRate;

	@Column(name = "INT_RT_OP")
	private String interestRateOperation;

	@Column(name = "DISQUALIFY_RULE")
	private Boolean isDisqualifyingRule;

	@Column(name = "DISQUALIFY_CHK")
	private Boolean disqualifyingCheck;

	public Boolean getIsNameRule() {
		return isNameRule;
	}

	public void setIsNameRule(Boolean isNameRule) {
		this.isNameRule = isNameRule;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsInterestRateRule() {
		return isInterestRateRule;
	}

	public void setIsInterestRateRule(Boolean isInterestRateRule) {
		this.isInterestRateRule = isInterestRateRule;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Boolean getIsDisqualifingRule() {
		return isDisqualifyingRule;
	}

	public void setIsDisqualifingRule(Boolean isDisqualifingRule) {
		this.isDisqualifyingRule = isDisqualifingRule;
	}

	public Boolean getDisqualifyingCheck() {
		return disqualifyingCheck;
	}

	public void setDisqualifyingCheck(Boolean disqualifyingCheck) {
		this.disqualifyingCheck = disqualifyingCheck;
	}

	public String getNameOperation() {
		return nameOperation;
	}

	public void setNameOperation(String nameOperation) {
		this.nameOperation = nameOperation;
	}

	public String getInterestRateOperation() {
		return interestRateOperation;
	}

	public void setInterestRateOperation(String interestRateOperation) {
		this.interestRateOperation = interestRateOperation;
	}
}
