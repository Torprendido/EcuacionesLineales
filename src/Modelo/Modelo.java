
package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Modelo {
    
    public static char[][] AbrirTablaTrancicion(){
        try{
            File archivo = new File("src/txts/Tebla.txt");
            BufferedReader txt = new BufferedReader(new FileReader(archivo));
            String s = "";
            String aux;
            int largo = 0, alto = 0;
            do {
                aux = txt.readLine();
                if (aux != null) {
                    alto ++;
                    largo = aux.length();
                }
            } while (aux != null);
            char[][] tabla = new char[largo][alto];
            txt = new BufferedReader(new FileReader(archivo));
            int c = 0;
            do {
                aux = txt.readLine();
                if (aux != null) {
                    for (int i = 0; i < largo; i++) {
                        tabla[i][c] = aux.charAt(i);
                    }
                    c ++;
                }
            } while (aux != null);
            return tabla;
        } catch(IOException e){return new char[0][0];}
    }
    
    public static String[][] ExtraerSeparadores() {
        try {
            int n = 0;
            BufferedReader caracteres = new BufferedReader(new FileReader(new File("src/txts/separadores.txt")));
            while (caracteres.readLine() != null) n ++;
            String aux[][] = new String[n][3];
            caracteres = new BufferedReader(new FileReader(new File("src/txts/separadores.txt")));
            for (String[] a: aux) {
                String linea = caracteres.readLine();
                a[0] = linea.substring(25, linea.length());
                a[1] = linea.substring(0, 2).replaceAll(" ", "");
                a[2] = linea.substring(2, 25);
            }
            return aux;
        } catch (IOException ex) {
            return new String[3][0];
        }
    }
    
    public static String[][] ExtraerAutomatas() {
        try{
            int n = 0;
            BufferedReader palabras = new BufferedReader(new FileReader(new File("src/txts/automatas.txt")));
            while (palabras.readLine() != null) n ++;
            String aux[][] = new String[n][2];
            palabras = new BufferedReader(new FileReader(new File("src/txts/automatas.txt")));
            for (String[] a : aux) {
                String linea = palabras.readLine();
                a[0] = linea.substring(1, linea.length());
                a[1] = linea.substring(0, 1);
            }
            return aux;
        } catch (IOException ex) {
            return new String[2][0];
        }
    }
    
    public static String tokenToLexema(String numeroToken) {
        String[][] separadores = ExtraerSeparadores();
        String[][] automatas = ExtraerAutomatas();
        if (numeroToken.compareTo("1") == 0) return "+";
        if (numeroToken.compareTo("2") == 0) return "-";
        if (numeroToken.compareTo("3") == 0) return "=";
        for (String[] s: separadores) if (numeroToken.compareTo(s[1]) == 0) return s[2];
        for (String[] a: automatas) if (numeroToken.compareTo(a[1]) == 0) return a[0];
        return "nada";//espero que la encuentre
    }
    
    public static String producto(String archivo, String factorA, String factorB) {
        try {
            Workbook libro = Workbook.getWorkbook(new File("src/txts/" + archivo));
            int altoTabla = libro.getSheet(0).getRows();
            String[][] tabla = new String[libro.getSheet(0).getColumns()][altoTabla];
            for (int i = 0; i < tabla.length; i++)
                for (int j = 0; j < altoTabla; j++)
                    tabla[i][j] = libro.getSheet(0).getCell(i, j).getContents();
            int x = 0, y = 0;
            for (int i = 0; i < tabla.length; i++) if (tabla[i][0].compareTo(factorA) == 0) x = i;
            for (int j = 0; j < altoTabla; j++) if (tabla[0][j].compareTo(factorB) == 0) y = j;
            return tabla[x][y];
        } catch (BiffException | IOException ex) {
            return "";
        }
    }
}
