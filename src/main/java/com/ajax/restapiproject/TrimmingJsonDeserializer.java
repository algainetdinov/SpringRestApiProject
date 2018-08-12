package com.ajax.restapiproject;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import static com.fasterxml.jackson.core.JsonToken.VALUE_STRING;

/**
 * Trim any leading/trailing space at Strings
 * 
 * @author Al
 *
 */
@JsonComponent
public class TrimmingJsonDeserializer extends JsonDeserializer<String> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String deserialize(JsonParser parser, DeserializationContext context) {

		String parsedText = null;

		if (parser.hasToken(VALUE_STRING)) {
			try {
				parsedText = parser.getText().trim();
			} catch (IOException e) {
				logger.error("Json deserialization exception", e);
			}
		}

		return parsedText;
	}
}
