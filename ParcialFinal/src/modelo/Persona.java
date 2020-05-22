package modelo;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Persona {
	
	private float posX;
	
	private float posY;
	
	private int rad;
	
	private float xSpeed;
	
	private float ySpeed;
	
	private int xDirection;
	
	private int yDirection;
	
	public Persona() {
		super();
		rad=10;
		xSpeed=0.5f;
		ySpeed=0.5f;
		xDirection=1;
		yDirection=1;
		
		randomPos();
		
	}
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosY() {
		return posY;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	public void movimiento() {
		posX=posX+(xSpeed*xDirection);
		posY=posY+(ySpeed*yDirection);
		
		if(posX>800-rad || posX<rad) {
			xDirection*=-1;
		}
		if(posY>600-rad || posY<rad) {
			yDirection*=-1;
		}
		
	}
	
	public void checkCollision(CopyOnWriteArrayList<Persona> personas, Simulacion sim ) {
		for(Persona p : personas) {
			float p1x=p.getPosX()+rad;
			float p1y=p.getPosY()+rad;
			float p2x=getPosX()+rad;
			float p2y=getPosY()+rad;
			double dist=Math.sqrt(((p1x-p2x)*(p1x-p2x))+((p1y-p2y)*(p1y-p2y)));
			if(dist<=rad) {
				xDirection*=-1;
				yDirection*=-1;
//				p.setxDirection(p.getxDirection()*-1);
//				p.setyDirection(p.getyDirection()*-1);
				if(this instanceof Sano) {
					if(p instanceof Infectado) {
						sim.infectar(this);
					}
				}
				
			}
		}
	}
	
	public void randomPos() {
		Random r = new Random();
		int low = 10;
		int high = 790;
		int result = r.nextInt(high-low) + low;
		posX=result;
		high=590;
		int resultY=r.nextInt(high-low) + low;
		posY=resultY;
	}
	public int getRad() {
		return rad;
	}
	public void setRad(int rad) {
		this.rad = rad;
	}
	public float getxSpeed() {
		return xSpeed;
	}
	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}
	public float getySpeed() {
		return ySpeed;
	}
	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}
	public int getxDirection() {
		return xDirection;
	}
	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}
	public int getyDirection() {
		return yDirection;
	}
	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}
	
	

}
