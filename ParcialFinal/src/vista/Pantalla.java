package vista;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import control.Controlador;
import modelo.Infectado;
import modelo.Persona;
import modelo.Sano;
import modelo.Simulacion;
import processing.core.PApplet;

public class Pantalla extends PApplet{
	
	private Controlador controlador;
	private long timeI;
	public void setup() {
		timeI=System.currentTimeMillis();
		Simulacion nueva = new Simulacion();
		controlador=new Controlador(nueva);
	}
	
	public static void main(String[] args) {
		PApplet.main(Pantalla.class);
	}
	
	public void settings() {
		  size(1000, 600);
		}
	
	public void draw() {
		background(200);
		CopyOnWriteArrayList<Persona> personas=controlador.getSimulacion().getPersonas();
		for(Persona p: personas) {
			if(p instanceof Sano) {
				fill(0,255,0);
			}
			else if(p instanceof Infectado) {
				fill(255,0,0);
			}
			else{
				fill(0,0,255);
			}
			ellipse(p.getPosX(), p.getPosY(), p.getRad(), p.getRad());
		}
		long currTime=System.currentTimeMillis()-timeI;
		//System.out.println(currTime);
		if(currTime>=1000) {
			controlador.getSimulacion().timePass();
			timeI=System.currentTimeMillis();
		}
		
		text(controlador.getSimulacion().getDias()+"",10,10);
		int[] cantidades=controlador.getSimulacion().cantidades();
		
		for (int i=0;i<cantidades.length;i++) {
			if(i==0) {
				fill(255,0,0);
				text("Infectados: "+cantidades[i], 900, (i+10)*20);
			}
			else if (i==1) {
				fill(0,0,255);
				text("Recuperados: "+cantidades[i], 900, (i+10)*20);
			}
			else {
				fill(0,255,0);
				text("Sanos: "+cantidades[i], 900, (i+10)*20);
			}
		}
	}

}
