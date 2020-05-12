package org.star_pet_house_commons.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Date;

/*
 *@description:
 *@author jiafeng
 *@date 2020/5/11 0011 20:57
 */
public class DateStandardFormateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DateUtil.format(date, DateUtil.DATE_TIME_FORMAT));
    }
}
