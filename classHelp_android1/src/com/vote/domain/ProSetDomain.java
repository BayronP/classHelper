package com.vote.domain;

import java.io.Serializable;

public class ProSetDomain implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String problemSetCode;
	String problemSetDesc;
	String problemsAns;
	String username;
	String problems;
	String solutions;
	
	
	@Override
	public String toString() {
		return "ProSetDomain [problemSetCode=" + problemSetCode
				+ ", problemSetDesc=" + problemSetDesc + ", problemsAns="
				+ problemsAns + ", username=" + username + ", problems="
				+ problems + ", solutions=" + solutions + ", hashCode()="
				+ hashCode() + ", getProblemSetCode()=" + getProblemSetCode()
				+ ", getProblemSetDesc()=" + getProblemSetDesc()
				+ ", getProblemsAns()=" + getProblemsAns() + ", getUsername()="
				+ getUsername() + ", getProblems()=" + getProblems()
				+ ", getSolutions()=" + getSolutions() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((problemSetCode == null) ? 0 : problemSetCode.hashCode());
		result = prime * result
				+ ((problemSetDesc == null) ? 0 : problemSetDesc.hashCode());
		result = prime * result
				+ ((problems == null) ? 0 : problems.hashCode());
		result = prime * result
				+ ((problemsAns == null) ? 0 : problemsAns.hashCode());
		result = prime * result
				+ ((solutions == null) ? 0 : solutions.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		ProSetDomain other = (ProSetDomain) obj;
		if (problemSetCode == null) {
			if (other.problemSetCode != null)
				return false;
		} else if (!problemSetCode.equals(other.problemSetCode))
			return false;
		if (problemSetDesc == null) {
			if (other.problemSetDesc != null)
				return false;
		} else if (!problemSetDesc.equals(other.problemSetDesc))
			return false;
		if (problems == null) {
			if (other.problems != null)
				return false;
		} else if (!problems.equals(other.problems))
			return false;
		if (problemsAns == null) {
			if (other.problemsAns != null)
				return false;
		} else if (!problemsAns.equals(other.problemsAns))
			return false;
		if (solutions == null) {
			if (other.solutions != null)
				return false;
		} else if (!solutions.equals(other.solutions))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
	public String getProblemSetCode() {
		return problemSetCode;
	}
	public void setProblemSetCode(String problemSetCode) {
		this.problemSetCode = problemSetCode;
	}
	public String getProblemSetDesc() {
		return problemSetDesc;
	}
	public void setProblemSetDesc(String problemSetDesc) {
		this.problemSetDesc = problemSetDesc;
	}
	public String getProblemsAns() {
		return problemsAns;
	}
	public void setProblemsAns(String problemsAns) {
		this.problemsAns = problemsAns;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProblems() {
		return problems;
	}
	public void setProblems(String problems) {
		this.problems = problems;
	}
	public String getSolutions() {
		return solutions;
	}
	public void setSolutions(String solutions) {
		this.solutions = solutions;
	}
	
}
