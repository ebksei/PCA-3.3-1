package pca.laboratorio4i;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CyclicBarrier;

class Banco {

	private Map<String, Cuenta> mapaCuentas;
	private Cajero[] cajeros;
	private final int NUM_CAJEROS = 10;

	private Banco() {
		mapaCuentas = new TreeMap<String, Cuenta>();
		Cuenta cuenta1 = new Cuenta("1111111111");
		mapaCuentas.put("1111111111", cuenta1);
		Cuenta cuenta2 = new Cuenta("2222222222");
		mapaCuentas.put("2222222222", cuenta2);

		//BarreraCiclica barrera = new BarreraCiclica(NUM_CAJEROS);
		CyclicBarrier cb = new CyclicBarrier(NUM_CAJEROS);
		
		cajeros = new Cajero[NUM_CAJEROS];
		for (int i = 0; i < cajeros.length; i++) {
			cajeros[i] = new Cajero(i, this, cb);
		}
	}

	public Cuenta getCuenta(String codigo) {
		return mapaCuentas.get(codigo);
	}

	private void ejecutar() {
		for (int i = 0; i < cajeros.length; i++) {
			cajeros[i].start();
		}
		try {
			for (int i = 0; i < cajeros.length; i++) {
				cajeros[i].join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	
		Collection<Cuenta> coleccionCuentas = mapaCuentas.values();
		for (Cuenta cuenta : coleccionCuentas) {
			try {
				Pantalla.getPantalla().escribir(
						"Cuenta = " + cuenta.getCodigo() +
						" Saldo = " + cuenta.getSaldo());
			} catch (InterruptedException ex) {
				System.out.println("EXCEPCION al escribir saldo: " +
						ex.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		Banco banco = new Banco();
		banco.ejecutar();
	}
}
