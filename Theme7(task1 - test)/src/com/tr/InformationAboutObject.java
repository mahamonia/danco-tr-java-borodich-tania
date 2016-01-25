package com.tr;

import java.lang.reflect.Field;

public class InformationAboutObject {
	private Object object;
	private Class classObject;
	private Field[] fields;
	
	public InformationAboutObject(Object object) {
		this.object = object;
		this.classObject = object.getClass();

	}
	
	
	public Object getObject() {
		return object;
	}


	public void setObject(Object object) {
		this.object = object;
	}


	public Class getClassObject() {
		return object.getClass();
	}

	public void setClassObject(Class classObject) {
		this.classObject = classObject;
	}
	
	public Field [] getFields(){
		return classObject.getDeclaredFields();
	}
	
	public void setFields(Field[] fields) {
		this.fields = fields;
	}

}
