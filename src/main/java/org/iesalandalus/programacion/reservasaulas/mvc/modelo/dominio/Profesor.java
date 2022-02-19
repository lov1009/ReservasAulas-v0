package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profesor {

	/*
	 * Los profesores podrán realizar reservas. Un profesor se identifica por su
	 * nombre, su correo electrónico (que debe ser correcto) y su teléfono. El
	 * teléfono puede proporcionarlo el profesor o no. Si lo indica será una cadena
	 * de 9 dígitos y siempre debe comenzar por 6 o 9. Si no lo indica, no se
	 * asociará ningún teléfono a dicho profesor. Una vez creado un profesor no se
	 * le podrá cambiar el nombre, pero sí se podrá cambiar su correo o su teléfono,
	 * pudiendo ser este último vacío. Podremos añadir nuevos profesores (siempre
	 * que no exista otro profesor con el mismo nombre), borrarlos, buscar
	 * profesores por su nombre y listar los profesores dados de alta.
	 */

	private static final String ER_TELEFONO = "^[69][0-9]{8}$";
	private static final String ER_CORREO = "^[a-z]+([a-z0-9\\-\\_\\.]*[a-z0-9])*+@([a-z]*\\.[a-z]{2,})+$";
	private String nombre;
	private String correo;
	private String telefono;

	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}

	public Profesor(String nombre, String correo, String telefono) {

		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	}

	public Profesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		}

		if (profesor.telefono != null) {
			setTelefono(profesor.telefono);

		}
		setNombre(profesor.nombre);
		setCorreo(profesor.correo);

	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		}
		if (nombre.isEmpty() || nombre.isBlank()) {
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		}

		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");

		}

		// No compruebo que sea vacío o esté en blanco, porque eso ocurriría si no
		// cumple el patrón que es lo que compruebo abajo.

		Pattern p = Pattern.compile(ER_CORREO);
		Matcher m = p.matcher(correo);

		if (m.find()) {
			this.correo = correo;

		} else {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		}

	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {

		if (telefono == null) {
			return;
		}

		Pattern p = Pattern.compile(ER_TELEFONO);
		Matcher m = p.matcher(telefono);

		if (m.find()) {
			this.telefono = telefono;

		} else {
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		}

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
		Profesor other = (Profesor) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		StringBuilder profesor = new StringBuilder("nombre=" + nombre + ", correo=" + correo);
		if (telefono != null) {
			profesor.append(", telefono=" + telefono);
		}
		return profesor.toString();

	}

}
