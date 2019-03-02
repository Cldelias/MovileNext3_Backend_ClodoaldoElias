package br.com.cldelias.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.br.cldelias.enums.EnumPatternFormatDate;

public class DateUtil {
	
	
	public static String formatLocalDateTime(LocalDateTime localDateTime, EnumPatternFormatDate pattern) {
		String text = "";
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getPattern());
			text = localDateTime.format(formatter);
		} catch (Exception e) {
			text = "";
		}
		return text;
	}
	
	public static String formatLocalTime(LocalTime localTime, EnumPatternFormatDate pattern) {
		String text = "";
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getPattern());
			text = localTime.format(formatter);
		} catch (Exception e) {
			text = "";
		}
		return text;
	}

	public static LocalTime parseLocalTime(String text, EnumPatternFormatDate pattern) {
		LocalTime localTime = null;
		try {
			if (text != null && pattern != null) {
				localTime = LocalTime.parse(text, DateTimeFormatter.ofPattern(pattern.getPattern()));
				
			}
		} catch (Exception e) {
			localTime = null;
		}
		return localTime;
	}

	public static boolean isLocalTimeValid(String text, EnumPatternFormatDate pattern) {
		return parseLocalTime(text, pattern) != null;
	}

}
