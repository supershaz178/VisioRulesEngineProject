package com.visio.rules.engine.persistance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RULES")
public class Rules implements Serializable {

	private static final long serialVersionUID = 1214663916928241728L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "condId")
	private RuleCondition condition;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "actionId")
	private RuleAction action;

	@Column(name = "DT_CREATED")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Column(name = "DT_UPDATED")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dateUpdated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public RuleCondition getCondition() {
		return condition;
	}

	public void setCondition(RuleCondition condition) {
		this.condition = condition;
	}

	public RuleAction getAction() {
		return action;
	}

	public void setAction(RuleAction action) {
		this.action = action;
	}
}
