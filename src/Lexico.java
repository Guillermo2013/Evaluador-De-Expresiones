
public class Lexico {
    String lexema;
    Entrada ent;
    int estado;
    char current_symbol;

    public Lexico(Entrada ent) {
        this.ent = ent;
        current_symbol = ent.getNextSymbol();
    }
    
    void consumirYCambiarEstado(int estado){
        lexema += current_symbol;
        current_symbol = ent.getNextSymbol();
        this.estado = estado;
    }

    public Token getNextToken() throws Exception {
        lexema = "";
        estado = 0;

        while (true) {
            switch (estado) {
                case 0:
                    if(current_symbol == '"'){
                        consumirYCambiarEstado(5);
                    }else if(Character.isLetter(current_symbol)){
                        consumirYCambiarEstado(7);
                    }else if(current_symbol == '<'){
                        consumirYCambiarEstado(1);
                    }else if(current_symbol == '>'){
                        consumirYCambiarEstado(2);
                    }else if(current_symbol == '/'){
                        consumirYCambiarEstado(3);
                    }else if(current_symbol == '='){
                        consumirYCambiarEstado(9);
                    }else if(current_symbol == 0){
                        consumirYCambiarEstado(10);
                    }else if(Character.isWhitespace(current_symbol)){
                        current_symbol = ent.getNextSymbol();
                    }else{
                        throw new Exception("Simbolo no reconocido");
                    }
                    break;
                case 1:
                    if(current_symbol == '/'){
                        consumirYCambiarEstado(8);
                    }else{
                        return new Token(TokenType.abrirTag, lexema);
                    }
                    break;
                case 2:
                    return new Token(TokenType.cerrarTag, lexema);
                case 3:
                    if(current_symbol == '>'){
                        consumirYCambiarEstado(4);
                    }else{
                        throw new Exception("Se esperaba un mayor");
                    }
                    break;
                case 4:
                    return new Token(TokenType.CERRAR_TAG_SIN_HIJOS, lexema);
                case 5:
                    if(Character.isLetterOrDigit(current_symbol)){
                        consumirYCambiarEstado(5);
                    }else if(current_symbol == '"'){
                        consumirYCambiarEstado(6);
                    }else{
                        throw new Exception("Cadena mal formada");
                    }
                    break;
                case 6:
                    return new Token(TokenType.VALOR, lexema);
                case 7:
                    if(Character.isLetterOrDigit(current_symbol)){
                        consumirYCambiarEstado(7);
                    }else{
                        return new Token(TokenType.ID, lexema);
                    }
                    break;
                case 8:
                    return new Token(TokenType.abrirTagFinal, lexema);
                case 9:
                    return new Token(TokenType.asignacion, lexema);
                case 10:
                    return new Token(TokenType.EOF, lexema);
                default:
                    throw new Exception("Error Grave");
            }
        }
    }

    public static void main(String args[]) throws Exception {
        Lexico lexico = new Lexico(new Entrada("<alumno nombre=\"JuanCarlosEspinoza\"/ >"));
        Token current = lexico.getNextToken();
        while (current.type != TokenType.EOF) {
            System.out.println(current.lexema + " " + current.type);
            current = lexico.getNextToken();
        }
    }
}
