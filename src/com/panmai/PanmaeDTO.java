package com.panmai;

public class PanmaeDTO {
	private int panmae_num, jumun_num,basket_num, delivery_code, sell_num, send_state, sell_price, send_code ; 
	private String memberId; //상품번호, 주문번호, 장바구니 일련번호, 회원아이디, 이미지파일이름
	private String kind_code, produce_code, name, introduce, created, produce_corpor_name; // 분류코드, 생산자코드, 상품명, 소개글, 등록일, 생산업체명
	private int save_num, price, buy_num, total; // 잔여수량, 단가, 구매수량, 총금액
	private String jumun_created, pay_state, pay_root, getname, addr1, addr2, zip, phone_1, phone_2, phone_3, bank, memo;
	
	
	public String getAddr1() {
		return addr1;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone_1() {
		return phone_1;
	}
	public void setPhone_1(String phone_1) {
		this.phone_1 = phone_1;
	}
	public String getPhone_2() {
		return phone_2;
	}
	public void setPhone_2(String phone_2) {
		this.phone_2 = phone_2;
	}
	public String getPhone_3() {
		return phone_3;
	}
	public void setPhone_3(String phone_3) {
		this.phone_3 = phone_3;
	}
	public String getGetname() {
		return getname;
	}
	public void setGetname(String getname) {
		this.getname = getname;
	}
	public String getPay_root() {
		return pay_root;
	}
	public void setPay_root(String pay_root) {
		this.pay_root = pay_root;
	}
	public String getPay_state() {
		return pay_state;
	}
	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	public String getJumun_created() {
		return jumun_created;
	}
	public void setJumun_created(String jumun_created) {
		this.jumun_created = jumun_created;
	}
	public int getDelivery_code() {
		return delivery_code;
	}
	public void setDelivery_code(int delivery_code) {
		this.delivery_code = delivery_code;
	}
	public int getSell_num() {
		return sell_num;
	}
	public void setSell_num(int sell_num) {
		this.sell_num = sell_num;
	}
	public int getSend_state() {
		return send_state;
	}
	public void setSend_state(int send_state) {
		this.send_state = send_state;
	}
	public int getSell_price() {
		return sell_price;
	}
	public void setSell_price(int sell_price) {
		this.sell_price = sell_price;
	}
	public int getSend_code() {
		return send_code;
	}
	public void setSend_code(int send_code) {
		this.send_code = send_code;
	}
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
	private String panmae_state, image; // 상품상태, 이미지;
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
