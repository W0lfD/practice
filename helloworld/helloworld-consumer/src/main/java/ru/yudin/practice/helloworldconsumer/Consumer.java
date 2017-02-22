/*
 * Consumer
 * 
 * 1.0
 * 
 * вызывает метод из потреблённого сервиса
 * 
 */

package ru.yudin.practice.helloworldconsumer;

import ru.yudin.practice.helloworldservice.able.Service;

public class Consumer {
	private Service service;
	
	public Consumer(Service service){
		super();
		this.service = service;
		service.helloWorld();
	}
	
}
