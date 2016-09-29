package com.adminOrder;

public class AdminOrderSubDTO {
	private String kindCode, kindName, kindParentName;
	private String panmaeNum, panmaeName, sellNum, sellPrice, totalPay;
	private String sendState, sendCorporName, sendPhoneNum;
	private String reason, returnCreated;
	
	public String getPanmaeName() {
		return panmaeName;
	}
	public void setPanmaeName(String panmaeName) {
		this.panmaeName = panmaeName;
	}
	public String getPanmaeNum() {
		return panmaeNum;
	}
	public void setPanmaeNum(String panmaeNum) {
		this.panmaeNum = panmaeNum;
	}
	public String getSendState() {
		return sendState;
	}
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	public String getKindCode() {
		return kindCode;
	}
	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getKindParentName() {
		return kindParentName;
	}
	public void setKindParentName(String kindParentName) {
		this.kindParentName = kindParentName;
	}
	public String getSellNum() {
		return sellNum;
	}
	public void setSellNum(String sellNum) {
		this.sellNum = sellNum;
	}
	public String getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(String totalPay) {
		this.totalPay = totalPay;
	}
	public String getSendCorporName() {
		return sendCorporName;
	}
	public void setSendCorporName(String sendCorporName) {
		this.sendCorporName = sendCorporName;
	}
	public String getSendPhoneNum() {
		return sendPhoneNum;
	}
	public void setSendPhoneNum(String sendPhoneNum) {
		this.sendPhoneNum = sendPhoneNum;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReturnCreated() {
		return returnCreated;
	}
	public void setReturnCreated(String returnCreated) {
		this.returnCreated = returnCreated;
	}
	
	
}
