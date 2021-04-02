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
@Table(name="RULE_COND")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "RULE_COND_TYPE", discriminatorType = DiscriminatorType.STRING)
public class RuleCondition implements Serializable {

	private static final long serialVersionUID = -2709040315569223057L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer condId;

	@Column(name = "COND_DESC", length = 200)
	private String description;

	// Operation Constants
	public static final String EQUAL = "EQ";
	public static final String NOT_EQUAL = "NEQ";
	public static final String LESS_THAN = "LT";
	public static final String GREATER_THAN = "GT";
	public static final String LESS_THAN_EQUAL = "LTE";
	public static final String GREATER_THAN_EQUAL = "GTE";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Integer getId() {
		return condId;
	}

	
	public void setId(Integer id) {
		this.condId = id;
	}

}
