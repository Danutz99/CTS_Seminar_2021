package dan.seminar3;

public enum TipContainer {
	Mic_10mc("Mic_10mc"), Mediu_25mc("Mediu_25mc"), Mare_50mc("Mare_50mc"), Jumbo_100mc("Jumbo_100mc");

	String valoare;

	TipContainer(String valoare) {
		this.valoare = valoare;
	}
}
