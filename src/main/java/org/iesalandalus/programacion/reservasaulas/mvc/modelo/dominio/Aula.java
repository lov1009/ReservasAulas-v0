package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Aula {

	/*
	 * Crea la clase Aula con los atributos indicados, los métodos de acceso y
	 * modificación con su visibilidad adecuada y el constructor con un parámetro y
	 * copia tal y como se indica en el diagrama de clases. Crea también los métodos
	 * equals, hashCode y toString, teniendo en cuenta que dos aulas serán iguales
	 * si sus nombres son los mismos. Haz un commit.
	 */
	
	//atributos
	private String nombre;
	
	
	//constructor con parámetros
	public Aula(String nombre) {
		setNombre(nombre);
		
	}
	
	//constructor copia
	public Aula(Aula aula) {
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede copiar un aula nula.");
		}
		
		setNombre(aula.nombre);
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	private void setNombre(String nombre) {
		if(nombre == null) {
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
			
		}
		if (nombre.isEmpty() || nombre.isBlank()) {
			throw new IllegalArgumentException("ERROR: El nombre del aula no puede estar vacío.");
		}
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "nombre Aula=" + nombre;
	}
	
	
	
}
