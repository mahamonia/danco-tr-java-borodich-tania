package com.danco.dependency;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import com.danco.annotation.Injection;

public class DependencyInjection {
	
	private static DependencyInjection container;
	
	private static final String TYPE_BACKEND = "IServiceAdmin";
	private static final String TYPE_CONFIG = "IPropertyProgramm";
	private static final String TYPE_ANNOTATION = "IProcessorAnnotation";
	
	
	private DependencyInjection (){	
	}
	
	public static DependencyInjection getInstance(){
		
		if (container == null){
			container = new DependencyInjection();
		}		
		return container;
	}
	
	public void getDI(Class objectClass) {
		
		try {		
		//Class cl = object.getClass();
		
		Field [] fields = objectClass.getDeclaredFields();
		
		for (Field field : fields) {
			field.setAccessible(true);
			
			if (field.isAnnotationPresent(Injection.class)){
				Type typeField = field.getType();
				
				switch (typeField.toString()) {
				case TYPE_BACKEND:
					Class classBackEnd = Class.forName("SreviceAdmin");
					field.set(objectClass.newInstance(), classBackEnd.newInstance());
					break;
				case TYPE_CONFIG:
					Class classConfig = Class.forName("PropertyProgramm");
					field.set(objectClass.newInstance(), classConfig.newInstance());
					break;
				case TYPE_ANNOTATION:
					Class classAnnotation = Class.forName("ProcessorAnnotation");
					field.set(objectClass.newInstance(), classAnnotation.newInstance());
					break;
//				case :
//					break;
				default:
					break;
				}
			}
		}
		} catch (Exception e) {
			e.getMessage();
		}		
	}

}
