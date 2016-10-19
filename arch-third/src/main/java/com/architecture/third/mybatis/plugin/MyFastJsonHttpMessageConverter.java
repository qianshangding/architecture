package com.architecture.third.mybatis.plugin;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

public class MyFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {

	private String dateFormat = "yyyy-MM-dd";

	public MyFastJsonHttpMessageConverter() {
		super();
	}

	public MyFastJsonHttpMessageConverter(String dateFormat) {
		super();
		this.dateFormat = dateFormat;
	}

	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		OutputStream out = outputMessage.getBody();
		String text = JSON.toJSONStringWithDateFormat(obj, dateFormat, super.getFeatures());
		byte[] bytes = text.getBytes(super.getCharset());
		out.write(bytes);
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

}
