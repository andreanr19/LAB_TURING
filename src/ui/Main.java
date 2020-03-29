package ui;

import model.TapeController;

public class Main {

	public static void main(String[] args) throws Exception {


		TapeController tc4 = new TapeController();

		tc4.removeAll();
		
		tc4.toReadTape3("data/ejemploEnunciado.txt");
		
		tc4.removeAll();
		System.out.println("\n");
		tc4.convertirTapeArchivoGrande();
	}

}
