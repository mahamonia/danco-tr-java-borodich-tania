package com.danco.dependency;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import com.danco.annotation.Injection;
import com.danco.annotation.ProcessorAnnotation;
import com.danco.api.IServiceAdmin;
import com.danco.config.PropertyProgramm;
import com.danco.training.services.ServiceAdmin;

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
					field.set(objectClass.newInstance(), ServiceAdmin.class.newInstance());
					break;
				case TYPE_CONFIG:
					field.set(objectClass.newInstance(), PropertyProgramm.class.newInstance());
					break;
				case TYPE_ANNOTATION:
					field.set(objectClass.newInstance(), ProcessorAnnotation.class.newInstance());
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
