package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class MainApp {

	/*
	 * Crea la clase MainApp que simplemente creará un objeto de la clase Modelo,
	 * otro de la clase Vista y otro de la clase Controlador pasándole los dos
	 * anteriores y luego llamará al método comenzar de la clase Controlador.
	 * Realiza el commit correspondiente.
	 */

	public static void main(String[] args) {
		System.out.println("Programa para la gestión de reservas de espacios del IES Al-Ándalus");

		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		try {
			Controlador controlador = new Controlador(modelo, vista);
			controlador.comenzar();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	Controlador controlador;

	public MainApp() {

	}

}
