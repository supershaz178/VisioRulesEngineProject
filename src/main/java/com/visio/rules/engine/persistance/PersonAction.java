package com.visio.rules.engine.persistance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON_ACT")
@DiscriminatorValue("PersonAction")
public class PersonAction extends RuleAction {

	private static final long serialVersionUID = -4323304918490387480L;

	@Column(name = "CREDIT_SCR_RULE")
	private Boolean isCreditScoreAction;

	@Column(name = "CREDIT_SCR")
	private Integer creditScore;

	@Column(name = "CRD_SCR_OP")
	private String creditScoreOperation;

	public Boolean getIsCreditScoreAction() {
		return isCreditScoreAction;
	}

	public void setIsCreditScoreAction(Boolean isCreditScoreAction) {
		this.isCreditScoreAction = isCreditScoreAction;
	}

	public Integer getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

	public String getCreditScoreOperation() {
		return creditScoreOperation;
	}

	public void setCreditScoreOperation(String creditScoreOperation) {
		this.creditScoreOperation = creditScoreOperation;
	}
}
