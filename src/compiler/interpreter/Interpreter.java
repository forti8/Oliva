package compiler.interpreter;

import compiler.commands.Variable;
import compiler.runtime.VarTable;
import compiler.token.Token; 
import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    private List<Token> lineTokens = new ArrayList<Token>();
    private VarTable varTable = new VarTable();

    public Interpreter (List<Token> list) {
        super();
        this.lineTokens = list;
    }

    private Token getToken(int index) {
        if (index >= 0 & index < lineTokens.size()) {
            return lineTokens.get(index);
        }
        return null;
    }

    public void activate() {
        int i = 0;
        int size = this.lineTokens.size();

        while (i < size) {
            Token currentToken = this.getToken(i);
            
            if (currentToken == null) {
                i++;
                continue;
            }

            if (currentToken.type == Token.TokenType.RESERVED_WORD) {
                if (currentToken.content.equals("var")) {
                    i++;
                    currentToken = this.getToken(i);
                    String variableName = "";

                    if (currentToken != null && currentToken.type == Token.TokenType.WORD) {
                        variableName = currentToken.content;
                        i++;
                        currentToken = this.getToken(i);

                        if (currentToken != null && currentToken.type == Token.TokenType.COLON) {
                            i++;
                            currentToken = this.getToken(i);
                            String variableType = "";

                            if (currentToken != null) {
                                switch (currentToken.content) {
                                    case "int": variableType = "int"; break;
                                    case "float": variableType = "float"; break;
                                    case "char": variableType = "char"; break;
                                    case "bool": variableType = "bool"; break;
                                }
                            }

                            if (!variableType.isEmpty()) {
                                i++;
                                currentToken = this.getToken(i);
                                
                                if (currentToken != null && currentToken.type == Token.TokenType.EQUALS) {
                                    i++;
                                    currentToken = this.getToken(i);

                                    if (currentToken != null) {
                                        switch (currentToken.type) {
                                            case INT:
                                                if (variableType.equals("int")) {

                                                    Variable var = new Variable();
                                                    var.variableName = variableName;
                                                    var.variableType = Variable.varType.INT;
                                                    var.define(Integer.parseInt(currentToken.content));

                                                    varTable.define(var);
                                                    i++;
                                                    continue;
                                                }
                                                break;
                                        }
                                    }
                                }
                                continue; 
                            } 
                            
                            else {
                                System.out.println("Error: Null type");
                            }
                        } 
                        
                        else {
                            System.out.println("Error: Missing ':'");
                        }
                    } 
                    
                    else {
                        System.out.println("Error: Invalid variable name");
                        return;
                    }
                }

                if (currentToken.content.equals("print")) {
                    // nothing, yet...
                }
            }
            i++;
        }
    }
}