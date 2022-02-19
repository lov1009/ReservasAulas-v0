package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {
	/*
	 * Crea la clase Reservas con los atributos indicados, los métodos de acceso y
	 * modificación con su visibilidad adecuada y el constructor por defecto y
	 * copia. Crea los métodos insertar, buscar, borrar, representar,
	 * getReservasAula, getReservasProfesor, getReservasPermanencia y
	 * consultarDisponibilidad apoyándote en los métodos privados que se exponen en
	 * el diagrama de clases. Haz un commit
	 */
	
	private int capacidad, tamano;
	private Reserva[] reservas;

	public Reservas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		tamano = 0; //sería this.tamano = 0; ??
		reservas = new Reserva[capacidad];

	}

	public Reserva[] get() {

		return copiaProfundaReservas(); 
	}

	private Reserva[] copiaProfundaReservas() {
		Reserva[] copiaReserva = new Reserva[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (reservas[i] != null)
				copiaReserva[i] = new Reserva(reservas[i]);
		}
		return copiaReserva;
	}

	public int getTamano() {

		return tamano;
	}

	public int getCapacidad() {

		return capacidad;
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		if (tamano == capacidad) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		}
		
		if (tamano > 0) {
			int reservaBuscada = buscarIndice(reserva);

			if (tamano > reservaBuscada) {
				throw new IllegalArgumentException("ERROR: El aula está ocupada.");
			}
		}
		reservas[tamano++] = new Reserva(reserva);
	}

	private int buscarIndice(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}

		for (int i = 0; i < tamano; i++) {
			if (reservas[i].equals(reserva)) {
				return i;
			}
		}
		return tamano + 1;
	} 

	// metodo tamanoSuperado
	private boolean tamanoSuperado(int tamano) {
		if (tamano >= this.tamano) {
			return true;
		}
		return false;
	}

	// metodo capacidadSuperada (si la capacidad es menor que la capacidad que le
	// pasamos, se supera la capacidad, sería true.
	private boolean capacidadSuperada(int capacidad) {
		if (this.capacidad < capacidad) {
			return true;
		}
		return false;
	}

	
	public Reserva buscar(Reserva reserva) {
		int indice = buscarIndice(reserva);
		if (indice < tamano) {
			return new Reserva(reservas[indice]);
		}
		return null;
	} 

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}

		int resultado = buscarIndice(reserva);
		if (resultado > tamano) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
			
		}
		desplazarUnaPosicionHaciaIzquierda(resultado);
		tamano--;
	}

	//Creo q no se contemplan estas excepciones en los test
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		if (capacidadSuperada(indice)) {
			throw new IllegalArgumentException("ERROR: Fuera de rango");
		}
		if (tamanoSuperado(indice)) {
			throw new IllegalArgumentException("ERROR: Fuera de rango");

		}
		reservas[indice] = null;

		for (int i = indice; i < capacidad - 1; i++) {
			Reserva aux = reservas[i];
			reservas[i] = reservas[i + 1];
			reservas[i + 1] = aux;

		}
	}

	public String[] representar() {

		String[] representacion = new String[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			representacion[i] = reservas[i].toString();
		}

		return representacion;

	}

	public Reserva[] getReservasProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}

		Reserva[] reservasProfesor = new Reserva[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (reservas[i].getProfesor().equals(profesor)) {
				reservasProfesor[j++] = reservas[i];
			}
		}
		return reservasProfesor;

	}

	public Reserva[] getReservasAula(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula.");
		}

		Reserva[] reservasAula = new Reserva[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (reservas[i].getAula().equals(aula)) {
				reservasAula[j++] = reservas[i];
			}
		}
		return reservasAula;
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("ERROR: La permanencia no puede ser nula.");
		}

		Reserva[] reservasPermanencia = new Reserva[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {

			if (reservas[i].getPermanencia().equals(permanencia)) {
				reservasPermanencia[j++] = reservas[i];
			}
			

		}
		return reservasPermanencia;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}

		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}

		for (int i = 0; !tamanoSuperado(i); i++) {

			if (reservas[i].getPermanencia().equals(permanencia) && reservas[i].getAula().equals(aula)) {

				return false;
			}
			

		}

		return true;
	}
}
