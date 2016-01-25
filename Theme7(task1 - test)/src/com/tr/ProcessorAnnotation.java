package com.tr;

import java.lang.reflect.Field;

public class ProcessorAnnotation {
	private InformationAboutObject infoObject;

	public ProcessorAnnotation(InformationAboutObject infoObject) {
		this.infoObject = infoObject;
	}

	public void processAnnotation() {
		try {
			Field[] fields = this.infoObject.getFields();
			for (Field field : fields) {
				field.setAccessible(true);// allow access
				// if field contains annotation then working with him
				if (field.isAnnotationPresent(ConfigProperty.class)) {
					System.out.println("field: " + field.get(this.infoObject.getObject()));
					// change value
					field.set(this.infoObject.getObject(), "new name");
					System.out.println("field after change: " + field.get(this.infoObject.getObject()));
				}
				// if field is Object

				if (field.getType().getSimpleName().equals("Guest")) {// ??
					System.out.println(" ---fields object --");
					Class cl = field.getType();
					Field[] fieldsObject = cl.getDeclaredFields();

					for (Field fieldObject : fieldsObject) {
						if (fieldObject.isAnnotationPresent(ConfigProperty.class)) {
							System.out.println("field: " + fieldObject);
						}
					}
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}
}
