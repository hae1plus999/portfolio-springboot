package com.portfolio.framework.util;

import org.apache.commons.lang3.ObjectUtils;

import com.portfolio.mvc.domain.BaseCodeLabelEnum;

public class EnumUtils {

	/**
	 * values 파라미터로 넘어온 선택된 값들
	 * @param values
	 * @param codeEnum
	 * @return
	 */
	public static boolean isSelected(BaseCodeLabelEnum[] values, BaseCodeLabelEnum codeEnum) {
		
		if(ObjectUtils.isEmpty(values)) {
			return false;
		}
		for(BaseCodeLabelEnum value : values) {
			//동일화면
			if(value.code().equals(codeEnum.code())) {
				return true;
			}
		}
		return false;
	}
}
