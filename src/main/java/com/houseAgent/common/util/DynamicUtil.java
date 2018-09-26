package com.houseAgent.common.util;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class DynamicUtil {
	/**
	 * 根据 属性名，给实体类赋值
	 * @param entity 实体类
	 * @param propertyNames 属性名
	 * @param qtySums 属性值
	 */
	public static<T> void dynamicSet(T entity, List<Object> propertyNames, List<Object> qtySums) {
		try {
			
			for (int i = 0; i < propertyNames.size() ; i++) {
				Field field  = entity.getClass().getDeclaredField((String)propertyNames.get(i));
				field.setAccessible(true);			
				field.set(entity, qtySums.get(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}
	
}
