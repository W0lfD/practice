/*
 * ServiceActivator
 * 
 * 1.0
 * 
 * регистрирует сервис
 * 
 */

package ru.yudin.practice.helloworldservice;

import ru.yudin.practice.helloworldservice.able.Service;
import ru.yudin.practice.helloworldservice.implementation.ServiceImplementation;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {
	private ServiceRegistration serviceRegistration;
	
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		serviceRegistration = bundleContext.registerService(Service.class.getName(),
				new ServiceImplementation(),
				null);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		serviceRegistration.unregister();
	}
	
}
