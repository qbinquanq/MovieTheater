package com.revature.testholder;

import com.revature.beans.Transactions;

public class TransAmt {

	Transactions trans;
	int amt;
	public TransAmt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransAmt(Transactions trans, int amt) {
		super();
		this.trans = trans;
		this.amt = amt;
	}
	@Override
	public String toString() {
		return "TransAmt [trans=" + trans + ", amt=" + amt + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amt;
		result = prime * result + ((trans == null) ? 0 : trans.hashCode());
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
		TransAmt other = (TransAmt) obj;
		if (amt != other.amt)
			return false;
		if (trans == null) {
			if (other.trans != null)
				return false;
		} else if (!trans.equals(other.trans))
			return false;
		return true;
	}
	public Transactions getTrans() {
		return trans;
	}
	public void setTrans(Transactions trans) {
		this.trans = trans;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
}
