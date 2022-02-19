package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {

	/*
	 * Crea la clase Aulas con los atributos indicados, los métodos de acceso y
	 * modificación con su visibilidad adecuada y el constructor por defecto y
	 * copia. Crea los métodos insertar, buscar, borrar y representar apoyándote en
	 * los métodos privados que se exponen en el diagrama de clases. Haz un commit.
	 */

	private int capacidad, tamano;
	private Aula[] aulas;

	public Aulas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}

		this.capacidad = capacidad;
		tamano = 0; // sería this.tamano = 0; ??
		aulas = new Aula[capacidad];

	}

	public Aula[] get() {

		return copiaProfundaAulas();
	}

	private Aula[] copiaProfundaAulas() {
		Aula[] copiaAulas = new Aula[capacidad];

		for (int i = 0; !tamanoSuperado(i); i++) {
			if (aulas[i] != null)

				copiaAulas[i] = new Aula(aulas[i]);
		}
		return copiaAulas;
	}

	public int getTamano() {

		return tamano;
	}

	public int getCapacidad() {

		return capacidad;
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		if (tamano == capacidad) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
			// SI COMENTO LA EXCEPCION DE ARRIBA NO FUNCIONA, ME DICE Q EL TIPO DE EXCEPCION
			// NO ES CORRECTO
		}
		if (tamano > 0) {
			int aulaBuscada = buscarIndice(aula);

			if (tamano > aulaBuscada) {
				throw new IllegalArgumentException("ERROR: No puede insertar un aula que ya existe.");
			}
		}
		aulas[tamano++] = new Aula(aula);

	}

	private int buscarIndice(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}

		for (int i = 0; i < tamano; i++) {
			if (aulas[i].equals(aula)) {
				return i;
			}
		}
		return tamano + 1;
	}

	// metodo tamanoSuperado
	private boolean tamanoSuperado(int tamano) {
		if (this.tamano <= tamano) {
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

	public Aula buscar(Aula aula) {
		int indice = buscarIndice(aula);
		if (indice < tamano) {
			return new Aula(aulas[indice]);
		}
		return null;
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		int resultado = buscarIndice(aula);
		if (resultado > tamano) {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");

		}
		desplazarUnaPosicionHaciaIzquierda(resultado);
		tamano--;
	}

	// Creo q no se contemplan estas excepciones en los test

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		if (capacidadSuperada(indice)) {
			throw new IllegalArgumentException("ERROR: Fuera de rango");
		}
		if (tamanoSuperado(indice)) {
			throw new IllegalArgumentException("ERROR: Fuera de rango");

		}
		aulas[indice] = null;

		for (int i = indice; i < capacidad - 1; i++) {
			Aula aux = aulas[i];
			aulas[i] = aulas[i + 1];
			aulas[i + 1] = aux;

		}
	}

	public String[] representar() {
		String[] representacion = new String[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {

			representacion[i] = aulas[i].toString();

		}
		return representacion;

	}
}
