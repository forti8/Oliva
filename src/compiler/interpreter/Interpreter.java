package compiler.interpreter;
import compiler.token.Token; 
import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    private List<Token> lineTokens = new ArrayList<Token>();

    public Interpreter (List<Token> list) {
        super();
        this.lineTokens = list;
    }

    public void activate () {
        for (int i = 0; i < this.lineTokens.size(); i++) {
            Token currentToken = this.lineTokens.get(i);

            // Logic here
        }
    }
}