/*
 * Activator
 * 
 * 1.0
 * 
 * Активатор бандла, регистрирует сервис и соответствующую команду
 * 
 */

package ru.yudin.practice.owncommand;

import ru.yudin.practice.owncommand.HelloSender;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	private ServiceRegistration registration;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Hashtable commands = new Hashtable();
		commands.put("osgi.command.scope", "practice");
		commands.put("osgi.command.function", new String[] { "hello" });
		registration = bundleContext.registerService(HelloSender.class.getName(), 
				new HelloSender(bundleContext), 
				commands);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
	}

}
