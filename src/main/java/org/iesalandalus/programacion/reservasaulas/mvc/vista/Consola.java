package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	/*
	 * Crea la clase Consola de ayuda, que será la encargada de realizar las
	 * diferentes lecturas de los objetos desde la consola. Recuerda evitar que se
	 * cree el constructor por defecto. Haz un commit.
	 */

	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static Aula aula;
	static Profesor profesor;

	private Consola() {

	}

	// DE LA EXPLICACION D CLASE
	public static void mostrarMenu() {

		mostrarCabecera("MENÚ");
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	// REVISAR OBVIAMENTE
	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);

	}

	public static int elegirOpcion() {
		int opcionOrdinal;
		do {
			System.out.println("Elige una opción: ");
			opcionOrdinal = Entrada.entero();

		} while (!Opcion.esOrdinalValido(opcionOrdinal));

		return opcionOrdinal;
	}

	public static Aula leerAula() {
		Aula aula = null;

		do {
			System.out.println("Introduzca el aula:");

			try {
				aula = new Aula(leerNombreAula());

			} catch (NullPointerException e) {
				System.out.println(e.getMessage());

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		} while (aula == null);

		return aula;

	}

	public static String leerNombreAula() {

		return Entrada.cadena();
	}

	public static Profesor leerProfesor() {
		Profesor profesor = null;

		do {
			String nombreProfesor = leerNombreProfesor();

			System.out.println("Introduzca el correo del profesor");
			String correoProfesor = Entrada.cadena();

			System.out.println("Introduzca el telefono profesor");
			String telefonoProfesor = Entrada.cadena();

			try {
				if (telefonoProfesor.isBlank()) {
					profesor = new Profesor(nombreProfesor, correoProfesor);
				} else
					profesor = new Profesor(nombreProfesor, correoProfesor, telefonoProfesor);

			} catch (NullPointerException e) {
				System.out.println(e.getMessage());

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		} while (profesor == null);

		return profesor;

	}

	public static String leerNombreProfesor() {

		System.out.println("Introduzca el nombre del profesor");
		return Entrada.cadena();

	}

	public static Tramo leerTramo() {
		Tramo tramoEnum = null;

		do {
			System.out.println("Introduzca el tramo para la reserva");
			for (Tramo tramo : Tramo.values()) {
				System.out.println(String.format("%d.- %s", tramo.ordinal(), tramo));
			}

			int tramo = Entrada.entero();

			try {// TODO
				tramoEnum = Tramo.values()[tramo];

			} catch (NullPointerException e) {
				System.out.println(e.getMessage());

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("ERROR: " + tramo + " no es una opción válida.");
			}

		} while (tramoEnum == null);

		return tramoEnum;

	}

	public static LocalDate leerDia() {
		LocalDate diaLocalDate = null;

		while (diaLocalDate == null) {
			System.out.println("Introduzca la fecha, con el formato: dd/MM/yyyy");
			String diaIntroducido = Entrada.cadena();

			try {
				diaLocalDate = LocalDate.parse(diaIntroducido, (FORMATO_DIA));

				if (diaLocalDate.isBefore(LocalDate.now())) {
					System.out.println("ERROR: El día no puede ser anterior al día de hoy.");

					diaLocalDate = null;
				}

			} catch (DateTimeParseException e) {
				System.out.println("ERROR: El formato de la fecha no es correcto.");
			}
		}
		return diaLocalDate;
	}

}
