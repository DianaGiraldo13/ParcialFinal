package control;

import modelo.Simulacion;

public class Controlador {
	
	private Simulacion simulacion;
	
	public Controlador(Simulacion simulacion) {
		super();
		this.simulacion = simulacion;
	}

	public Simulacion getSimulacion() {
		return simulacion;
	}

	public void setSimulacion(Simulacion simulacion) {
		this.simulacion = simulacion;
	}
	
	

}
