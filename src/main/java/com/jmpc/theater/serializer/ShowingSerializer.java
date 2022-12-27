package com.jmpc.theater.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jmpc.theater.pojo.Showing;
import com.jmpc.theater.util.CustomStringUtils;

/**
 * Custom serializer for Showing object, mainly to display running time in human readable format 
 * @author vinayakbhope
 *
 */
public class ShowingSerializer extends StdSerializer<Showing> {
	
	private CustomStringUtils utils = new CustomStringUtils();

	public ShowingSerializer() {
        this(null);
    }
	
	public ShowingSerializer(Class<Showing> t) {
        super(t);
    }
	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(Showing value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeNumberField("sequenceOfTheDay", value.getSequenceOfTheDay());
		jgen.writeStringField("showStartTime", value.getShowStartTime().toString());
		jgen.writeStringField("title", value.getMovie().getTitle());
		jgen.writeStringField("runningTime", utils.humanReadableFormat(value.getMovie().getRunningTime()));
		jgen.writeNumberField("ticketPrice", value.getTicketPrice());
		jgen.writeEndObject();
	}

}
