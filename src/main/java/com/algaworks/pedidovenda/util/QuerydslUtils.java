package com.algaworks.pedidovenda.util;

import java.lang.reflect.Field;

import com.querydsl.core.types.EntityPath;

public class QuerydslUtils {

	@SuppressWarnings("unchecked")
	public static <T> EntityPath<T> getEntityPath(Class<T> entityClass) {
		Class<?> queryClass = getQueryClass(entityClass);
		try {
			String fieldName = firstToLower(entityClass.getSimpleName());
			Field field = queryClass.getField(fieldName);
			return (EntityPath<T>) field.get(entityClass);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new IllegalStateException("failed to access query class " + queryClass.getName(), e);
		}
	}

	private static String firstToLower(String name) {
		return Character.toLowerCase(name.charAt(0)) + name.substring(1);
	}

	public static Class<?> getQueryClass(Class<?> entityClass) {
		String queryClassName = entityClass.getPackage().getName() + ".Q" + entityClass.getSimpleName();
		try {
			return entityClass.getClassLoader().loadClass(queryClassName);
		} catch (ClassNotFoundException | SecurityException | IllegalArgumentException e) {
			throw new IllegalStateException("unable to find query class " + queryClassName, e);
		}
	}
}
