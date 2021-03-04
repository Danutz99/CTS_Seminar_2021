package ro.ase.acs.main;

import java.util.HashMap;
import java.util.Map;

public class IoC {

	Map<Class, Class> implementations = new HashMap<>();

	public void register(Class<?> contract, Class<?> implementation) {
		implementations.put(contract, implementation);
	}

	public <T> T resolve(Class<?> contract) {
		try {
			return (T) implementations.get(contract).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
