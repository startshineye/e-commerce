package com.common.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yxm.cms.sys.dict.model.Dict;
import com.yxm.cms.sys.dict.model.DictCond;
import com.yxm.cms.sys.dict.service.IDictService;
import com.yxm.cms.sys.param.model.Param;
import com.yxm.cms.sys.param.model.ParamCond;
import com.yxm.cms.sys.param.service.IParamService;

/**
 * @类说明:字典/参数辅助工具类
 * @author GZZ
 */
@Repository
public class DictiParamUtil {
	@Autowired
	private IDictService dictService;

	@Autowired
	private IParamService paramService;

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(DictiParamUtil.class);

	/**
	 * @功能描述: 构建下拉框参照Map
	 */
	public Map<Integer, Object> getDictMap(Integer typeCode, boolean addBlank) {
		Map<Integer, Object> map = new LinkedHashMap<Integer, Object>();
		if (addBlank) {
			map.put(null, "--请选择--");
		}
		for(Dict vo : dictService.queryAllObj(new DictCond(new Object[][] { { "type_code_c", typeCode } }))) {
			map.put(vo.getData_key(), vo.getData_value());
		}
		return map;
	}

	/**
	 * @功能描述: 按类型与关键字查找对象
	 */
	public Dict findDict(Integer typecode, Integer datakey) {
		Dict dict = null;
		List<Dict> list = dictService
				.queryAllObj(new DictCond(new Object[][] { { "type_code_c", typecode }, { "data_key_c", datakey } }));
		if (list.size() > 0) {
			dict = list.get(0);
		}
		return dict;
	}
	/**
	 * @功能描述: 按类型与关键字查找对象
	 */
	public String findDictValue(Integer typecode, Integer datakey) {
		if (null != typecode && !typecode.equals("") && datakey != null && !datakey.equals("")) {
			Dict dict = findDict(typecode, datakey);
			return dict.getData_value();
		} else {
			return "";
		}
	}

	/**
	 * @功能描述: 参数表按键找值
	 */
	public String findValue(String key) {
		String svalue = "";
		ParamCond cond = new ParamCond();
		cond.setParam_key_c(key);
		List<Param> list = paramService.queryAllObj(cond);
		if (null != list && list.size() > 0) {
			svalue = list.get(0).getParam_value();
		}
		return svalue;
	}
}
