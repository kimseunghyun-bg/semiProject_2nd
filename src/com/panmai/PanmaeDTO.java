package com.panmai;

public class PanmaeDTO {
	private int panmae_num, jumun_num,basket_num; 
	private String memberId; //��ǰ��ȣ, �ֹ���ȣ, ��ٱ��� �Ϸù�ȣ, ȸ�����̵�, �̹��������̸�
	public String getMemberId() {
		return memberId;
	}
	public int getBasket_num() {
		return basket_num;
	}
	public void setBasket_num(int basket_num) {
		this.basket_num = basket_num;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	private String kind_code, produce_code, name, introduce, created, produce_corpor_name; // �з��ڵ�, �������ڵ�, ��ǰ��, �Ұ���, �����, �����ü��
	private int save_num, price, buy_num, total; // �ܿ�����, �ܰ�, ���ż���, �ѱݾ�
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}
	public int getJumun_num() {
		return jumun_num;
	}
	public void setJumun_num(int jumun_num) {
		this.jumun_num = jumun_num;
	}
	public String getProduce_corpor_name() {
		return produce_corpor_name;
	}
	public void setProduce_corpor_name(String produce_corpor_name) {
		this.produce_corpor_name = produce_corpor_name;
	}
	private String panmae_state, image; // ��ǰ����, �̹���;
	public int getPanmae_num() {
		return panmae_num;
	}
	public void setPanmae_num(int panmae_num) {
		this.panmae_num = panmae_num;
	}
	public String getKind_code() {
		return kind_code;
	}
	public void setKind_code(String kind_code) {
		this.kind_code = kind_code;
	}
	public String getProduce_code() {
		return produce_code;
	}
	public void setProduce_code(String produce_code) {
		this.produce_code = produce_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public int getSave_num() {
		return save_num;
	}
	public void setSave_num(int save_num) {
		this.save_num = save_num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPanmae_state() {
		return panmae_state;
	}
	public void setPanmae_state(String panmae_state) {
		this.panmae_state = panmae_state;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}