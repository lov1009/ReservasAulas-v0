package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Permanencia {
	
	 
	/*
	 * Crea la clase Permanencia con sus atributos especificados, los métodos de
	 * acceso y modificación con su visibilidad adecuada y el constructor con dos
	 * parámetros y copia tal y como se indica en el diagrama de clases. El formato
	 * de un día debe ser "dd/MM/yyyy". Crea también los métodos equals, hashCode y
	 * toString, teniendo en cuenta que dos permanencias serán iguales si son para
	 * el mismo día y para el mismo tramo. Haz un commit.
	 */

	// atributos
	private LocalDate dia;
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public Tramo tramo;

	// constructor con parámetros
	public Permanencia(LocalDate dia, Tramo tramo) {
		setDia(dia);
		setTramo(tramo);
	}

	// constructor copia
	public Permanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede copiar una permanencia nula.");
		}
		setDia(permanencia.dia);
		setTramo(permanencia.tramo);
	}

	// getter y setter
	public LocalDate getDia() {

		return dia;
	}

	private void setDia(LocalDate dia) {
		if (dia == null) {
			throw new NullPointerException("ERROR: El día de una permanencia no puede ser nulo.");
		}
		
		
		this.dia = dia;

	}

	public Tramo getTramo() {
		return tramo;
	}

	private void setTramo(Tramo tramo) {
		if (tramo == null) {
			throw new NullPointerException("ERROR: El tramo de una permanencia no puede ser nulo.");
		}
		
		this.tramo = tramo;
	}

	@Override
	public int hashCode() {

		return Objects.hash(dia, tramo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permanencia other = (Permanencia) obj;

		return Objects.equals(dia, other.dia) && Objects.equals(tramo, other.tramo);
	}
	// CREO Q ESTA MAL LO DL FORMATO, REVISAR
	@Override
	public String toString() {
		return "dia=" + dia.format(FORMATO_DIA) + ", tramo=" + tramo;

	}

}
