
public class Entrada {
    int pos = 0;
    String buffer;
    public Entrada(String buffer) {
        this.buffer = buffer;
    }
    char getNextSymbol(){
        int len = buffer.length();
        if(pos < len){
             return buffer.charAt(pos++);
        }
        return 0;
    }
    
    public static void main(String args[]){
        System.out.println("loquesea");
        Entrada entrada = new Entrada("12+3");
        char nextsymbol = entrada.getNextSymbol();
        
        while(nextsymbol != 0){
            System.out.print (nextsymbol);
            nextsymbol = entrada.getNextSymbol();
        }
    }
}
