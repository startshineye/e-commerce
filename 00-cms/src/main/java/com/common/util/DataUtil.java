package com.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @类说明:数据处理辅助工具类
 * @author GZZ
 */
public class DataUtil {
	private final static Log logger = LogFactory.getLog(DataUtil.class);

	/**
	 * @方法说明: 填充工作表数据并以excel文件输出到客户端
	 */
	public static void export(String[] headers, List<Map<String, Object>> list, HttpServletResponse response,
			String title, String operator) throws Exception {
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String((title + ".xls").getBytes(), "iso8859-1"));
		WritableWorkbook wbook = Workbook.createWorkbook(response.getOutputStream());
		if (list.size() < 65531) {
			addsheet(headers, list, title, operator, wbook, 1);
		} else {
			Map<Integer, List<Map<String, Object>>> map = splitList(list, 65530);
			for (Entry<Integer, List<Map<String, Object>>> entry : map.entrySet()) {
				addsheet(headers, entry.getValue(), title, operator, wbook, entry.getKey());
			}
			map.clear();
		}
		wbook.write();
		if (wbook != null) {
			wbook.close();
		}
	}

	public static void addsheet(String[] headers, List<Map<String, Object>> list, String title, String operator,
			WritableWorkbook wbook, int sheetIndex) throws Exception {
		DecimalFormat df = new DecimalFormat("-000");
		WritableSheet wsheet = wbook.createSheet(title + df.format(sheetIndex), sheetIndex);
		WritableCellFormat format = new WritableCellFormat();
		format.setBorder(Border.ALL, BorderLineStyle.THIN);
		int rowIndex = 2;
		int columnIndex = 0;
		for (String head : headers) {
			wsheet.addCell(new Label(columnIndex++, rowIndex, head, format));
		}
		for (Map<String, Object> map : list) {
			rowIndex++;
			columnIndex = 0;
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				wsheet.addCell(new Label(columnIndex++, rowIndex,
						null != entry.getValue() ? entry.getValue().toString() : "", format));
			}
		}
		// 设置表头信息与样式
		wsheet.mergeCells(0, 1, headers.length - 1, 1);
		format = new WritableCellFormat();
		format.setAlignment(Alignment.RIGHT);
		wsheet.addCell(new Label(0, 1, "导出时间:" + getTime() + getOperator(operator), format));
		format = new WritableCellFormat();
		format.setFont(new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false));
		format.setAlignment(Alignment.CENTRE);
		wsheet.mergeCells(0, 0, headers.length - 1, 0);
		wsheet.addCell(new Label(0, 0, title, format));
	}

	public static Map<Integer, List<Map<String, Object>>> splitList(List<Map<String, Object>> list, int num) {
		Map<Integer, List<Map<String, Object>>> map = new HashMap<Integer, List<Map<String, Object>>>();
		int mapKey = 1;
		while (list.size() > 0) {
			List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
			for (int i = 0; list.size() > 0 && i < num; i++) {
				tempList.add(list.get(0));
				list.remove(0);
			}
			map.put(mapKey++, tempList);
		}
		return map;
	}

	/**
	 * @方法说明:批量导入指定类型的List
	 */
	public static <T> int[] insertBatch(final List<T> list, JdbcTemplate jdbcTemplate, String sql) {
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) {
				try {
					T t = list.get(i);
					Field fields[] = t.getClass().getDeclaredFields();
					Method method;
					ps.setString(1, UUIDGenerator.getUUID());
					for (int j = 2; j <= fields.length; j++) {
						method = t.getClass().getDeclaredMethod("get" + firstUpper(fields[j - 1].getName()));
						if (fields[j - 1].getType().getName().contains("Integer") && null != method.invoke(t)) {
							ps.setInt(j, (Integer) method.invoke(t));
						} else if (fields[j - 1].getType().getName().contains("Date") && null != method.invoke(t)) {
							ps.setTimestamp(j, new Timestamp(((Date) method.invoke(t)).getTime()));
						} else {
							ps.setString(j, null != method.invoke(t) ? (String) method.invoke(t) : null);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("批量插入数据时出现异常!");
				}
			}

			public int getBatchSize() {
				return list.size();
			}
		});
	}

	/**
	 * @方法说明:批量导入指定类型的List(自增涨型的主键,不需给出主键,对应的INSERT中也没有PK字段)
	 */
	public static <T> int[] insertBatchNoPK(final List<T> list, JdbcTemplate jdbcTemplate, String sql) {
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) {
				try {
					T t = list.get(i);
					Field fields[] = t.getClass().getDeclaredFields();
					Method method;
					for (int j = 1; j <= fields.length; j++) {
						method = t.getClass().getDeclaredMethod("get" + firstUpper(fields[j - 1].getName()));
						if (fields[j - 1].getType().getName().contains("Integer") && null != method.invoke(t)) {
							ps.setInt(j, (Integer) method.invoke(t));
						} else if (fields[j - 1].getType().getName().contains("Date") && null != method.invoke(t)) {
							ps.setTimestamp(j, new Timestamp(((Date) method.invoke(t)).getTime()));
						} else if (fields[j - 1].getType().getName().contains("Long") && null != method.invoke(t)) {
							ps.setLong(j, (Long) method.invoke(t));
						} else if (fields[j - 1].getType().getName().contains("Float") && null != method.invoke(t)) {
							ps.setFloat(j, (Float) method.invoke(t));
						} else if (fields[j - 1].getType().getName().contains("Boolean") && null != method.invoke(t)) {
							ps.setBoolean(j, (Boolean) method.invoke(t));
						} else if (fields[j - 1].getType().getName().contains("BigDecimal")
								&& null != method.invoke(t)) {
							ps.setBigDecimal(j, (BigDecimal) method.invoke(t));
						} else if (fields[j - 1].getType().getName().contains("String") && null != method.invoke(t)) {
							ps.setString(j, (String) method.invoke(t));
						} else {
							ps.setString(j, null != method.invoke(t) ? (String) method.invoke(t) : null);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("批量插入数据时出现异常!");
				}
			}

			public int getBatchSize() {
				return list.size();
			}
		});
	}

	/**
	 * @方法说明:把excel文件转换成支持范型的List<?>
	 */
	public static <T> List<T> setList(final MultipartFile file, Class<T> cz) throws Exception {
		List<T> list = new ArrayList<T>();
		Workbook wbook = Workbook.getWorkbook(file.getInputStream());
		Sheet sheet = wbook.getSheet(0);
		T t;
		Class<?> paraClass;
		Object valueObj;
		Cell[] cell;
		Field fields[] = cz.getDeclaredFields();
		for (int i = 3; i < sheet.getRows(); i++) {
			t = cz.newInstance();
			cell = sheet.getRow(i);
			for (int j = 0; j < fields.length; j++) {
				if (!cell[j].getContents().equals("")) {
					if (fields[j].getType().getName().contains("Integer")) {
						valueObj = new Integer(cell[j].getContents());
						paraClass = Integer.class;
					} else if (fields[j].getType().getName().contains("Date")) {
						valueObj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cell[j].getContents());
						paraClass = Date.class;
					} else if (fields[j].getType().getName().contains("Boolean")) {
						valueObj = new Boolean(cell[j].getContents());
						paraClass = Boolean.class;
					} else if (fields[j].getType().getName().contains("Float")) {
						valueObj = new Float(cell[j].getContents());
						paraClass = Float.class;
					} else if (fields[j].getType().getName().contains("String")) {
						valueObj = new String(cell[j].getContents());
						paraClass = String.class;
					} else if (fields[j].getType().getName().contains("Long")) {
						valueObj = new Long(cell[j].getContents());
						paraClass = Long.class;
					} else if (fields[j].getType().getName().contains("BigDecimal")) {
						valueObj = new BigDecimal(cell[j].getContents());
						paraClass = BigDecimal.class;
					} else {
						valueObj = cell[j].getContents();
						paraClass = String.class;
					}
					cz.getDeclaredMethod("set" + firstUpper(fields[j].getName()), paraClass).invoke(t, valueObj);
				}

			}
			list.add(t);
		}
		if (wbook != null) {
			wbook.close();
		}
		return list;
	}

	public static String firstUpper(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
	}

	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public static String getOperator(String operator) {
		return null != operator && !"".equals(operator) ? "(操作员:" + operator + ")" : "";
	}

	/**
	 * @方法说明:数据库中执行的SQL语句
	 */
	public static String showSql(String sql, Object[] obj) {
		String param;
		for (int j = 0; null != obj && j < obj.length; j++) {
			param = "null";
			if (null != obj[j]) {
				String cname = obj[j].getClass().getName();
				if (cname.contains("Date") || cname.contains("Timestamp")) {
					param = "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj[j]) + "'";
				} else if (cname.contains("String")) {
					param = "'" + (String) obj[j] + "'";
				} else {
					param = obj[j].toString();
				}
			}
			sql = sql.replaceFirst("[?]", param);
		}
		return sql;
	}

	/**
	 * @方法说明:去掉对象中字符串型字段值前后的空格
	 */
	public static <T> void trim(T t) {
		try {
			Field fields[] = t.getClass().getDeclaredFields();
			String fieldName;
			Object rValue;
			Method method = null;
			for (int j = 0; j < fields.length; j++) {
				fieldName = firstUpper(fields[j].getName());
				rValue = method.invoke(t);
				method = t.getClass().getDeclaredMethod("get" + fieldName);
				if (fields[j].getType().getName().contains("String") && null != rValue && !"".equals(rValue))
					t.getClass().getDeclaredMethod("set" + fieldName, String.class).invoke(t, ((String) rValue).trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @方法说明:去掉数组中字符串型字段值前后的空格
	 */
	public static void trim(Object[] object) {
		for (int i = 0; i < object.length; i++) {
			if (object[i].getClass().getName().contains("String"))
				object[i] = object[i].toString().trim();
		}
	}

}
