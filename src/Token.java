
public class Token {
    TokenType type;
    String lexema;

    public Token(TokenType type, String lexema) {
        this.type = type;
        this.lexema = lexema;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
}
