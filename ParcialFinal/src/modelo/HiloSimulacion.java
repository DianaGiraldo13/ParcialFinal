package modelo;

public class HiloSimulacion extends Thread{

	private Simulacion simulacion;

	public HiloSimulacion(Simulacion simulacion) {
		super();
		this.simulacion = simulacion;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while(!simulacion.isDetenida()) {
			simulacion.movimiento();
			try {
				simulacion.checkInfection();
			} catch (InfeccionLimiteExeption e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}
}
