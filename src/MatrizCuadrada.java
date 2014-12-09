
import Modelo.Modelo;
import java.util.ArrayList;

public class MatrizCuadrada {

    private final double[][] array;
    private int indiceFilas;
    private String error;

    public MatrizCuadrada() {
        array = new double[27][26];
        indiceFilas = -1;
        error = "";
    }

    public void insertVector(ArrayList<Lexema> vectorFila) {
        if (!vectorFila.isEmpty()) {
            vectorFila.add(0, new Lexema());
            vectorFila.add(0, new Lexema());
            vectorFila.add(new Lexema());
            indiceFilas++;
        }
        String[][] c = Modelo.ExtraerSeparadores();
        for (int j = 2; j < vectorFila.size(); j++) {
            String coeficiente = vectorFila.get(j - 1).getId();
            String signo = vectorFila.get(j - 2).getId();
            for (int i = 3; i < c.length; i++) {
                if (c[i][0].compareTo(vectorFila.get(j).getLexema()) == 0) {
                    if (array[i - 3][indiceFilas] != 0) {
                        error += "Variable " + c[i][0] + " repedida en la Ecuacion " + (indiceFilas + 1) + '\n';
                        return;
                    }
                    array[i - 3][indiceFilas] = coeficiente.contains("5")
                            ? signo.compareTo("2") == 0
                            ? new Double(vectorFila.get(j - 2).getLexema() + vectorFila.get(j - 1).getLexema())
                            : new Double(vectorFila.get(j - 1).getLexema())
                            : coeficiente.compareTo("2") == 0
                            ? -1.0 : 1.0;
                    break;
                } else if (vectorFila.get(j).getLexema().compareTo("=") == 0) {
                    array[array.length - 1][indiceFilas] = vectorFila.get(j + 1).getId().compareTo("2") == 0
                            ? new Double(vectorFila.get(j + 1).getLexema() + vectorFila.get(j + 2).getLexema())
                            : new Double(vectorFila.get(j + 1).getLexema());
                    break;
                }
            }
        }
    }

    public String resolverEcuacion() {
        if (error.length() != 0) {
            return error;
        }
        for (int j = 0; j < indiceFilas + 1; j++) {
            for (int i = 0; i < 26; i++) {
                if (array[i][j] != 0.0) {
                    normalizar(i, j);
                    aplicarGauss(i, j);
                    break;
                }
            }
        }
        for (int j = 0; j < indiceFilas + 1; j++) {
            for (int i = 0; i < 27; i++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        return "";
    }

    private void normalizar(int columna, int fila) {
        double aux = array[columna][fila];
        for (int i = columna; i < 27; i++) array[i][fila] = array[i][fila] / aux;
    }

    private void aplicarGauss(int columna, int fila) {
        for (int j = 0; j < indiceFilas + 1; j++) {
            if (j != fila) {
                double aux = array[columna][j];
                for (int i = columna; i < 27; i++) {
                    array[i][j] = -array[i][fila] * aux + array[i][j];
                }
            }
        }
    }
}
