package com.visio.rules.engine.persistance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.visio.rules.engine.State;

@Entity
@Table(name = "PERSON_COND")
@DiscriminatorValue("PersonCond")
public class PersonCondition extends RuleCondition {

	private static final long serialVersionUID = 3538452222700696566L;


	@Column(name = "CREDIT_SCR_RULE")
	private Boolean isCreditScoreRule;

	@Column(name = "CREDIT_SCR_CHK")
	private Integer creditScore;

	@Column(name = "CRD_SCR_OP")
	private String creditScoreOperation;

	@Column(name = "STATE_RULE")
	private Boolean isStateRule;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATE_RULE_CHK")
	private State stateCheck;

	public Boolean getIsCreditScoreRule() {
		return isCreditScoreRule;
	}

	public void setIsCreditScoreRule(Boolean isCreditScoreRule) {
		this.isCreditScoreRule = isCreditScoreRule;
	}

	public Integer getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Integer creditScoreCheck) {
		this.creditScore = creditScoreCheck;
	}

	public String getCreditScoreOperation() {
		return creditScoreOperation;
	}

	public void setCreditScoreOperation(String creditScoreOperation) {
		this.creditScoreOperation = creditScoreOperation;
	}

	public Boolean getIsStateRule() {
		return isStateRule;
	}

	public void setIsStateRule(Boolean isStateRule) {
		this.isStateRule = isStateRule;
	}

	public State getStateCheck() {
		return stateCheck;
	}

	public void setStateCheck(State stateCheck) {
		this.stateCheck = stateCheck;
	}
}
