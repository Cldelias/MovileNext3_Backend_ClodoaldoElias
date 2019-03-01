package com.br.cldelias.enums;

public enum EnumDayWeek {

	SUNDAY(1),
	MONDAY(2),
	TUESDAY(3),
	WEDNESDAY(4),
	THURSDAY(5),
	FRIDAY(6),
	SATURDAY(7),
	;
	
	private EnumDayWeek(int dayWeek) {
		this.dayWeek = dayWeek;
	}
	
	private int dayWeek;

	public int getDayWeek() {
		return dayWeek;
	}
	
	public static boolean isDayWeekValid(Integer day) {
		for (EnumDayWeek enumDayWeek : values()) {
			if (enumDayWeek.getDayWeek() == day) {
				return true;
			}
		}
		return false;
	}
	
	public static EnumDayWeek getDayWeek(Integer day) {
		for (EnumDayWeek enumDayWeek : values()) {
			if (enumDayWeek.getDayWeek() == day) {
				return  enumDayWeek;
			}
		}
		return null;
	}
	
}
