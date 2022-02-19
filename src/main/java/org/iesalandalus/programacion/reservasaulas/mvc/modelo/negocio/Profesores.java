package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	/*
	 * Crea la clase Profesores con los atributos indicados, los métodos de acceso y
	 * modificación con su visibilidad adecuada y el constructor por defecto y
	 * copia. Crea los métodos insertar, buscar, borrar y representar apoyándote en
	 * los métodos privados que se exponen en el diagrama de clases. Haz un commit.
	 */

	private int capacidad, tamano;
	private Profesor[] profesores;

	public Profesores(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		tamano = 0;
		profesores = new Profesor[capacidad];

	}

	public Profesor[] get() {

		return copiaProfundaProfesores();
	}

	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaProfesores = new Profesor[capacidad];

		for (int i = 0; !tamanoSuperado(i); i++) {
			if (profesores[i] != null)

				copiaProfesores[i] = new Profesor(profesores[i]);
		}
		return copiaProfesores;
	}

	public int getTamano() {

		return tamano;
	}

	public int getCapacidad() {

		return capacidad;
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		if (tamano == capacidad) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		}

		if (tamano > 0) {
			int profeBuscado = buscarIndice(profesor);

			if (tamano > profeBuscado) {
				throw new OperationNotSupportedException("ERROR: No puede insertar un profesor que ya existe.");
			}
		}
		profesores[tamano++] = new Profesor(profesor);
	}

	private int buscarIndice(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		for (int i = 0; i < tamano; i++) {
			if (profesores[i].equals(profesor)) {
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

	public Profesor buscar(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		int indice = buscarIndice(profesor);
		if (indice < tamano) {
			return new Profesor(profesores[indice]);
		}
		return null;
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}

		int resultado = buscarIndice(profesor);
		if (resultado > tamano) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
		desplazarUnaPosicionHaciaIzquierda(resultado);
		tamano--;
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		if (capacidadSuperada(indice)) {
			throw new IllegalArgumentException("ERROR: Fuera de rango");
		}
		if (tamanoSuperado(indice)) {
			throw new IllegalArgumentException("ERROR: Fuera de rango");

		}
		profesores[indice] = null;

		for (int i = indice; i < capacidad - 1; i++) {
			Profesor aux = profesores[i];
			profesores[i] = profesores[i + 1];
			profesores[i + 1] = aux;

		}
	}

	public String[] representar() {
		String[] representacion = new String[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			representacion[i] = profesores[i].toString();
		}
		return representacion;
	}

}
