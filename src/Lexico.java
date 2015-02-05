
public class Lexico {
    
    Entrada ent;
    char current_symbol;
    
    public Lexico(Entrada ent) {
        this.ent = ent;
        current_symbol = ent.getNextSymbol();
        
    }
    public Token getNextToken () throws Exception{
        String lexema = "";
        int estado = 0;
        
         while(true){
             switch (estado){
                 case 0 : 
                     if(Character.isDigit(current_symbol)){
                         lexema += current_symbol;
                         current_symbol = ent.getNextSymbol();
                         estado = 1;
                     }else if (current_symbol == '+'){
                         lexema += current_symbol;
                         current_symbol = ent.getNextSymbol();
                         estado = 2;
                     }else if (current_symbol == '-'){
                         lexema += current_symbol;
                         current_symbol = ent.getNextSymbol();
                         estado = 3;
                     }else if (current_symbol == '/'){
                         lexema += current_symbol;
                         current_symbol = ent.getNextSymbol();
                         estado = 4;
                     }else if (current_symbol == '*'){
                         lexema += current_symbol;
                         current_symbol = ent.getNextSymbol();
                         estado = 5;
                     }else if (current_symbol == '('){
                         lexema += current_symbol;
                         current_symbol = ent.getNextSymbol();
                         estado = 6;
                     }else if (current_symbol == ')'){
                         lexema += current_symbol;
                         current_symbol = ent.getNextSymbol();
                         estado = 7;
                     }else if (current_symbol == 0){
                         current_symbol = ent.getNextSymbol();
                         estado = 8;
                     }else if (Character.isWhitespace(current_symbol)){
                         current_symbol = ent.getNextSymbol();
                         estado = 0;
                     }else{
                         throw new Exception("Current Symbol no reconocido");
                     }
                     
                     break;
                 case 1 : 
                     if(Character.isDigit(current_symbol)){
                         lexema += current_symbol;
                         current_symbol = ent.getNextSymbol();
                         estado = 1;
                     }else{
                         return new Token (TokenType.numero, lexema);
                     }
                     break;
                 case 2 : 
                     return new Token (TokenType.suma, lexema);
                 case 3 : 
                     return new Token (TokenType.resta, lexema);
                 case 4 : 
                     return new Token (TokenType.division, lexema);
                 case 5 : 
                     return new Token (TokenType.multiplicacion, lexema);
                 case 6 : 
                     return new Token (TokenType.parentesis_izquierdo, lexema);
                 case 7 : 
                     return new Token (TokenType.parentesis_derecho, lexema);
                 case 8 : 
                     return new Token (TokenType.end, lexema);
                     
                     
                     
                     
                     
                     
                         
      
             }
         }
    }
    
    public static void main (String args[]) throws Exception{
        Lexico lexico = new Lexico(new Entrada("(12 +3)*5"));
        Token current = lexico.getNextToken();
        while(current.type != TokenType.end){
            System.out.println(current.lexema +" "+current.type);
            current = lexico.getNextToken();
        }
    }
}
