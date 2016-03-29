package com.danco.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.backend.IProcessorAnnotation;
import com.danco.api.backend.IProcessorProperty;
import com.danco.config.ProcessorProperty;

public class ProcessorAnnotation implements IProcessorAnnotation {

	private static final String TYPE_INTEGER = "int";
	private static final String TYPE_BOOLEAN = "boolean";
	private static final String TYPE_STRING = "java.lang.String";
	
	public static Map<String, IProcessorProperty> createdPropertys = new HashMap<String, IProcessorProperty>();
	
	private static final Logger LOGGER = LogManager
			.getLogger(ProcessorAnnotation.class);

	public ProcessorAnnotation() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void processAnnotation(Object object) {
		Class classObject = object.getClass();
		try {
			Field[] fields = classObject.getDeclaredFields();

			for (Field field : fields) {
				int modifier = field.getModifiers();
				field.setAccessible(true);// allow access

				// if field contains annotation then working with him
				if (field.isAnnotationPresent(ConfigProperty.class)) {
					// get annotation field
					ConfigProperty annotation = field
							.getAnnotation(ConfigProperty.class);

					// get file for PropertyProgramm
					String nameFile = annotation.configName();
					
					IProcessorProperty config = null;
					
					if (createdPropertys.containsKey(nameFile)) {
						config = createdPropertys.get(nameFile);
					} else {
						config = new ProcessorProperty(nameFile);
						createdPropertys.put(nameFile, config);
					}
					
					// get propertyName from annotation
					String propertyName = annotation.propertyName();

					if (propertyName == null || propertyName.isEmpty()) {
						propertyName = object.getClass().getSimpleName() + "."
								+ field.getName();
					}

					String value = config.getConfig(propertyName);
					Type typeField = field.getType();

					// change value
					switch (typeField.getTypeName()) {
					case TYPE_INTEGER:
						field.set(object, Integer.parseInt(value));
						break;
					case TYPE_BOOLEAN:
						field.set(object, Boolean.valueOf(value));
						break;
					case TYPE_STRING:
						field.set(object, value);
						break;
					default:
						processAnnotation(field);
						break;
					}
				}	
				if (Modifier.isPrivate(modifier)){
					field.setAccessible(false);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
