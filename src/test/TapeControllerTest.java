package test;

import model.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TapeControllerTest {

	TapeController tp;

	void setUp1() {

		tp = new TapeController();

	}

	void setUp2() {
		tp = new TapeController();

		tp.toAddAnElementwithchar('2', '1', 'A');
		tp.toAddAnElementwithchar('2', '1', 'B');
		tp.toAddAnElementwithchar('2', '1', 'C');
	}

	void setUp3() {

		tp = new TapeController();

		tp.toAddAnElementwithchar('2', '1', 'A');
		tp.toAddAnElementwithchar('2', '1', 'B');
		tp.toAddAnElementwithchar('2', '1', 'C');
		tp.toAddAnElementwithchar('2', '1', 'D');
	}

	void setUp4() {
		tp = new TapeController();
		tp.toAddAnElementwithchar('0', '1', 'X');
	}

	@Test
	void readElement2withcharTest() {

		setUp1();
		String actual0 = tp.readElement2withchar('0', '0');
		String actual1 = tp.readElement2withchar('1', '0');
		String actual2 = tp.readElement2withchar('2', '0');

		assertEquals(actual0, "#");
		assertEquals(actual1, "#");
		assertEquals(actual2, "#");

		setUp2();
		String expected0 = "\n" + 'A';
		String expected1 = "\n" + 'B';
		String expected2 = "\n" + 'C';

		assertEquals(expected0, tp.readElement2withchar('0', '0'));
		assertEquals(expected1, tp.readElement2withchar('1', '0'));
		assertEquals(expected2, tp.readElement2withchar('2', '0'));

	}

	@Test
	void toAddAnElementwithcharTest() {

		setUp1();

		// Agregar en la cabeza 0 cuando el escenario está vacio
		tp.toAddAnElementwithchar('0', '1', 'A');

		String actual = tp.getC0().getSimbolo();
		String expected = "" + 'A';

		assertEquals(expected, actual);

		setUp1();
		// Agregar en la cabeza 1 cuando el escenario está vacio

		tp.toAddAnElementwithchar('1', '1', 'A');

		String actual1 = tp.getC1().getSimbolo();
		String expected1 = "" + 'A';

		assertEquals(expected1, actual1);

		setUp1();
		// Agregar en la cabeza 2 cuando el escenario está vacio
		tp.toAddAnElementwithchar('2', '1', 'A');

		String actual2 = tp.getC2().getSimbolo();
		String expected2 = "" + 'A';

		assertEquals(expected2, actual2);

		setUp2();
		// Agregar en la cabeza 0 cuando ya hay elementos en la lista

		tp.toAddAnElementwithchar('0', '1', 'Z');

		String actual00 = tp.getC0().getSimbolo();
		String expected00 = "" + 'Z';

		assertEquals(expected00, actual00);

		setUp2();
		// Agregar en la cabeza 1 cuando ya hay elementos en la lista

		tp.toAddAnElementwithchar('1', '1', 'X');

		String actual11 = tp.getC1().getSimbolo();
		String expected11 = "" + 'X';

		assertEquals(expected11, actual11);

		setUp2();
		// Agregar en la cabeza 2 cuando ya hay elementos en la lista y Agregar
		// elementos a la lista cuando ésta es impar y verificar que todas las
		// cabezas quedan posicionadas correctamente

		tp.toAddAnElementwithchar('2', '1', 'Y');

		String actual22 = tp.getC2().getSimbolo();
		String expected22 = "" + 'Y';

		assertEquals(expected22, actual22);

		setUp3();
		// Agregar elementos a la lista cuando ésta es par y verificar que todas las
		// cabezas quedan posicionadas correctamente

		tp.toAddAnElementwithchar('2', '1', 'W');

		String actual33 = tp.getC2().getSimbolo();
		String expected33 = "" + 'W';

		assertEquals(expected33, actual33);

	}

	@Test
	void toDeleteAnElement3withcharTest() {

		// Eliminar un único elemento de la lista y verificar que todas las cabezas
		// quedan apuntando a null
		setUp4();
		tp.toDeleteAnElement3withchar('0', '2');

		assertEquals(null, tp.getC0());
		assertEquals(null, tp.getC1());
		assertEquals(null, tp.getC2());

		setUp3();
		// Eliminar un elemento de una lista con # de elementos par y verificar que
		// todas las cabezas quedan posicionadas correctamente y eliminar un elemento de
		// la cabeza C0 y verificar que este queda apuntando al siguiente
		String actual4 = tp.getC0().getNextElement().getSimbolo();
		String expected4 = "" + 'B';
		tp.toDeleteAnElement3withchar('0', '2');
		assertEquals(expected4, actual4);

		setUp3();
		// eliminar un elemento de la cabeza C1 y verificar que este queda apuntando al
		// medio

		String expected44 = "C";
		tp.toDeleteAnElement3withchar('1', '2');
		String actual44 = tp.getC1().getSimbolo();
		assertEquals(expected44, actual44);

		setUp3();
		// eliminar un elemento de la cabeza C1 y verificar que este queda apuntando al
		// ultimo
		String expected55 = "C";
		tp.toDeleteAnElement3withchar('2', '2');
		String actual55 = tp.getC2().getSimbolo();

		assertEquals(expected55, actual55);
	}
	
	@Test
	void convertirTapeEjemploEnunciadoTest() {
		
	}

}
