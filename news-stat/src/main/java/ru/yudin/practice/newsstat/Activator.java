/*
 * Activator
 * 
 * 1.0
 * 
 * Регистрирует сервис, создаёт команду practice:parse
 */

package ru.yudin.practice.newsstat;

import ru.yudin.practice.newsstat.able.IParser;
import ru.yudin.practice.newsstat.impl.Parser;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	private ServiceRegistration registration;
	
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		ServiceReference reference =  bundleContext.getServiceReference(IParser.class.getName());
		Hashtable commands = new Hashtable();
		commands.put("osgi.command.scope", "practice");
		commands.put("osgi.command.function", new String[] { "parse" });
		registration = bundleContext.registerService(IParser.class.getName(),
				new Parser(bundleContext),
				commands);
	}
	
	@Override
	public void stop(BundleContext arg0) throws Exception {
		registration.unregister();		
	}
	
}
