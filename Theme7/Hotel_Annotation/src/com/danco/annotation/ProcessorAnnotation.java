package com.danco.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import com.danco.api.IProcessorAnnotation;
import com.danco.api.IPropertyProgramm;

public class ProcessorAnnotation implements IProcessorAnnotation {

	private static ProcessorAnnotation annotation;

	private static final String TYPE_INTEGER = "int";
	private static final String TYPE_BOOLEAN = "boolean";
	private static final String TYPE_STRING = "String";

	//@Injection
	//private IPropertyProgramm config;

	private ProcessorAnnotation() {

	}

	public static ProcessorAnnotation getInstance() {
		if (annotation == null) {
			annotation = new ProcessorAnnotation();
		}
		return annotation;

	}

	@Override
	public void processAnnotation(Object object) {
		Class classObject = object.getClass();
		try {
			Field[] fields = classObject.getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);// allow access

				// if field contains annotation then working with him
				if (field.isAnnotationPresent(ConfigProperty.class)) {
					// get annotation field
					ConfigProperty annotation = field
							.getAnnotation(ConfigProperty.class);

					// get file for PropertyProgramm
					String nameFile = annotation.configName();
					// !!!!!!!!!!!!!!!!!!!!!!!!!
					PropertyProgramm config = PropertyProgramm
							.getInstance(nameFile);

					// get propertyName from annotation
					String propertyName = annotation.propertyName();

					if (propertyName == null || propertyName.equals("")) {
						propertyName = object.getClass().getSimpleName() + "."
								+ field.getName();
					}

					String value = config.getConfig(propertyName);
					String type = annotation.type();

					Type typeField = field.getType();

					// change value
					switch (typeField.toString()) {
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
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
