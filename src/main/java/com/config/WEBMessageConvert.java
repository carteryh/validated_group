package com.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WEBMessageConvert {
	/**
	 * 使用bean注入,才能使其有效果,验证的话就在Entity字段中使用fastjson的 注解@JSONField(serialize =
	 * false),转换出来的信息不含该字段,则成功
	 * 
	 * @return
	 */
	@Bean
	public HttpMessageConverters customConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(supportedMediaTypes);
		return new HttpMessageConverters((HttpMessageConverter<?>) fastConverter);
	}
}
