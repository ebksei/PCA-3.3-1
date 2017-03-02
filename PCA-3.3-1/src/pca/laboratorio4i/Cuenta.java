package pca.laboratorio4i;

// TODO 2: Conseguir exclusion mutua en el acceso a la cuenta
class Cuenta {

	private String codigo;
	private int saldo;

	public Cuenta(String codigo) {
		this.codigo = codigo;
		saldo = 0;
	}

	public void ingresar(int cantidad) throws CuentaException {
		if (cantidad < 0) {
			throw new CuentaException(codigo,
					"Ingreso de cantidad " + cantidad + " negativa");
		}
		saldo = saldo + cantidad;
	}

	public void retirar(int cantidad) throws CuentaException {
		if (cantidad < 0) {
			throw new CuentaException(codigo,
					"Retirada de cantidad " + cantidad + " negativa");
		}
		if (cantidad > saldo) {
			throw new CuentaException(codigo,
					"Saldo " + saldo + " insuficiente" +
					" para retirada de " + cantidad);
		}
		saldo = saldo - cantidad;
		if (saldo < 0)  {
			throw new CuentaException(codigo,
					"ERROR FATAL: Saldo " + saldo + " negativo");
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public int getSaldo() {
		return saldo;
	}
}
