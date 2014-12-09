import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import Modelo.Modelo;

public class Sintaxis {
    
    private static ArrayList<String> ids;
    private static ArrayList<String> lineas;
    private static ArrayList<String> nombres;
    private static String[][] tabla;
    private static int altoTabla = 0;
    private static ArrayList<Produccion> producciones;
    
    private static void CrearTablaSintaxis() {
        try {
            Workbook libro = Workbook.getWorkbook(new File("src/txts/tablaSintaxis.xls"));
            altoTabla = libro.getSheet(0).getRows();
            tabla = new String[libro.getSheet(0).getColumns()][altoTabla];
            for (int i = 0; i < tabla.length; i++)
                for (int j = 0; j < altoTabla; j++)
                    tabla[i][j] = libro.getSheet(0).getCell(i, j).getContents();
        } catch (BiffException | IOException ex) {}
    }
    
    private static void CrearListaProduccion() {
        try {
            BufferedReader txt = new BufferedReader(new FileReader(new File("src/txts/listaProduccion.txt")));
            String aux = "";
            do {
                if (aux != null & aux.compareTo("") != 0) producciones.add(new Produccion(aux));
                aux = txt.readLine();
            }
            while (aux != null);
        } catch (IOException e) {}
    }
    
    private static String AnalizarGramatica() {
        ArrayList<String> pila = new ArrayList();
        pila.add("$");
        pila.add(tabla[tabla.length - 1][1]);
        MatrizCuadrada mat =  new MatrizCuadrada();
        ArrayList<Lexema> vectorFila = new ArrayList();
        int x, y;
        while (!ids.isEmpty()) {
            if (comapararTopes(ids, pila)) {
                Lexema lex = new Lexema();
                lex.setId(ids.get(ids.size() - 1));
                lex.setLexema(nombres.get(nombres.size() - 1));
                vectorFila.add(lex);
                cotarCabezas(ids, pila);
            } else if (checarλ(pila)) pila.remove(pila.size() - 1);
            else {
                x = BuscarX(ids.get(ids.size() - 1));
                y = BuscarY(pila.get(pila.size() - 1));
                if (x == tabla.length) 
                    return ids.get(ids.size() - 1) + " en la Ecuacion numero: " + lineas.get(lineas.size() - 1) + "\n";
                if (y == altoTabla)
                    return "Esperaba:" +
                            Modelo.tokenToLexema(pila.get(pila.size() - 1)) +
                            " en la Ecuacion numero: " +
                            lineas.get(lineas.size() - 1) +
                            ", encontro " + Modelo.tokenToLexema(ids.get(ids.size() - 1)) + "\n";
                if (tabla[x][y].compareTo("E") == 0) 
                    return "Esperaba: " + Esperando(y) +
                            " en la Ecuacion numero: " +
                            lineas.get(lineas.size() - 1) +
                            ", encontro " + Modelo.tokenToLexema(ids.get(ids.size() - 1)) + "\n";
                int numPro = Integer.parseInt(tabla[x][y]);
                if (numPro == 1 | numPro == 2 | numPro == 3 | numPro == 4) {
                    mat.insertVector(vectorFila);
                    vectorFila = new ArrayList();
                }
                pila.remove(pila.size() - 1);
                agregarGramtica(pila, producciones.get(numPro - 1).getListas());
            }
        }
        return mat.resolverEcuacion();
    }
    
    public static String mensajeSintaxis(ArrayList<Lexema> lexemas) {
        ids = new ArrayList();
        lineas = new ArrayList();
        nombres = new ArrayList();
        producciones = new ArrayList();
        ids.add("$");
        lineas.add(lexemas.get(lexemas.size() - 1).getLinea());
        nombres.add("nada");
        for (int j = lexemas.size() - 1; j >= 0; j --) {
            ids.add(lexemas.get(j).getId());
            lineas.add(lexemas.get(j).getLinea());
            nombres.add(lexemas.get(j).getLexema());
        }
        CrearTablaSintaxis();
        CrearListaProduccion();
        return AnalizarGramatica();
    }

    private static int BuscarX(String s) {
        //System.out.print(s + " -> codigo ");
        for (int i = 0; i < tabla.length; i++) if (tabla[i][0].compareTo(s) == 0) return i;
        return tabla.length;
    }

    private static int BuscarY(String s) {
        //System.out.println(s + " -> pila");
        for (int i = 0; i < altoTabla; i++) if (tabla[tabla.length - 1][i].compareTo(s) == 0) return i;
        return altoTabla;
    }

    private static boolean comapararTopes(ArrayList<String> ids, ArrayList<String> pila) {
        return ids.get(ids.size() - 1).compareTo(pila.get(pila.size() - 1)) == 0;
    }

    private static void cotarCabezas(ArrayList<String> ids, ArrayList<String> pila) {
        //System.out.print(ids.get(ids.size() - 1) + " ");
        //System.out.println(pila.get(pila.size() - 1) + ".                        Cortar cabezas");
        ids.remove(ids.size() - 1);
        nombres.remove(nombres.size() - 1);
        lineas.remove(lineas.size() - 1);
        pila.remove(pila.size() - 1);
    }

    private static void agregarGramtica(ArrayList<String> pila, ArrayList<String> l) {
        for (int i = l.size() - 1; i > 0; i --) {
            pila.add(l.get(i));
        }
    }

    private static boolean checarλ(ArrayList<String> pila) {
        return (pila.get(pila.size() - 1).compareTo("λ") == 0);
    }

    private static String Esperando(int y) {
        String s = "";
        for (int i = 0; i < tabla.length - 1; i++)
            if (tabla[i][y].compareTo("E") != 0) s = s  + Modelo.tokenToLexema(tabla[i][0]) + ", ";
        return s;
    }    
}