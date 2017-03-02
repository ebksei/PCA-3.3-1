package pca.laboratorio4i;

class CuentaException extends Exception {

	public CuentaException(String codigo, String mensaje) {
		super("EXCEPCION en cuenta " + codigo + ": " + mensaje);
	}
}
