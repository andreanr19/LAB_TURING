package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TapeController {

	private ElementTape C0;
	private ElementTape C1;
	private ElementTape C2;

	private int tamanio;
	private int cantidadLineasLeidas;

	public int getCantidadLineasLeidas() {
		return cantidadLineasLeidas;
	}

	public void setCantidadLineasLeidas(int cantidadLineasLeidas) {
		this.cantidadLineasLeidas = cantidadLineasLeidas;
	}

	public TapeController() {

		cantidadLineasLeidas = 0;
	}

	public ElementTape getC0() {
		return C0;
	}

	public void setC0(ElementTape c0) {
		C0 = c0;
	}

	public ElementTape getC1() {
		return C1;
	}

	public void setC1(ElementTape c1) {
		C1 = c1;
	}

	public ElementTape getC2() {
		return C2;
	}

	public void setC2(ElementTape c2) {
		C2 = c2;
	}

	// operacion de leer
	/**
	 * This method allows to read an element from the tape
	 * 
	 * @param cabeza    it's the reference where the element is going to be put
	 * @param operacion it's the operation read from the tape
	 * @return a string with the element read
	 */

	public String readElement2(String cabeza, String operacion) {

		String toPrint = "";

		if (cabeza.equalsIgnoreCase("0") && operacion.equalsIgnoreCase("0")) {

			if (C0 == null) {
				toPrint += "#";

			} else {
				toPrint += "\n" + C0.getSimbolo();
			}
		} else if (cabeza.equalsIgnoreCase("1") && operacion.equalsIgnoreCase("0")) {
			if (C1 == null) {
				toPrint += "#";
			} else {
				toPrint += "\n" + C1.getSimbolo();
			}
		} else if (cabeza.equalsIgnoreCase("2") && operacion.equalsIgnoreCase("0")) {
			if (C2 == null) {
				toPrint += "#";
			} else {
				toPrint += "\n" + C2.getSimbolo();
			}
		}
		return toPrint;
	}

	public String readElement2withchar(char cabeza, char operacion) {

		String toPrint = "";

		if (cabeza == '0' && operacion == '0') {

			if (C0 == null) {
				toPrint += "#";

			} else {
				toPrint += "\n" + C0.getSimbolo();
			}
		} else if (cabeza == '1' && operacion == '0') {
			if (C1 == null) {
				toPrint += "#";
			} else {
				toPrint += "\n" + C1.getSimbolo();
			}
		} else if (cabeza == '2' && operacion == '0') {
			if (C2 == null) {
				toPrint += "#";
			} else {
				toPrint += "\n" + C2.getSimbolo();
			}
		}
		return toPrint;
	}

	public void toAddAnElement(String cabeza, String operacion, String letra) {

		// SI NO HAY NINGÚN ELEMENTO TODAVIA
		if (C0 == null && C1 == null && C2 == null) {

			if (cabeza.equalsIgnoreCase("0") && operacion.equalsIgnoreCase("1")
					&& Character.isLetter(letra.charAt(0))) {

				C0 = new ElementTape(letra);
				C1 = C0;
				C2 = C0;

			} else if (cabeza.equalsIgnoreCase("1") && operacion.equalsIgnoreCase("1")
					&& Character.isLetter(letra.charAt(0))) {

				C1 = new ElementTape(letra);
				C2 = C1;
				C0 = C1;
			} else if (cabeza.equalsIgnoreCase("2") && operacion.equalsIgnoreCase("1")
					&& Character.isLetter(letra.charAt(0))) {

				C2 = new ElementTape(letra);
				C0 = C2;
				C1 = C2;
			}

			// SI YA HAY ELEMENTOS EN LA LISTA
		} else {

			int ListSize = listSize() + 1;

			// SI SOLO QUEDAN DOS ELEMENTOS EN LA LISTA
			if (ListSize == 2) {
				if (cabeza.equalsIgnoreCase("0") && operacion.equalsIgnoreCase("1")
						&& Character.isLetter(letra.charAt(0))) {

					ElementTape theNew = new ElementTape(letra);

					C0.insertBefore(theNew);

					C0 = theNew;
					C1 = C0;
					C2 = C0.getNextElement();
				} else if (cabeza.equalsIgnoreCase("1") && operacion.equalsIgnoreCase("1")
						&& Character.isLetter(letra.charAt(0))) {

					// si se va a agregar por C1 el objeto debería quedar en la mitad y como hay
					// solo dos elementos, debe quedar de primero
					ElementTape theNew = new ElementTape(letra);

//					C0.insertAfter(theNew);
//					C1 = C0.getNextElement();
//					C2 = C1;

					C0.insertBefore(theNew);
					C0 = theNew;
					C1 = theNew;
					C2 = C0.getNextElement();

				} else if (cabeza.equalsIgnoreCase("2") && operacion.equalsIgnoreCase("1")
						&& Character.isLetter(letra.charAt(0))) {

					ElementTape theNew = new ElementTape(letra);

					C0.insertAfter(theNew);
					C1 = C0;
					C2 = C0.getNextElement();
				}
			}
			// SI EL TAMAÑO DE LA LISTA CON EL ELEMENTO QUE SE VA A AGREGAR QUEDA PAR

			else if (ListSize % 2 == 0) {
				if (cabeza.equalsIgnoreCase("0") && operacion.equalsIgnoreCase("1")
						&& Character.isLetter(letra.charAt(0))) {

					ElementTape theNew = new ElementTape(letra);
					C0.insertBefore(theNew);
					C0 = theNew;

					int middle = (ListSize / 2) - 1;
					C1 = getAnElement(middle);
					C2 = getTheLastElement();

				} else if (cabeza.equalsIgnoreCase("1") && operacion.equalsIgnoreCase("1")
						&& Character.isLetter(letra.charAt(0))) {

					int middle = (ListSize / 2) - 1;
					ElementTape theNew = new ElementTape(letra);

					C1.insertBefore(theNew);
					C1 = theNew;

				} else if (cabeza.equalsIgnoreCase("2") && operacion.equalsIgnoreCase("1")
						&& Character.isLetter(letra.charAt(0))) {

					ElementTape theNew = new ElementTape(letra);

					C2.insertAfter(theNew);

					C2 = theNew;

				}
				// SI EL TAMAÑO DE LA LISTA CON EL ELEMENTO QUE SE VA A AGREGAR QUEDA IMPAR
			} else {
				if (cabeza.equalsIgnoreCase("0") && operacion.equalsIgnoreCase("1")
						&& Character.isLetter(letra.charAt(0))) {

					ElementTape theNew = new ElementTape(letra);

					C0.insertBefore(theNew);

					C0 = theNew;

				} else if (cabeza.equalsIgnoreCase("1") && operacion.equalsIgnoreCase("1")
						&& Character.isLetter(letra.charAt(0))) {

					ElementTape theNew = new ElementTape(letra);

					C1.insertAfter(theNew);

					C1 = theNew;
				} else if (cabeza.equalsIgnoreCase("2") && operacion.equalsIgnoreCase("1")
						&& Character.isLetter(letra.charAt(0))) {

					ElementTape theNew = new ElementTape(letra);
					C2.insertAfter(theNew);
					C2 = theNew;

					int middle = (ListSize / 2);

					ElementTape elementC1 = getAnElement(middle);
					C1 = elementC1;

				}
			}
		}
	}

	public void toAddAnElementwithcharORIGINAL(char cabeza, char operacion, char letra) {

		// SI NO HAY NINGÚN ELEMENTO TODAVIA
		if (C0 == null && C1 == null && C2 == null) {

			if (cabeza == '0' && operacion == '1') {

				String theletra = "" + letra;
				C0 = new ElementTape(theletra);
				C1 = C0;
				C2 = C0;

			} else if (cabeza == '1' && operacion == '1') {

				String theletra = "" + letra;
				C1 = new ElementTape(theletra);
				C2 = C1;
				C0 = C1;
			} else if (cabeza == '2' && operacion == '1') {
				String theletra = "" + letra;
				C2 = new ElementTape(theletra);
				C0 = C2;
				C1 = C2;
			}

			// SI YA HAY ELEMENTOS EN LA LISTA
		} else {

			int ListSize = listSize() + 1;

			// SI SOLO QUEDAN DOS ELEMENTOS EN LA LISTA
			if (ListSize == 2) {
				if (cabeza == '0' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

					C0.insertBefore(theNew);

					C0 = theNew;
					C1 = C0;
					C2 = C0.getNextElement();
				} else if (cabeza == '1' && operacion == '1') {

					// si se va a agregar por C1 el objeto debería quedar en la mitad y como hay
					// solo dos elementos, debe quedar de primero
					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

//					C0.insertAfter(theNew);
//					C1 = C0.getNextElement();
//					C2 = C1;

					C0.insertBefore(theNew);
					C0 = theNew;
					C1 = theNew;
					C2 = C0.getNextElement();

				} else if (cabeza == '2' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

					C0.insertAfter(theNew);
					C1 = C0;
					C2 = C0.getNextElement();
				}
			}
			// SI EL TAMAÑO DE LA LISTA CON EL ELEMENTO QUE SE VA A AGREGAR QUEDA PAR

			else if (ListSize % 2 == 0) {
				if (cabeza == '0' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);
					C0.insertBefore(theNew);
					C0 = theNew;

					int middle = (ListSize / 2) - 1;
					C1 = getAnElement(middle);
					C2 = getTheLastElement();

				} else if (cabeza == '1' && operacion == '1') {
					String theletra = "" + letra;
					int middle = (ListSize / 2) - 1;
					ElementTape theNew = new ElementTape(theletra);

					C1.insertBefore(theNew);
					C1 = theNew;

				} else if (cabeza == '2' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

					C2.insertAfter(theNew);

					C2 = theNew;

				}
				// SI EL TAMAÑO DE LA LISTA CON EL ELEMENTO QUE SE VA A AGREGAR QUEDA IMPAR
			} else {
				if (cabeza == '0' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

					C0.insertBefore(theNew);

					C0 = theNew;

				} else if (cabeza == '1' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

					C1.insertAfter(theNew);

					C1 = theNew;
				} else if (cabeza == '2' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);
					C2.insertAfter(theNew);
					C2 = theNew;

					int middle = (ListSize / 2);

					ElementTape elementC1 = getAnElement(middle);
					C1 = elementC1;

				}
			}
		}
	}

	public void toAddAnElementwithchar(char cabeza, char operacion, char letra) {

		// SI NO HAY NINGÚN ELEMENTO TODAVIA
		if (C0 == null && C1 == null && C2 == null) {

			if (cabeza == '0' && operacion == '1') {

				String theletra = "" + letra;
				C0 = new ElementTape(theletra);
				C1 = C0;
				C2 = C0;
				tamanio++;

			} else if (cabeza == '1' && operacion == '1') {

				String theletra = "" + letra;
				C1 = new ElementTape(theletra);
				C2 = C1;
				C0 = C1;
				tamanio++;
			} else if (cabeza == '2' && operacion == '1') {
				String theletra = "" + letra;
				C2 = new ElementTape(theletra);
				C0 = C2;
				C1 = C2;
				tamanio++;
			}

			// SI YA HAY ELEMENTOS EN LA LISTA
		} else {

//			int ListSize = listSize() + 1;

			int ListSize = tamanio + 1;
			// SI SOLO QUEDAN DOS ELEMENTOS EN LA LISTA
			if (ListSize == 2) {
				if (cabeza == '0' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

					C0.insertBefore(theNew);

					C0 = theNew;
					C1 = C0;
					C2 = C0.getNextElement();
					tamanio++;
				} else if (cabeza == '1' && operacion == '1') {

					// si se va a agregar por C1 el objeto debería quedar en la mitad y como hay
					// solo dos elementos, debe quedar de primero
					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

//					C0.insertAfter(theNew);
//					C1 = C0.getNextElement();
//					C2 = C1;

					C0.insertBefore(theNew);
					C0 = theNew;
					C1 = theNew;
					C2 = C0.getNextElement();
					tamanio++;

				} else if (cabeza == '2' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

					C0.insertAfter(theNew);
					C1 = C0;
					C2 = C0.getNextElement();
					tamanio++;
				}
			}
			// SI EL TAMAÑO DE LA LISTA CON EL ELEMENTO QUE SE VA A AGREGAR QUEDA PAR

			else if (ListSize % 2 == 0) {
				if (cabeza == '0' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);
					C0.insertBefore(theNew);
					C0 = theNew;

					ElementTape theNewC1 = C1.getPreviousElement();
					C1 = theNewC1;
//					int middle = (ListSize / 2) - 1;
//					C1 = getAnElement(middle);
//					C2 = getTheLastElement();

					tamanio++;
				} else if (cabeza == '1' && operacion == '1') {
					String theletra = "" + letra;
//					int middle = (ListSize / 2) - 1;
					ElementTape theNew = new ElementTape(theletra);

					C1.insertBefore(theNew);
					C1 = theNew;
					tamanio++;

				} else if (cabeza == '2' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

					C2.insertAfter(theNew);

					C2 = theNew;

					// c1 queda igual

					tamanio++;
				}
				// SI EL TAMAÑO DE LA LISTA CON EL ELEMENTO QUE SE VA A AGREGAR QUEDA IMPAR
			} else {
				if (cabeza == '0' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

					C0.insertBefore(theNew);

					C0 = theNew;
					// a c1 no le pasa nada

					tamanio++;
				} else if (cabeza == '1' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);

					C1.insertAfter(theNew);

					C1 = theNew;
					tamanio++;
				} else if (cabeza == '2' && operacion == '1') {

					String theletra = "" + letra;
					ElementTape theNew = new ElementTape(theletra);
					C2.insertAfter(theNew);
					C2 = theNew;

					ElementTape theNewC1 = C1.getNextElement();
					C1 = theNewC1;
//					int middle = (ListSize / 2);
//
//					
//					ElementTape elementC1 = getAnElement(middle);
//					C1 = elementC1;
					tamanio++;

				}
			}
		}
	}

	/**
	 * THIS METHOD ALLOWS TO GET THE SIZE OF THE LINKED LIST
	 * 
	 * @return an int with the size of the linked list
	 */
	public int listSize() {

		int size = 0;
		ElementTape aux = C0;
		while (aux != null) {
			size++;
			aux = aux.getNextElement();
		}
		return size;
	}

	/**
	 * THIS METHOD ALLOWS TO GET AN ELEMENT OF THE LINKED LIST
	 * 
	 * @param index it's an int that indicates the index of the searched element
	 * @return an ElementTape which is the element that is being searched
	 */
	public ElementTape getAnElement(int index) {
		int indice = 0;

		ElementTape actual = C0;
		while (actual != null && indice != index) {
			actual = actual.getNextElement();
			indice++;
		}
		return actual;
	}

	/**
	 * THIS METHOD ALLOWS TO GET THE LAST ELEMENT OF THE LINKED LIST
	 * 
	 * @return an ElementTape which is the last element of the linked list
	 */
	public ElementTape getTheLastElement() {
		ElementTape actual = C0;
		if (actual != null) {
			while (actual.getNextElement() != null) {
				actual = actual.getNextElement();
			}
		}
		return actual;
	}

	public void toDeleteAnElement2(String cabeza, String operacion) {

		if (C0 == null && C1 == null && C2 == null) {

			// nothing happens
		} else {

			if (cabeza.equalsIgnoreCase("0") && operacion.equalsIgnoreCase("2")) {
				// SI el elemento a eliminar tiene relacion con las tres cabezas lectoras
				// es porque solo hay un elemento en la lista enlazada
				if (C0 == C1 && C0 == C2) {

					C0 = null;
					C1 = null;
					C2 = null;

					// SI EL ELEMENTO A ELIMINAR TIENE UNA RELACION CON C0 Y C1
				} else if (C0 == C1 && C2 != null) {
					C0 = C2;
					C1 = C2; // todas quedarían apuntando hacia c2

					// SI EL ELEMENTO A ELIMINAR TIENE UNA RELACION CON C1 Y C2
				} else if (C1 == C2) {
//					C1 = null;
//					C2 = C1;

					C1.setPreviousElement(null);
					C0 = C1;

					// SI NINGUNO ES IGUAL
					// COMPROBAR SI EL TAMAÑO DE LA LISTA RESULTANTE ES PAR
				} else {

					int ListSize = listSize() - 1;

					if (ListSize % 2 == 0) {

						int middle = (ListSize / 2) - 1;

						ElementTape theNewC0 = C0.getNextElement();
						C0 = theNewC0;
						C0.setPreviousElement(null);

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					} else { // si el tamaño resultante es impar
						int middle = (ListSize / 2);
						ElementTape theNewC0 = C0.getNextElement();
						C0 = theNewC0;
						C0.setPreviousElement(null);

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					}
				}
				// SI SE QUIERE ELIMINAR DE LA CABEZA LECTORA 1
			} else if (cabeza.equalsIgnoreCase("1") && operacion.equalsIgnoreCase("2")) {
				if (C0 == C1 && C0 == C2) {
					C0 = null;
					C1 = null;
					C2 = null;

					// SI EL ELEMENTO A ELIMINAR TIENE UNCA RELACION CON C0 Y C1
				} else if (C0 == C1 && C2 != null) {
					C0 = C2;
					C1 = C2;

					// SI EL ELEMENTO A ELIMINAR TIENE UNA RELACION CON C1 Y C2
				} else if (C1 == C2 && C0 != null) {
					C1 = C0;
					C2 = C0;

					// SI NINGUNO ES IGUAL
					// COMPROBAR SI EL TAMAÑO DE LA LISTA RESULTANTE ES PAR
				} else {
					int ListSize = listSize() - 1;
					if (ListSize % 2 == 0) {

						int middle = (ListSize / 2) - 1;

						ElementTape theBeforeOfTheDeleted = C1.getPreviousElement();
						ElementTape theNextofTheDeleted = C1.getNextElement();
						theBeforeOfTheDeleted.setNextElement(theNextofTheDeleted);
						theNextofTheDeleted.setPreviousElement(theBeforeOfTheDeleted);

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					} else { // si el tamaño resultante es impar

						int middle = (ListSize / 2);

						ElementTape theBeforeOfTheDeleted = C1.getPreviousElement();
						ElementTape theNextOfTheDeleted = C1.getNextElement();
						theBeforeOfTheDeleted.setNextElement(theNextOfTheDeleted);
						theNextOfTheDeleted.setPreviousElement(theBeforeOfTheDeleted);

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					}
				}
				// SI SE QUIERE ELIMINAR LA CABEZA LECTORA C2

			} else if (cabeza.equalsIgnoreCase("2") && operacion.equalsIgnoreCase("2")) {

				if (C0 == C1 & C0 == C2) {
					C0 = null;
					C1 = null;
					C2 = null;

					// si el elemento a eliminar tiene una relacion con C0 Y C1
				} else if (C0 == C1 && C2 != null) {
					C2 = C0;
					// todas quedarian apuntando a C0
				} else if (C1 == C2) {
					C1 = C0;
					C2 = C0;

					// Si ninguno es igual
				} else {

					// si queda par el tamaño
					int ListSize = listSize() - 1;
					if (ListSize % 2 == 0) {

						int middle = (ListSize / 2) - 1;

						ElementTape theBeforeOfTheDeleted = C2.getPreviousElement();

						theBeforeOfTheDeleted.setNextElement(null);

						C2 = theBeforeOfTheDeleted;

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					} else { // si queda impar el taamaño
						int middle = (ListSize / 2);

						ElementTape theBeforeOfTheDeleted = C2.getPreviousElement();
						theBeforeOfTheDeleted.setNextElement(null);

						C2 = theBeforeOfTheDeleted;

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					}
				}
			}
		}
	}

	public void toDeleteAnElement2withchar(char cabeza, char operacion) {

		if (C0 == null && C1 == null && C2 == null) {

			// nothing happens
		} else {

			if (cabeza == '0' && operacion == '2') {
				// SI el elemento a eliminar tiene relacion con las tres cabezas lectoras
				// es porque solo hay un elemento en la lista enlazada
				if (C0 == C1 && C0 == C2) {

					C0 = null;
					C1 = null;
					C2 = null;

					// SI EL ELEMENTO A ELIMINAR TIENE UNA RELACION CON C0 Y C1
				} else if (C0 == C1 && C2 != null) {
					C0 = C2;
					C1 = C2; // todas quedarían apuntando hacia c2

					// SI EL ELEMENTO A ELIMINAR TIENE UNA RELACION CON C1 Y C2
				} else if (C1 == C2) {
//					C1 = null;
//					C2 = C1;

					C1.setPreviousElement(null);
					C0 = C1;

					// SI NINGUNO ES IGUAL
					// COMPROBAR SI EL TAMAÑO DE LA LISTA RESULTANTE ES PAR
				} else {

					int ListSize = listSize() - 1;

					if (ListSize % 2 == 0) {

						int middle = (ListSize / 2) - 1;

						ElementTape theNewC0 = C0.getNextElement();
						C0 = theNewC0;
						C0.setPreviousElement(null);

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					} else { // si el tamaño resultante es impar
						int middle = (ListSize / 2);
						ElementTape theNewC0 = C0.getNextElement();
						C0 = theNewC0;
						C0.setPreviousElement(null);

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					}
				}
				// SI SE QUIERE ELIMINAR DE LA CABEZA LECTORA 1
			} else if (cabeza == '1' && operacion == '2') {
				if (C0 == C1 && C0 == C2) {
					C0 = null;
					C1 = null;
					C2 = null;

					// SI EL ELEMENTO A ELIMINAR TIENE UNCA RELACION CON C0 Y C1
				} else if (C0 == C1 && C2 != null) {
					C0 = C2;
					C1 = C2;

					// SI EL ELEMENTO A ELIMINAR TIENE UNA RELACION CON C1 Y C2
				} else if (C1 == C2 && C0 != null) {
					C1 = C0;
					C2 = C0;

					// SI NINGUNO ES IGUAL
					// COMPROBAR SI EL TAMAÑO DE LA LISTA RESULTANTE ES PAR
				} else {
					int ListSize = listSize() - 1;
					if (ListSize % 2 == 0) {

						int middle = (ListSize / 2) - 1;

						ElementTape theBeforeOfTheDeleted = C1.getPreviousElement();
						ElementTape theNextofTheDeleted = C1.getNextElement();
						theBeforeOfTheDeleted.setNextElement(theNextofTheDeleted);
						theNextofTheDeleted.setPreviousElement(theBeforeOfTheDeleted);

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					} else { // si el tamaño resultante es impar

						int middle = (ListSize / 2);

						ElementTape theBeforeOfTheDeleted = C1.getPreviousElement();
						ElementTape theNextOfTheDeleted = C1.getNextElement();
						theBeforeOfTheDeleted.setNextElement(theNextOfTheDeleted);
						theNextOfTheDeleted.setPreviousElement(theBeforeOfTheDeleted);

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					}
				}
				// SI SE QUIERE ELIMINAR LA CABEZA LECTORA C2

			} else if (cabeza == '2' && operacion == '2') {

				if (C0 == C1 & C0 == C2) {
					C0 = null;
					C1 = null;
					C2 = null;

					// si el elemento a eliminar tiene una relacion con C0 Y C1
				} else if (C0 == C1 && C2 != null) {
					C2 = C0;
					// todas quedarian apuntando a C0
				} else if (C1 == C2) {
					C1 = C0;
					C2 = C0;

					// Si ninguno es igual
				} else {

					// si queda par el tamaño
					int ListSize = listSize() - 1;
					if (ListSize % 2 == 0) {

						int middle = (ListSize / 2) - 1;

						ElementTape theBeforeOfTheDeleted = C2.getPreviousElement();

						theBeforeOfTheDeleted.setNextElement(null);

						C2 = theBeforeOfTheDeleted;

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					} else { // si queda impar el taamaño
						int middle = (ListSize / 2);

						ElementTape theBeforeOfTheDeleted = C2.getPreviousElement();
						theBeforeOfTheDeleted.setNextElement(null);

						C2 = theBeforeOfTheDeleted;

						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

					}
				}
			}
		}
	}

	public void toDeleteAnElement3withchar(char cabeza, char operacion) {

		if (C0 == null && C1 == null && C2 == null) {

			// nothing happens
		} else {

			if (cabeza == '0' && operacion == '2') {
				// SI el elemento a eliminar tiene relacion con las tres cabezas lectoras
				// es porque solo hay un elemento en la lista enlazada
				if (C0 == C1 && C0 == C2) {

					C0 = null;
					C1 = null;
					C2 = null;
					tamanio--;

					// SI EL ELEMENTO A ELIMINAR TIENE UNA RELACION CON C0 Y C1
				} else if (C0 == C1 && C2 != null) {
					C0 = C2;
					C1 = C2; // todas quedarían apuntando hacia c2
					tamanio--;

					// SI EL ELEMENTO A ELIMINAR TIENE UNA RELACION CON C1 Y C2
				} else if (C1 == C2) {
//					C1 = null;
//					C2 = C1;

					C1.setPreviousElement(null);
					C0 = C1;

					tamanio--;
					// SI NINGUNO ES IGUAL
					// COMPROBAR SI EL TAMAÑO DE LA LISTA RESULTANTE ES PAR
				} else {

					int ListSize = tamanio - 1;
//					int ListSize = listSize() - 1;

					if (ListSize % 2 == 0) {

//						int middle = (ListSize / 2) - 1;

						ElementTape theNewC0 = C0.getNextElement();
						C0 = theNewC0;
						C0.setPreviousElement(null);

						// c1 queda igual
//						ElementTape theNewC1 = getAnElement(middle);
//						C1 = theNewC1;
						tamanio--;

					} else { // si el tamaño resultante es impar
//						int middle = (ListSize / 2);
						ElementTape theNewC0 = C0.getNextElement();
						C0 = theNewC0;
						C0.setPreviousElement(null);

//						ElementTape theNewC1 = getAnElement(middle);
//						C1 = theNewC1;
//						
						// c1 queda apuntando al siguiente del que estaba apuntando antes
						C1 = C1.getNextElement();

						tamanio--;
					}
				}
				// SI SE QUIERE ELIMINAR DE LA CABEZA LECTORA 1
			} else if (cabeza == '1' && operacion == '2') {
				if (C0 == C1 && C0 == C2) {
					C0 = null;
					C1 = null;
					C2 = null;

					tamanio--;

					// SI EL ELEMENTO A ELIMINAR TIENE UNCA RELACION CON C0 Y C1
				} else if (C0 == C1 && C2 != null) {
					C0 = C2;
					C1 = C2;

					tamanio--;
					// SI EL ELEMENTO A ELIMINAR TIENE UNA RELACION CON C1 Y C2
				} else if (C1 == C2 && C0 != null) {
					C1 = C0;
					C2 = C0;

					tamanio--;
					// SI NINGUNO ES IGUAL
					// COMPROBAR SI EL TAMAÑO DE LA LISTA RESULTANTE ES PAR
				} else {
					int ListSize = tamanio - 1;
//					int ListSize = listSize() - 1;
					if (ListSize % 2 == 0) {

//						int middle = (ListSize / 2) - 1;

						ElementTape theNewC1 = C1.getPreviousElement();
						ElementTape theBeforeOfTheDeleted = C1.getPreviousElement();
						ElementTape theNextofTheDeleted = C1.getNextElement();
						theBeforeOfTheDeleted.setNextElement(theNextofTheDeleted);
						theNextofTheDeleted.setPreviousElement(theBeforeOfTheDeleted);

//						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

						tamanio--;
					} else { // si el tamaño resultante es impar

//						int middle = (ListSize / 2);

						ElementTape theNewC1 = C1.getNextElement();
						ElementTape theBeforeOfTheDeleted = C1.getPreviousElement();
						ElementTape theNextOfTheDeleted = C1.getNextElement();
						theBeforeOfTheDeleted.setNextElement(theNextOfTheDeleted);
						theNextOfTheDeleted.setPreviousElement(theBeforeOfTheDeleted);

//						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;

						tamanio--;
					}
				}
				// SI SE QUIERE ELIMINAR LA CABEZA LECTORA C2

			} else if (cabeza == '2' && operacion == '2') {

				if (C0 == C1 & C0 == C2) {
					C0 = null;
					C1 = null;
					C2 = null;

					tamanio--;
					// si el elemento a eliminar tiene una relacion con C0 Y C1
				} else if (C0 == C1 && C2 != null) {
					C2 = C0;
					tamanio--;
					// todas quedarian apuntando a C0
				} else if (C1 == C2) {
					C1 = C0;
					C2 = C0;
					tamanio--;

					// Si ninguno es igual
				} else {

					// si queda par el tamaño
					int ListSize = tamanio - 1;
//					int ListSize = listSize() - 1;
					if (ListSize % 2 == 0) {

//						int middle = (ListSize / 2) - 1;

						ElementTape theNewC1 = C1.getPreviousElement();
						ElementTape theBeforeOfTheDeleted = C2.getPreviousElement();

						theBeforeOfTheDeleted.setNextElement(null);

						C2 = theBeforeOfTheDeleted;

//						ElementTape theNewC1 = getAnElement(middle);
						C1 = theNewC1;
						tamanio--;

					} else { // si queda impar el taamaño
//						int middle = (ListSize / 2);

						ElementTape theBeforeOfTheDeleted = C2.getPreviousElement();
						theBeforeOfTheDeleted.setNextElement(null);

						C2 = theBeforeOfTheDeleted;
						tamanio--;

						// c1 queda igual
//						ElementTape theNewC1 = getAnElement(middle);
//						C1 = theNewC1;

					}
				}
			}
		}
	}

	public void toReadTape3(String file) throws IOException {

		String[] tape;
		File fl = new File(file);
		FileReader fr = new FileReader(fl);
		BufferedReader br = new BufferedReader(fr);

		String casodeprueba = br.readLine();

		BufferedWriter escribirArchivo = new BufferedWriter(new FileWriter("data/solucionEjemploEnunciado.txt"));
		int cabeza = 0;
		int operacion = cabeza + 1;
		int agregar = operacion + 1;

		while (casodeprueba != null) {
			tape = casodeprueba.split("");

			while (agregar <= tape.length) {

				// ES LA CABEZA C0
				if (tape[cabeza].equalsIgnoreCase("0")) {

					// operacion de añadir 1
					if (tape[operacion].equalsIgnoreCase("1")) {

						toAddAnElement(tape[cabeza], tape[operacion], tape[agregar]);

						cabeza = cabeza + 3;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de leer 0
					} else if (tape[operacion].equalsIgnoreCase("0")) {

						System.out.print(readElement2(tape[cabeza], tape[operacion]));
						escribirArchivo.write(readElement2(tape[cabeza], tape[operacion]));
						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de eliminar 2
					} else if (tape[operacion].equalsIgnoreCase("2")) {
						toDeleteAnElement2(tape[cabeza], tape[operacion]);

						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

					}

					// ES LA CABEZA 1
				} else if (tape[cabeza].equalsIgnoreCase("1")) {
					// operacion de añadir 1
					if (tape[operacion].equalsIgnoreCase("1")) {

						toAddAnElement(tape[cabeza], tape[operacion], tape[agregar]);

						cabeza = cabeza + 3;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de leer 0
					} else if (tape[operacion].equalsIgnoreCase("0")) {

						System.out.print(readElement2(tape[cabeza], tape[operacion]));
						escribirArchivo.write(readElement2(tape[cabeza], tape[operacion]));
						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de eliminar 2
					} else if (tape[operacion].equalsIgnoreCase("2")) {
						toDeleteAnElement2(tape[cabeza], tape[operacion]);

						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

					}

					// ES LA CABEZA 2
				} else if (tape[cabeza].equalsIgnoreCase("2")) {
					// operacion de añadir 1
					if (tape[operacion].equalsIgnoreCase("1")) {

						toAddAnElement(tape[cabeza], tape[operacion], tape[agregar]);

						cabeza = cabeza + 3;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de leer 0
					} else if (tape[operacion].equalsIgnoreCase("0")) {

						System.out.print(readElement2(tape[cabeza], tape[operacion]));
						escribirArchivo.write(readElement2(tape[cabeza], tape[operacion]));
						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de eliminar 2
					} else if (tape[operacion].equalsIgnoreCase("2")) {
						toDeleteAnElement2(tape[cabeza], tape[operacion]);

						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

					}
				}
			}
			cabeza = 0;
			operacion = cabeza + 1;
			agregar = operacion + 1;
			C0 = null;
			C1 = null;
			C2 = null;
			casodeprueba = br.readLine();

		}

		escribirArchivo.close();
		br.close();
	}

	public void toReadTape4(String file) throws IOException {

//		String[] tape;
		File fl = new File(file);
		FileReader fr = new FileReader(fl);
		BufferedReader br = new BufferedReader(fr);

		String casodeprueba = br.readLine();

		BufferedWriter escribirArchivo = new BufferedWriter(new FileWriter("data/solucion.txt"));
		int cabeza = 0;
		int operacion = cabeza + 1;
		int agregar = operacion + 1;

		while (casodeprueba != null) {
//			tape = casodeprueba.split("");

			while (agregar <= casodeprueba.length()) {

				// ES LA CABEZA C0
				if (casodeprueba.charAt(cabeza) == '0') {

					// operacion de añadir 1
					if (casodeprueba.charAt(operacion) == '1') {

						toAddAnElementwithchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion),
								casodeprueba.charAt(agregar));

						cabeza = cabeza + 3;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de leer 0
					} else if (casodeprueba.charAt(operacion) == '0') {

//						System.out.print(
//								readElement2withchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion)));
						escribirArchivo.write(
								readElement2withchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion)));
						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de eliminar 2
					} else if (casodeprueba.charAt(operacion) == '2') {
						toDeleteAnElement3withchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion));

						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

					}

					// ES LA CABEZA 1
				} else if (casodeprueba.charAt(cabeza) == '1') {
					// operacion de añadir 1
					if (casodeprueba.charAt(operacion) == '1') {

						toAddAnElementwithchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion),
								casodeprueba.charAt(agregar));

						cabeza = cabeza + 3;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de leer 0
					} else if (casodeprueba.charAt(operacion) == '0') {

//						System.out.print(
//								readElement2withchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion)));
						escribirArchivo.write(
								readElement2withchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion)));
						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de eliminar 2
					} else if (casodeprueba.charAt(operacion) == '2') {
						toDeleteAnElement3withchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion));

						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

					}

					// ES LA CABEZA 2
				} else if (casodeprueba.charAt(cabeza) == '2') {
					// operacion de añadir 1
					if (casodeprueba.charAt(operacion) == '1') {

						toAddAnElementwithchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion),
								casodeprueba.charAt(agregar));

						cabeza = cabeza + 3;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de leer 0
					} else if (casodeprueba.charAt(operacion) == '0') {

//						System.out.print(
//								readElement2withchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion)));
						escribirArchivo.write(
								readElement2withchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion)));
						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

						// operacion de eliminar 2
					} else if (casodeprueba.charAt(operacion) == '2') {
						toDeleteAnElement3withchar(casodeprueba.charAt(cabeza), casodeprueba.charAt(operacion));

						cabeza = cabeza + 2;
						operacion = cabeza + 1;
						agregar = operacion + 1;

					}
				}
			}
			cabeza = 0;
			operacion = cabeza + 1;
			agregar = operacion + 1;
			C0 = null;
			C1 = null;
			C2 = null;
			casodeprueba = br.readLine();

		}

		escribirArchivo.close();
		br.close();
	}

	public void convertirTapeEjemploEnunciado() throws IOException {

		BufferedWriter writeTime = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new FileReader("data/ejemploEnunciado.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/solucionEjemploEnunciado.txt"));

		char[] datos;

		String msg = br.readLine();

		while (msg != null) {
			datos = msg.toCharArray();

			int i = 0;
			int j = 1;

			while (i < datos.length) {
				if (datos[j] == '0') {
					bw.write(readElement2withchar(datos[i], datos[j]));
					i = j + 1;
				} else if (datos[j] == '1') {

					toAddAnElementwithchar(datos[i], datos[j], datos[j + 1]);
					i = j + 2;
				} else if (datos[j] == '2') {
					toDeleteAnElement3withchar(datos[i], datos[j]);
					i = j + 1;
				}
				j = i + 1;
			}

			C0 = null;
			C1 = null;
			C2 = null;

			msg = br.readLine();
		}
		br.close();
		bw.close();
		writeTime.close();

	}

	public void convertirTapeArchivoGrande() throws IOException {

		long tiempoFinal;

		BufferedWriter writeTime = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new FileReader("data/in_turing.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/solucionArchivoGrande.txt"));

		char[] datos;

		String msg = br.readLine();
		long tiempoInicial = System.currentTimeMillis();
		boolean stop = false;

		datos = msg.toCharArray();

		int i = 0;
		int j = 1;

		while (i < datos.length) {
			if (datos[j] == '0') {
				bw.write(readElement2withchar(datos[i], datos[j]));
				i = j + 1;
			} else if (datos[j] == '1') {

				toAddAnElementwithchar(datos[i], datos[j], datos[j + 1]);
				i = j + 2;
			} else if (datos[j] == '2') {
				toDeleteAnElement3withchar(datos[i], datos[j]);
				i = j + 1;
			}
			j = i + 1;
		}
		C0 = null;
		C1 = null;
		C2 = null;

		tiempoFinal = System.currentTimeMillis() - tiempoInicial;
		System.out.println("El tiempo que se tardó convirtiendo el archivo grande fue " + tiempoFinal + " milisegundos");
		br.close();
		bw.close();
		writeTime.close();

	}

	public void convertirTape2() throws IOException {

		long tiempoFinal;

		BufferedWriter writeTime = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new FileReader("data/in_turing.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/solucion.txt"));

		char[] datos;

		String msg = br.readLine();
//		long tiempoInicial = System.currentTimeMillis();
		boolean stop = false;

		while (msg != null) {
			datos = msg.toCharArray();

			int i = 0;
			int j = 1;

			while (i < datos.length) {
				if (datos[j] == '0') {
					bw.write(readElement2withchar(datos[i], datos[j]));
					i = j + 1;
				} else if (datos[j] == '1') {

					toAddAnElementwithchar(datos[i], datos[j], datos[j + 1]);
					i = j + 2;
				} else if (datos[j] == '2') {
					toDeleteAnElement3withchar(datos[i], datos[j]);
					i = j + 1;
				}
				j = i + 1;
			}

			C0 = null;
			C1 = null;
			C2 = null;

//		tiempoFinal = System.currentTimeMillis() - tiempoInicial;

			msg = br.readLine();
		}
		br.close();
		bw.close();
		writeTime.close();
	}

	public void removeAll() {
		C0=null;
		C1=null;
		C2=null;
	}
}
