package com.common.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <T>把json转成list
 */
public class JsonUtil {
	private static Log logger = LogFactory.getLog(JsonUtil.class);// 日志工具

	public static <T> List<T> jsonToList(String json, Class<T[]> clazz) {
		ObjectMapper mapper = new ObjectMapper();// 定义一个mapper
		T[] objects = null;
		try {
			objects = mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			logger.error("请求api时发生JsonParseException异常");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.error("请求api时发生JsonMappingException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("请求api时发生IOException异常");
			e.printStackTrace();
		}
		return Arrays.asList(objects);

	}

}
