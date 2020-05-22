package modelo;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Simulacion {
	
	private CopyOnWriteArrayList<Persona> personas;
	
	private HiloSimulacion hilo;
	
	private int dias;
	
	private boolean detenida;
	
	

	public Simulacion( ) {
		super();
		this.personas = new CopyOnWriteArrayList<Persona>();
		this.dias = 0;
		leerDatos();
		hilo=new HiloSimulacion(this);
		hilo.start();
	}
	
	public void movimiento() {
		for(Persona p:personas) {
			p.movimiento();
			CopyOnWriteArrayList<Persona> temp=(CopyOnWriteArrayList<Persona>) personas.clone();
			temp.remove(p);
			p.checkCollision(temp,this);
			if(p instanceof Infectado) {
				if(((Infectado)p).getDiasInfectado()>=14) {
					curar(p);
				}
			}
		}
	}
	
	public int[] cantidades() {
		int[] cantidades = new int[3];
		for(Persona p:personas) {
			if(p instanceof Infectado) {
				cantidades[0]=cantidades[0]+1;
			}
			else if (p instanceof Recuperado) {
				cantidades[1]=cantidades[1]+1;
			}
			else {
				cantidades[2]=cantidades[2]+1;
			}
		}
		return cantidades;
	}
	
	public double getInfectados() {
		int infectados=0;
		for(Persona p : personas) {
			if(p instanceof Infectado) {
				infectados++;
			}
		}
		return infectados;
	}
	
	public void checkInfection() throws InfeccionLimiteExeption{
		if(getInfectados()/personas.size()>0.3) {
			throw new InfeccionLimiteExeption("Mas del 30% de la poblacion esta contagiada");
		}
	}
	
	public void curar(Persona p) {
		Recuperado nuevo= new Recuperado();
		nuevo.setPosX(p.getPosX());
		nuevo.setPosY(p.getPosY());
		nuevo.setxDirection(p.getxDirection());
		nuevo.setyDirection(p.getyDirection());
		personas.remove(p);
		personas.add(nuevo);
	}
	
	public void infectar(Persona p) {
		Infectado nuevo= new Infectado();
		nuevo.setPosX(p.getPosX());
		nuevo.setPosY(p.getPosY());
		nuevo.setxDirection(p.getxDirection());
		nuevo.setyDirection(p.getyDirection());
		personas.remove(p);
		personas.add(nuevo);
	}
	
	public void timePass() {
		dias++;
		for(Persona p:personas) {
			if(p instanceof Infectado) {
				((Infectado) p).setDiasInfectado(((Infectado) p).getDiasInfectado()+1);
			}
		}
	}
	
	public void leerDatos() {
		File myObj = new File("./docs/data/info.txt");
	      Scanner myReader;
		try {
			myReader = new Scanner(myObj);
			 while (myReader.hasNextLine()) {
			        String[] data = myReader.nextLine().split(":");
			        String filter=data[0];
			        int number=Integer.parseInt(data[1]);
			        if(filter.equals("sanas")) {
			        	for(int i=0;i<number;i++) {
			        		Sano s = new Sano();
			        		personas.add(s);
			        	}
			        }
			        else if(filter.equals("infectadas")) {
			        	for(int i=0;i<number;i++) {
			        		Infectado in = new Infectado();
			        		personas.add(in);
			        	}
			        	
			        }
			        else if(filter.equals("recuperadas")) {
			        	for(int i=0;i<number;i++) {
			        		Recuperado r = new Recuperado();
			        		personas.add(r);
			        	}
			        }
			        
			      }
			      myReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.println(personas.size());
	    }

	public CopyOnWriteArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(CopyOnWriteArrayList<Persona> personas) {
		this.personas = personas;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public boolean isDetenida() {
		return detenida;
	}

	public void setDetenida(boolean detenida) {
		this.detenida = detenida;
	}
	
	

}
