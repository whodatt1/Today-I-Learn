package com.example.redis.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/*
  GenericJackson2JsonRedisSerializer 사용시 클래스 타입에 상관없이 직렬화/역직렬화
  가능하지만 날짜타입에 지원되지 않아 ObjectMapper 커스텀 
 */
@Configuration
public class MapperConfig {
	
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	@Bean
	ObjectMapper serializingObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer())
					  .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
		
		objectMapper.registerModules(javaTimeModule, new Jdk8Module())
					.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
					.activateDefaultTyping(
							BasicPolymorphicTypeValidator.builder()
														 .allowIfBaseType(Object.class).build(),
							ObjectMapper.DefaultTyping.NON_FINAL
					);
		
		return objectMapper;
	}
	
	// 직렬화
	public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

		@Override
		public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException {
			gen.writeString(value.format(FORMATTER));
		}
	}
	
	// 역직렬화
	public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

		@Override
		public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JacksonException {
			return LocalDateTime.parse(p.getValueAsString(), FORMATTER);
		}
	}
}
