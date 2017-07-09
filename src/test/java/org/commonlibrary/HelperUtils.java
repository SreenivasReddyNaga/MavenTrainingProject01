package org.commonlibrary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperUtils {

	public static String getTimeStamp() {

		Date date = new Date();

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		return dateFormat.format(date);

	}

}
