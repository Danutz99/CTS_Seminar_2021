package ro.ase.acs.services;

import ro.ase.acs.main.IoC;
import ro.ase.acs.readers.Readable;
import ro.ase.acs.writers.Writeable;

public class Orchestrator {

	private Readable reader;
	private Writeable writer;
	private IoC container;

	public Orchestrator(Readable reader, Writeable writer) {
		this.reader = reader;
		this.writer = writer;
	}

	public Orchestrator() {
	}

	public void setContainer(IoC container) {
		this.container = container;
		this.reader = this.container.resolve(Readable.class);
		this.writer = this.container.resolve(Writeable.class);
	}

	public void execute() {
		writer.write(reader.read());
	}

}
