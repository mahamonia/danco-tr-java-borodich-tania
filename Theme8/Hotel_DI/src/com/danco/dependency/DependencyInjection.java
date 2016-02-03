package com.danco.dependency;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.Injection;
import com.danco.config.ProcessorProperty;


public class DependencyInjection {

	private static DependencyInjection container;

	private static final Logger LOGGER = LogManager
			.getLogger(DependencyInjection.class);

	private static final String FILE = "DI.properties";
	private ProcessorProperty config = new ProcessorProperty(FILE);

	public static Map<String, Object> createdObjects = new HashMap<String, Object>();

	private DependencyInjection() {
	}

	public static DependencyInjection getInstance() {

		if (container == null) {
			container = new DependencyInjection();
		}
		return container;
	}

	@SuppressWarnings("rawtypes")
	public void getDI(Object ... objects) {
		for (Object object : objects) {

		try {
			Class cl = object.getClass();

			Field[] fields = cl.getDeclaredFields();

			for (Field field : fields) {
				int modifier = field.getModifiers();
				field.setAccessible(true);

				if (field.isAnnotationPresent(Injection.class)) {

					String typeField = field.getType().getName();
					
					Object newObject = null;

					if (createdObjects.containsKey(typeField)) {
						newObject = createdObjects.get(typeField);
					} else {
						newObject = Class.forName(config.getConfig(typeField)).newInstance();
						createdObjects.put(typeField, newObject);
					}
					field.set(object, newObject);
				}
				if (Modifier.isPrivate(modifier)) {
					field.setAccessible(false);
				}
			}
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
		}
	}
	}
}
