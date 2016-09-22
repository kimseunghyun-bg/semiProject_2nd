package com.boardFAQ;

public class BoardFAQDTO {
	private int listNum, num, hitCount;
	private String memberId, name;
	private String subject, content, created;
	
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
}