package org.imsglobal.caliper.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

public class DateTimeSerializer extends StdScalarSerializer<DateTime> {

    public DateTimeSerializer() {
        super(DateTime.class);
    }

    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider provider)
                                                         throws IOException, JsonGenerationException {

        String dateTimeAsString = ISODateTimeFormat.dateTime().withZoneUTC().print(dateTime);
        //String dateTimeAsString = ISODateTimeFormat.dateHourMinuteSecondMillis().withZoneUTC().print(dateTime);
        jsonGenerator.writeString(dateTimeAsString);
    }
}