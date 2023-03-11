package com.datapoem.pms.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {

	public static Date stringToSqlDate(String strDate) throws ParseException {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf1.parse(strDate);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		return sqlDate;
	}
}
