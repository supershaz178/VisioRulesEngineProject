package com.visio.rules.engine.persistance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="RULE_ACT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "RULE_ACT_TYPE", discriminatorType = DiscriminatorType.STRING)
public class RuleAction implements Serializable {

	private static final long serialVersionUID = -8346047119955464447L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer actionId;

	@Column(name = "ACT_DESC", length = 200)
	private String description;

	// Operation Constants
	public static final String INCREASE_RT = "INC";
	public static final String DECREASE_RT = "DEC";
	public static final String DISQUALIFY = "DISQ";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return actionId;
	}

	public void setId(Integer id) {
		this.actionId = id;
	}

}
