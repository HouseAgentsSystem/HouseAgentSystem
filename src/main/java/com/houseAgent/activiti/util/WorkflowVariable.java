package com.houseAgent.activiti.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import jodd.util.StringUtil;


public class WorkflowVariable {
	private String keys;
    private String values;
    private String types;
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public Map<String, Object> getVariableMap() {
		Map<String, Object> vars = new HashMap<String, Object>();

        ConvertUtils.register(new DateConverter(), java.util.Date.class);
        if (StringUtil.isBlank(keys)) {
            return vars;
        }
        //解决split丢失结尾空字符串的问题
        //继续切分,保留null值
        String[] arrayKey = keys.split(",", -1);
        String[] arrayValue = values.split(",", -1);
        String[] arrayType = types.split(",", -1);
        for (int i = 0; i < arrayKey.length; i++) {
            if ("".equals(arrayKey[i]) || "".equals(arrayValue[i]) || "".equals(arrayType[i])) {
                continue;
            }
            String key = arrayKey[i];
            String value = arrayValue[i];
            String type = arrayType[i];
            
            Class<?> targetType = Enum.valueOf(PropertyType.class, type).getValue();
            Object objectValue = ConvertUtils.convert(value, targetType);
            vars.put(key, objectValue);
        }
        return vars;
	}
}
