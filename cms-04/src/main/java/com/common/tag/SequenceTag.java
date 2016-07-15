package com.common.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import com.common.condition.BaseCondition;
import com.common.constant.Constant;

/**
 * @功能说明:自定义记录序号标签
 * @author GZZ
 */
public class SequenceTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	// private final Log logger = LogFactory.getLog(SequenceTag.class);// 日志类
	int index;

	public SequenceTag() {
	}

	public int doEndTag() {
		int curPage = 0;
		int pageSize = Integer.valueOf(Constant.PAGE_SIZE);
		if (null != pageContext.getRequest().getAttribute("cond")) {
			BaseCondition cond = (BaseCondition) pageContext.getRequest().getAttribute("cond");
			curPage = cond.getCurPage();
			pageSize = cond.getPageSize();
		}
		try {
			pageContext.getOut().print((curPage - 1) * pageSize + index);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 6;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
