package com.done.common;

public enum MyLeagueEnum {
	ONGOING(0), AGREE(1), DENY(2), NONE(3),DONE(4),THESAME(5), ERROR(6);

	private Integer num;

	public Integer getNum() {
		return num;
	}

	MyLeagueEnum(Integer num) {
		this.num = num;
	}
	public static void main(String[] args) {
		System.out.println(MyLeagueEnum.NONE.getNum());
	}
}
