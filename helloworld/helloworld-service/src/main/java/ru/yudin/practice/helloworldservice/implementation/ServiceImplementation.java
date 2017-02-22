/*
 * ServiceImplementation
 * 
 * 1.0
 * 
 * вывод "Hello OSGi World!" в консоль
 */

package ru.yudin.practice.helloworldservice.implementation;

import ru.yudin.practice.helloworldservice.able.Service;

public class ServiceImplementation implements Service {
	
	@Override
	public void helloWorld(){
		System.out.println("Hello OSGi World!");
	}
}
