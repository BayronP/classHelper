package com.vote.domain;

import java.io.Serializable;

public class ProDomain implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String code;
	String title;
	String problemDesc;
	String problemSelect;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((problemDesc == null) ? 0 : problemDesc.hashCode());
		result = prime * result
				+ ((problemSelect == null) ? 0 : problemSelect.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProDomain other = (ProDomain) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (problemDesc == null) {
			if (other.problemDesc != null)
				return false;
		} else if (!problemDesc.equals(other.problemDesc))
			return false;
		if (problemSelect == null) {
			if (other.problemSelect != null)
				return false;
		} else if (!problemSelect.equals(other.problemSelect))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProDomain [code=" + code + ", title=" + title
				+ ", problemDesc=" + problemDesc + ", problemSelect="
				+ problemSelect + ", getCode()=" + getCode() + ", getTitle()="
				+ getTitle() + ", getProblemDesc()=" + getProblemDesc()
				+ ", getProblemSelect()=" + getProblemSelect()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProblemDesc() {
		return problemDesc;
	}
	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}
	public String getProblemSelect() {
		return problemSelect;
	}
	public void setProblemSelect(String problemSelect) {
		this.problemSelect = problemSelect;
	}

}
