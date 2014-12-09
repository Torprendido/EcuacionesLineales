public class Automata {
    
    public Automata() {}
    
    public static boolean EsValido(String s, char[][] tabla, Lexema lex) {
        int estado = 0 + 1;
        char[] array = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            boolean encuentra = false;
            for (int j = 0; j < tabla.length - 2; j++) {//no toma en cuenta el caracter 'P' ni N
                if (array[i] == tabla[j][0]) {
                    if (tabla[j][estado] != 'x') {
                        estado = Integer.parseInt(tabla[j][estado] + "") + 1;
                        encuentra = true;
                        break;
                    }
                }
            }
            boolean aux = tabla[tabla.length - 2][estado] != 'v' & i == array.length - 1;
            if (!encuentra || aux) {
                TokensError(estado, lex);
                return false;
            }
        }
        //si todo esta vien mandara el numero de token
        lex.setToken("Numero");
        lex.setId(tabla[tabla.length - 1][estado] + "");
        return true;
    }

    private static void TokensError(int estado, Lexema lex) {
        switch (estado) {
            case 1:
            case 6:
                lex.setToken("Numero no valido");
                lex.setId("Numero no valido");
                break;
        }
    }
}