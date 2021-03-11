package dan.seminar3;

public class MacaraCuSemafor extends Thread implements Descarcare {
	private Macara macara;
	private PortContainerCuSemafor portContainerCuSemafor;

	public MacaraCuSemafor(Macara macara, PortContainerCuSemafor portContainerCuSemafor) {
		this.macara = macara;
		this.portContainerCuSemafor = portContainerCuSemafor;
	}

	@Override
	public int descarcaContainer(PortContainer portContainer, Macara macara) {
		int containereRamase = 0;
		switch (macara.getTipContainer()) {
		case Mic_10mc: {
			containereRamase = portContainer.getNrContainere()[0];
			break;
		}
		case Mediu_25mc: {
			containereRamase = portContainer.getNrContainere()[1];
			break;
		}
		case Mare_50mc: {
			containereRamase = portContainer.getNrContainere()[2];
			break;
		}
		case Jumbo_100mc: {
			containereRamase = portContainer.getNrContainere()[3];
			break;
		}
		default:
			throw new RuntimeException("Nu stiu sa calculez nr de containere ramase de descarcat din PortContainerul "
					+ portContainer.getEticheta().toString());

		}
		return containereRamase;
	};

	private void operarePortContainer() {
		synchronized (this) {
//            System.out.println("Capacitatea inainte de operare descarcare pentru tipul de container " + macara.getTipContainer().toString()
//                    + ": " + portContainerCuSemafor.getCapacitate());

			if (this.portContainerCuSemafor.isOperare()) {
				System.out.println(
						"Capacitatea dan.mamaliga.tema_1.PortContainer-ului inainte de descarcare pentru tipul de container "
								+ macara.getTipContainer().toString() + ": " + portContainerCuSemafor.getCapacitate());
				System.out.println("Numarul de containere de tipul " + macara.getTipContainer().toString()
						+ " inainte de descarcare este "
						+ this.portContainerCuSemafor.getNrContainerePerTip(this.macara.getTipContainer()));
				int nrContainereRamase = descarcaContainer(this.portContainerCuSemafor, this.macara);
				if (nrContainereRamase == 0) {
					System.out.println(
							"Operatorul macara pentru tipul de containere: " + macara.getTipContainer().toString()
									+ " s-a oprit la numarul de containere " + nrContainereRamase);
					Thread.currentThread().interrupt();

				} else if (nrContainereRamase > 0) {
					portContainerCuSemafor.setNrContainerePerTip(nrContainereRamase - 1, this.macara.getTipContainer());
				}
				System.out.println("Numarul de containere de tipul " + macara.getTipContainer().toString()
						+ " dupa descarcare este "
						+ this.portContainerCuSemafor.getNrContainerePerTip(this.macara.getTipContainer()));
				System.out.println(
						"Capacitatea dan.mamaliga.tema_1.PortContainer-ului dupa operare descarcare pentru tipul de container: "
								+ macara.getTipContainer().toString() + ": " + portContainerCuSemafor.getCapacitate());
				System.out.println();
				this.portContainerCuSemafor.elibereazaOperare();
			} else {
				System.out.println("Operatorul macara pentru tipul de container " + macara.getTipContainer().toString()
						+ " este in asteptare!");
				System.out.println();
			}
		}
	}

	public void run() {
		try {
			while (true) {
				sleep(macara.getTimpManipulare());
				operarePortContainer();
			}
		} catch (InterruptedException e) {

		}
	}
}
