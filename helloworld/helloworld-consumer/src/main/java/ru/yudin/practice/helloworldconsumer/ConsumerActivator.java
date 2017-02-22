/*
 * ConsumerActivator
 * 
 * 1.0
 * 
 * потребляет сервис
 * 
 */

package ru.yudin.practice.helloworldconsumer;

import ru.yudin.practice.helloworldservice.able.Service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ConsumerActivator implements BundleActivator {
	Consumer consumer;
	
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		ServiceReference reference =  bundleContext.getServiceReference(Service.class.getName());
		consumer = new Consumer((Service) bundleContext.getService(reference));
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
				
	}
	
}
