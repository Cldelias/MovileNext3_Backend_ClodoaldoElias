package com.br.cldelias.enums;

public enum EnumPatternFormatDate {
	
	
	FORMAT_01("dd-MM-yyyy HH:mm:ss"),
	FORMAT_02("HH:mm")
	;
	
	private EnumPatternFormatDate(String pattern) {
		this.pattern = pattern;
	}

	private String pattern;
	
	public String getPattern() {
		return pattern;
	}
	
	
}
