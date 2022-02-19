package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;

public class Vista {

	/*
	 * Crea la clase Vista en el paquete adecuado, con los atributos y métodos
	 * especificados en el diagrama. Esta clase hará uso de los métodos de la clase
	 * Consola para pedir los datos y llamará al método adecuado de la clase
	 * Controlador. El método comenzar será un bucle que mostrará el menú, pedirá la
	 * opción deseada y la ejecutará, así hasta que la opción elegida sea SALIR. El
	 * método salir simplemente llamará al método terminar de la clase Controlador.
	 * Realiza el commit correspondiente.
	 */

	Controlador controlador;

	private static final String ERROR = "";
	private static final String NOMBRE_VALIDO = "salón de actos"; //
	private static final String CORREO_VALIDO = "pepi@gmail.com";

	public Vista() {

		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {

		if (controlador == null) {
			throw new NullPointerException("ERROR: El controlador no puede ser nulo.");

		}
		this.controlador = controlador;
	}

	/*
	 * El método comenzar será un bucle que mostrará el menú, pedirá la opción
	 * deseada y la ejecutará, así hasta que la opción elegida sea SALIR
	 */

	public void comenzar() {
		// POR DEFECTO GENERO E INSERTO EL AULA SALÓN DE ACTOS EN LA LISTA DE AULAS,
		// PORQUE ES LA ÚNICA SOBRE LA QUE SE PUEDEN HACER RESERVAS
		// (por si el usuario no la inserta).

		try {
			controlador.insertarAula(new Aula(NOMBRE_VALIDO));
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		}
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {

		controlador.terminar();

	}

	public void insertarAula() {

		Aula aula = Consola.leerAula();

		try {
			controlador.insertarAula(aula);

		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void borrarAula() {

		Aula aula = Consola.leerAula();

		try {
			controlador.borrarAula(aula);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		}

	}

	public void buscarAula() {
		Aula aula = Consola.leerAula();

		Aula aulaEncontrada = controlador.buscarAula(aula);
		if (aulaEncontrada != null) {
			System.out.println(aulaEncontrada);
		} else
			System.out.println("ERROR: No existe el aula: " + aula.getNombre());

	}

	public void listarAulas() {
		String[] aulas = controlador.representarAulas();
		for (String string : aulas) {
			if (string != null) {
				System.out.println(string);
			}
		}
	}

	public void insertarProfesor() {
		Profesor profesor = Consola.leerProfesor();

		try {
			controlador.insertarProfesor(profesor);

		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Profesor profesor = Consola.leerProfesor();

		try {
			controlador.borrarProfesor(profesor);

		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarProfesor() {
		String profesorNombre = Consola.leerNombreProfesor();
		Profesor profesor = new Profesor(profesorNombre, CORREO_VALIDO);
		Profesor profesorEncontrado = controlador.buscarProfesor(profesor);
		if (profesorEncontrado != null) {
			System.out.println(profesorEncontrado);
		} else
			System.out.println("ERROR: No existe el profesor: " + profesor.getNombre());

	}

	public void listarProfesores() {

		String[] profesores = controlador.representarProfesores();
		for (String string : profesores) {
			if (string != null) {
				System.out.println(string);
			}

		}

	}

	public void realizarReserva() {
		String profesorNombre = Consola.leerNombreProfesor();
		Profesor profesor = new Profesor(profesorNombre, CORREO_VALIDO);
		Profesor profesorEncontrado = controlador.buscarProfesor(profesor);
		if (profesorEncontrado == null) {
			System.out.println("ERROR: No existe el profesor: " + profesor.getNombre());
			return;
		}

		Reserva reserva = leerReserva(profesorEncontrado);

		try {
			controlador.realizarReserva(reserva);

		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private Reserva leerReserva(Profesor profesor) {

		Aula aula = new Aula(NOMBRE_VALIDO);

		if (controlador.buscarAula(aula) == null) {
			System.out.println("ERROR: No existe el aula: " + aula);
			return null;
		}

		LocalDate dia = Consola.leerDia();
		Tramo tramo = Consola.leerTramo();

		Permanencia permanencia = new Permanencia(dia, tramo);

		return new Reserva(profesor, aula, permanencia);

	}

	public void anularReserva() {
		String profesorNombre = Consola.leerNombreProfesor();
		Profesor profesor = new Profesor(profesorNombre, CORREO_VALIDO);
		Profesor profesorEncontrado = controlador.buscarProfesor(profesor);

		if (profesorEncontrado == null) {
			System.out.println("ERROR: No existe el profesor: " + profesor);
			return;
		}
		Reserva reserva = leerReserva(profesorEncontrado);

		try {
			controlador.anularReserva(reserva);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarReservas() {

		String[] reservas = controlador.representarReservas();
		if (reservas[0] == null) {
			System.out.println("No hay reservas.");
			return;
		}

		for (String string : reservas) {
			if (string != null) {
				System.out.println(string);
			}

		}

	}

	public void listarReservasAula() {
		Aula aula = Consola.leerAula();
		Reserva[] reservas = controlador.getReservasAula(aula);
		if (reservas[0] == null) {
			System.out.println("No hay reservas para esta aula.");
			return;
		}
		for (Reserva reserva : reservas) {
			if (reserva != null) {
				System.out.println(reserva);
			}
		}
	}

	public void listarReservasProfesor() {
		String profesorNombre = Consola.leerNombreProfesor();
		Profesor profesor = new Profesor(profesorNombre, CORREO_VALIDO);
		Reserva[] reservas = controlador.getReservasProfesor(profesor);

		if (reservas[0] == null) {
			System.out.println("El profesor " + profesor.getNombre() + " no tiene reservas.");
			return;
		}
		for (Reserva reserva : reservas) {
			if (reserva != null) {
				System.out.println(reserva);
			}
		}
	}

	public void listarReservasPermanencia() {
		Tramo tramo = Consola.leerTramo();
		LocalDate dia = Consola.leerDia();
		Permanencia permanencia = new Permanencia(dia, tramo);

		Reserva[] reservas = controlador.getReservasPermanencia(permanencia);
		if (reservas.length <= 0) {
			System.out.println("No hay reservas para esta permanencia.");
			return;
		}
		for (Reserva reserva : reservas) {
			if (reserva != null) {
				System.out.println(reserva);
			}
		}
	}

	public void consultarDisponibilidad() {
		Aula aula = Consola.leerAula();
		Tramo tramo = Consola.leerTramo();
		LocalDate dia = Consola.leerDia();

		Permanencia permanencia = new Permanencia(dia, tramo);

		if (controlador.consultarDisponibilidad(aula, permanencia)) {
			System.out.println(aula + " " + permanencia + ": DISPONIBLE.");

		} else {
			System.out.println(aula + " " + permanencia + ": NO DISPONIBLE.");
		}

	}

}
