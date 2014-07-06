package org.imsglobal.caliper.gson;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeTypeConverter {// implements JsonSerializer<DateTime> {

	private DateTimeFormatter formatter;

	public DateTimeTypeConverter() {
		formatter = ISODateTimeFormat.dateTime().withOffsetParsed();
	}

	// public JsonElement serialize(DateTime src, Type typeOfSrc,
	// JsonSerializationContext context) {
	//
	// String formatted = formatter.print(src);
	// return new JsonPrimitive(formatted);
	// }

}
