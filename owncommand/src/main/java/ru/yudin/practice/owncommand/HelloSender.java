/*
 * HelloSender
 * 
 * 1.0
 * 
 * Реализация вывода в консоль слова "Hello" и переданного параметра
 * 
 */


package ru.yudin.practice.owncommand;

import org.osgi.framework.BundleContext;

public class HelloSender {
	BundleContext bundleContext;
	
	public HelloSender(BundleContext bundleContext){
		this.bundleContext = bundleContext;
	}
	
	public void hello(String param){
		System.out.println("Hello, " + param);
	}

}
