package dan.seminar3;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class OperatorPort extends Thread {
	public static void main(String[] args) {
		int[] nrContainere1 = new int[4];
		int[] nrContainere2 = new int[4];
		int[] nrContainere3 = new int[4];
		Random random = new Random();
		for (int i = 0; i < nrContainere1.length; i++)
			nrContainere1[i] = random.nextInt(7);
		for (int i = 0; i < nrContainere2.length; i++)
			nrContainere2[i] = random.nextInt(7);
		for (int i = 0; i < nrContainere3.length; i++)
			nrContainere3[i] = random.nextInt(7);

		TipContainer[] tipContainer = new TipContainer[4];
		tipContainer[0] = TipContainer.Mic_10mc;
		tipContainer[1] = TipContainer.Mediu_25mc;
		tipContainer[2] = TipContainer.Mare_50mc;
		tipContainer[3] = TipContainer.Jumbo_100mc;

		PortContainer portContainer1 = new PortContainer("PortContainer1".toCharArray(), tipContainer, nrContainere1);
		PortContainer portContainer2 = new PortContainer("PortContainer2".toCharArray(), tipContainer, nrContainere2);
		PortContainer portContainer3 = new PortContainer("PortContainer3".toCharArray(), tipContainer, nrContainere3);
		ArrayList<PortContainer> flota = new ArrayList<>();
		flota.add(portContainer1);
		flota.add(portContainer2);
		flota.add(portContainer3);
		for (PortContainer portContainer : flota)
			System.out.println(portContainer.toString());

		FileWriter outFile = null;
		BufferedWriter writer = null;

		try {
			outFile = new FileWriter("portContainere.csv", false);
			writer = new BufferedWriter(outFile);

			for (PortContainer i : flota) {
				System.out.println(i.toString());
				writer.write(i.toString());
				writer.newLine();
			}

			writer.close();
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayDeque<PortContainer> coada = new ArrayDeque<PortContainer>();

		FileReader inFile = null;
		BufferedReader reader = null;

		try {
			inFile = new FileReader("portContainere.csv");
			reader = new BufferedReader(inFile);

			Scanner fileScanner = new Scanner(reader);
			while (fileScanner.hasNext()) {
				String linie = fileScanner.nextLine();
				Scanner lineScanner = new Scanner(linie);
				lineScanner.useDelimiter(",");

				String clasaCitita = lineScanner.next();
				lineScanner.close();
				Class<?> clasa = Class.forName(clasaCitita);
				Object local = clasa.getDeclaredConstructor().newInstance();
				if (local instanceof PortContainer) {
					local = ((PortContainer) local).dinString(linie, ",");
					coada.offerLast((PortContainer) local);
				}
			}
			fileScanner.close();

			reader.close();
			inFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		PortContainer iter = null;
		while (!coada.isEmpty()) {
			iter = coada.pollFirst();
			System.out.println(iter.toString());

		}

//        dan.mamaliga.tema_1.PortContainer test = new dan.mamaliga.tema_1.PortContainer();
//        try {
//            test = (dan.mamaliga.tema_1.PortContainer) portContainer1.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("------------Test Cloneable------------");
//        System.out.println(test.toString());
//        System.out.println(portContainer1.toString());

		Macara macaraContainerMic = new Macara(TipContainer.Mic_10mc, 40);
		Macara macaraContainerMediu = new Macara(TipContainer.Mediu_25mc, 50);
		Macara macaraContainerMare = new Macara(TipContainer.Mare_50mc, 60);
		Macara macaraContainerJumbo = new Macara(TipContainer.Jumbo_100mc, 70);

		PortContainerCuSemafor portContainerCuSemafor = new PortContainerCuSemafor("PortContainer1".toCharArray(),
				tipContainer, nrContainere1, 1);

		Thread macaraCuSemaforContainerMic = new MacaraCuSemafor(macaraContainerMic, portContainerCuSemafor);
		Thread macaraCuSemaforContainerMediu = new MacaraCuSemafor(macaraContainerMediu, portContainerCuSemafor);
		Thread macaraCuSemaforContainerMare = new MacaraCuSemafor(macaraContainerMare, portContainerCuSemafor);
		Thread macaraCuSemaforContainerJumbo = new MacaraCuSemafor(macaraContainerJumbo, portContainerCuSemafor);

		macaraCuSemaforContainerMic.setName("Fir macara Container Mic");
		macaraCuSemaforContainerMediu.setName("Fir macara Container Mediu");
		macaraCuSemaforContainerMare.setName("Fir macara Container Mare");
		macaraCuSemaforContainerJumbo.setName("Fir macara Container Jumbo");

		macaraCuSemaforContainerMic.setPriority(MAX_PRIORITY);
		macaraCuSemaforContainerMic.start();
		macaraCuSemaforContainerMediu.start();
		macaraCuSemaforContainerMare.start();
		macaraCuSemaforContainerJumbo.start();

		Thread[] threadArray = new Thread[Thread.activeCount()];
		Thread.enumerate(threadArray);
		for (int i = 0; i < threadArray.length; i++) {
			System.out.println(threadArray[i].getName() + ", " + threadArray[i].getPriority());
		}

		try {
			macaraCuSemaforContainerMic.join(1000);
			macaraCuSemaforContainerMediu.join(1000);
			macaraCuSemaforContainerMare.join(1000);
			macaraCuSemaforContainerJumbo.join(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (portContainerCuSemafor) {
			System.out.println("Capacitatea Finala dupa descarcare este: " + portContainerCuSemafor.getCapacitate());
		}

	}
}
