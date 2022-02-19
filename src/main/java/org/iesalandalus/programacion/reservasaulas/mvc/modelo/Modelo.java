package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;

public class Modelo {
	// Crea la clase Modelo con los atributos indicados, los métodos
	// que se especifican en el diagrama y que simplemente llamarán a
	// cada uno de los métodos de la clase DAO implicada. Haz un commit.

	private static final int CAPACIDAD = 5;
	Profesores profesores;
	Aulas aulas;
	Reservas reservas;

	public Modelo() {
		aulas = new Aulas(CAPACIDAD);
		profesores = new Profesores(CAPACIDAD);
		reservas = new Reservas(CAPACIDAD);
	}

	// AULA
	public Aula[] getAulas() {
		return aulas.get();

	}

	public int getNumAulas() {
		return aulas.getCapacidad();
	}

	public String[] representarAulas() {
		return aulas.representar();
	}

	public Aula buscarAula(Aula aula) {
		return aulas.buscar(aula);
	}

	public void insertarAula(Aula aula) throws OperationNotSupportedException {

		aulas.insertar(aula);

	}

	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		aulas.borrar(aula);
	}

	// PROFESOR
	public Profesor[] getProfesores() {
		return profesores.get();
	}

	public int getNumProfesores() {
		return profesores.getCapacidad();
	}

	public String[] representarProfesores() {
		return profesores.representar();
	}

	public Profesor buscarProfesor(Profesor profesor) {
		return profesores.buscar(profesor);
	}

	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {

		profesores.insertar(profesor);
	}

	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {

		profesores.borrar(profesor);
	}

	// RESERVA

	public Reserva[] getReservas() {
		return reservas.get();
	}

	public int getNumReservas() {
		return reservas.getCapacidad(); // QUIZAS ES SOLO RETURN CAPACIDAD; ??
	}

	public String[] representarReservas() {
		return reservas.representar();
	}

	public Reserva buscarReserva(Reserva reserva) {
		return reservas.buscar(reserva);
	}

	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.insertar(reserva);
	}

	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.borrar(reserva);
	}

	public Reserva[] getReservasAula(Aula aula) {
		return reservas.getReservasAula(aula);
	}

	public Reserva[] getReservasProfesor(Profesor profesor) {
		return reservas.getReservasProfesor(profesor);
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		return reservas.getReservasPermanencia(permanencia);
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {

		return reservas.consultarDisponibilidad(aula, permanencia);
	}

}
