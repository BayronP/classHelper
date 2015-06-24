package com.vote.domain;

public class SolutionDomain {
	String code;
	String ans;
	@Override
	public String toString() {
		return "SolutionDomain [code=" + code + ", ans=" + ans
				+ ", hashCode()=" + hashCode() + ", getCode()=" + getCode()
				+ ", getAns()=" + getAns() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ans == null) ? 0 : ans.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		SolutionDomain other = (SolutionDomain) obj;
		if (ans == null) {
			if (other.ans != null)
				return false;
		} else if (!ans.equals(other.ans))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	
}
