package com.br.cldelias.enums;

public enum EnumTypeOperation {

	
	SCHEDULED(1, "SCHEDULED"),
	ALERT(2, "ALERT"),
	;
	
	private EnumTypeOperation(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	private int id;
	private String description;
	
	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static boolean isTypeOperationValid(Integer type) {
		for (EnumTypeOperation enumType : values()) {
			if (enumType.getId() == type) {
				return true;
			}
		}
		return false;
	}
	
	public static EnumTypeOperation getTypeOperation(Integer type) {
		for (EnumTypeOperation enumType : values()) {
			if (enumType.getId() == type) {
				return enumType;
			}
		}
		return null;
	}
	
	public static String getTypeDescrptionOperation(Integer type) {
		for (EnumTypeOperation enumType : values()) {
			if (enumType.getId() == type) {
				return enumType.getDescription();
			}
		}
		return "";
	}



}
