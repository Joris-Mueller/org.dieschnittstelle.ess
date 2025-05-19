package org.dieschnittstelle.ess.basics;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;

import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.DisplayAs;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;

import static org.dieschnittstelle.ess.utils.Utils.*;

public class ShowAnnotations {

	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			showAttributes(((StockItemProxyImpl)consumable).getProxiedObject());
		}

		// we initialise a consumer
		Consumer consumer = new Consumer();
		// ... and let them consume
		consumer.doShopping(collection.getStockItems());
	}

	/*
	 * BAS2
	 */
	private static void showAttributes(Object instance) {
		show("class is: " + instance.getClass());

		Class klass = instance.getClass();
		String result = "{";
		try {

			//  BAS2: create a string representation of instance by iterating
			//  over the object's attributes / fields as provided by its class
			//  and reading out the attribute values. The string representation
			//  will then be built from the field names and field values.
			//  Note that only read-access to fields via getters or direct access
			//  is required here.

			//  BAS3: if the new @DisplayAs annotation is present on a field,
			//  the string representation will not use the field's name, but the name
			//  specified in the the annotation. Regardless of @DisplayAs being present
			//  or not, the field's value will be included in the string representation.

			// append Class Name
			String klassName = klass.getSimpleName();
			result += klassName;

			// get fields of instance and concat to string representation
			Field[] klassFields = klass.getDeclaredFields();
			ArrayList<String> klassFieldValues = new ArrayList<String>();
			for (Field field : klassFields) {
				String fieldName = field.getName();
				String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method getter = klass.getDeclaredMethod(getterName);
				Object fieldValue = getter.invoke(instance);
				if (fieldValue != null) {
					if(field.isAnnotationPresent(DisplayAs.class)) {
						fieldName = field.getAnnotation(DisplayAs.class).value();
					}
					klassFieldValues.add(fieldName + ":" + fieldValue);
				}
			}
			result += " " + String.join(", ", klassFieldValues) + "}";
			System.out.println(result);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("showAnnotations(): exception occurred: " + e,e);
		}

	}

}
