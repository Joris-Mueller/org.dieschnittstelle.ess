package org.dieschnittstelle.ess.basics;


import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.DisplaysAs;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.dieschnittstelle.ess.basics.reflection.ReflectedStockItemBuilder.getAccessorNameForField;
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
	 * TODO BAS2
	 */
	private static void showAttributes(Object instance) {
		show("class is: " + instance.getClass());

		Class cls = instance.getClass();

		try {
			String clsName = cls.getName();
			String[] splitName = clsName.split("\\.");
			String basicClassName = splitName[splitName.length - 1];
			StringBuilder fieldVals = new StringBuilder();
			for(Field f : cls.getDeclaredFields()){
				String getterName = getAccessorNameForField("get", f.getName());
				Method getter = cls.getDeclaredMethod(getterName);
				String val = getter.invoke(instance).toString();
				String attrName;
				if(f.isAnnotationPresent(DisplaysAs.class)){
					DisplaysAs annotation = f.getAnnotation(DisplaysAs.class);
					attrName = annotation.value();
				}else{
					attrName = f.getName();
				}
				fieldVals.append(String.format(" %s:%s", attrName, val));
			}
			String docString = String.format("{%s%s}", basicClassName, fieldVals.toString());
			System.out.println(docString);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("showAnnotations(): exception occurred: " + e,e);
		}

	}

}
