package ro.ase.acs.writers;

public class ConsoleWriter implements Writeable{
	@Override
	public void write(String message) {
		System.out.println(message);
	}
}
