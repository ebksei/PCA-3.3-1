package pca.laboratorio4i;

// TODO 5: Implementar la barrera ciclica
import java.util.concurrent.Semaphore;

class BarreraCiclica {

	private int numProcesos;
	private Semaphore[] semaforos;

	public BarreraCiclica(int numProcesos) {
		this.numProcesos = numProcesos;
		this.semaforos = new Semaphore[numProcesos];
		for (int i = 0; i < numProcesos; i++) {
			this.semaforos[i] = new Semaphore(0);
		}
	}

	public void esperar(int idProceso) throws InterruptedException {
		for (int i = 0; i < numProcesos; i++) {
			if (i != idProceso) {
				this.semaforos[i].release();
			}
		}
		for (int i = 0; i < numProcesos - 1; i++) {
			this.semaforos[idProceso].acquire();
		}
	}
}
