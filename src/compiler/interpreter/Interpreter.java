package compiler.interpreter;

import compiler.commands.Variable;
import compiler.runtime.VarTable;
import compiler.commands.Operation;
import compiler.token.Token; 
import java.util.ArrayList;
import java.util.List;

public class Interpreter {
   private List<Token> lineTokens = new ArrayList<Token>();
   private VarTable varTable;

   public enum oprtType {
      SUM, SUB, MUL, DIV, SQRT, CONCAT 
   }

   public Interpreter (List<Token> list, VarTable vt) {
      this.lineTokens = list;
      this.varTable = vt;
   }

   private Token getToken(int index) {
      if (index >= 0 && index < lineTokens.size()) {
         return lineTokens.get(index);
      }
      return null;
   }

   public Object operationInterpreter (Object a, Object b, boolean f, oprtType op) {
      Operation oprt = new Operation(a, b);
      oprt.floatOperation = f;

      switch (op) {
         case SUM:    return oprt.sum();
         case SUB:    return oprt.sub();
         case MUL:    return oprt.mul();
         case DIV:    return oprt.div();
         case SQRT:   return oprt.sqrt();
         case CONCAT: return oprt.concat();
         default:     return null;
      }
   }

   public void activate() {
      int i = 0;
      int size = this.lineTokens.size();

      while (i < size) {
         Token currentToken = this.getToken(i);
         if (currentToken == null) { i++; continue; }

         if (currentToken.type == Token.TokenType.RESERVED_WORD) {

            /*
               ====================================
               == Variable ========================
               ====================================
            */

            if (currentToken.content.equals("var")) {
               i++;
               Token nameToken = this.getToken(i);
            
               if (nameToken != null && nameToken.type == Token.TokenType.WORD) {
                  String varName = nameToken.content;
                  i++;
                  
                  Token colonToken = this.getToken(i);
                  if (colonToken != null && colonToken.type == Token.TokenType.COLON) {
                     i++;
                     Token typeToken = this.getToken(i);
                     
                     if (typeToken != null) {
                        String varTypeStr = typeToken.content;
                        i++;
                        
                        Token eqToken = this.getToken(i);
                        if (eqToken != null && eqToken.type == Token.TokenType.EQUALS) {
                           i++;
                           Token valueToken = this.getToken(i);
                           
                           if (valueToken != null) {
                              Object finalValue;
                              if (isOpToken(valueToken.type)) {
                                 finalValue = solveExpression(valueToken, i);
                                 i++; 
                              } else {
                                 finalValue = extractLiteral(valueToken);
                              }

                              Variable var = new Variable();
                              var.variableName = varName;
                              var.variableType = mapVariableType(varTypeStr);
                              var.define(finalValue);
                              varTable.define(var);
                           }
                        }

                        else {
                           Variable var = new Variable();
                           var.variableName = varName;
                           var.variableType = mapVariableType(varTypeStr);
                           var.define(null);
                           varTable.define(var);
                        }
                     }
                  } else {
                     System.out.println("Syntax Error: Expected ':' after variable name.");
                  }
               }
            }

            /*
               =============================
               = print =====================
               =============================
            */
           if (currentToken.content.equals("print")) {
            i++;
            Token parToken = this.getToken(i);

            if (parToken != null && parToken.type == Token.TokenType.OPEN_PAREN) {
               i++;

               while (i < size) {
                     Token contentToken = this.getToken(i);

                     if (contentToken == null || contentToken.type == Token.TokenType.CLOSE_PAREN) {
                        break;
                     }

                     Object value;
                     if (isOpToken(contentToken.type)) {
                        value = solveExpression(contentToken, i);
                        i++;
                     } else {
                        value = extractLiteral(contentToken);
                     }

                     System.out.print(value);

                     i++;
                     Token next = this.getToken(i);

                     if (next != null && (next.type == Token.TokenType.COMMA || next.type == Token.TokenType.CLOSE_PAREN)) {
                        i++;
                     } 
                     
                     else {
                        throw new RuntimeException("the comma character is missing after an argument of the \"print\" command");
                     }
               }
               System.out.println();
            } 
            
            else {
               throw new RuntimeException("Syntax Error: Expected '(' after print");
            }
         }
      }

        else if (currentToken.type == Token.TokenType.WORD) {
            int vI = varTable.find(currentToken.content);
            
            if (vI != -1) {
               Variable v = varTable.call(vI);
               i++;
               
               Token equalsToken = this.getToken(i);
               if (equalsToken != null && equalsToken.type == Token.TokenType.EQUALS) {
                     i++;
                     Token valueToken = this.getToken(i);
                     
                     if (valueToken != null) {
                        Object finalValue;

                        if (isOpToken(valueToken.type)) {
                           finalValue = solveExpression(valueToken, i);

                        } else {
                           finalValue = extractLiteral(valueToken);
                           System.out.println(finalValue);
                           i++; // +
                           Token next = this.getToken(i);

                           System.out.println(next.type);
                           while (next != null && isOpToken(next.type)) {
                                 i++;
                                 Token nextVal = this.getToken(i);

                                 System.out.println(nextVal.type);
                                 if (nextVal != null) {
                                     
                                    Object nextLiteral = extractLiteral(nextVal);
                                    finalValue = sumObjects(finalValue, nextLiteral);
                                 }

                                 i++;
                                 next = this.getToken(i);
                           }
                        }

                        // Update the variable in the table
                        v.put(finalValue);
                        varTable.put(vI, v);
                     }
               }
            }
         }

         i++;
      }
   }


   private Object sumObjects(Object a, Object b) {
      if (a instanceof String || b instanceof String) {
         return String.valueOf(a) + String.valueOf(b);
      }

      if (a instanceof Number && b instanceof Number) {
        
         if (a instanceof Double || b instanceof Double) {
               return ((Number) a).doubleValue() + ((Number) b).doubleValue();
         }
         
         return ((Number) a).intValue() + ((Number) b).intValue();
      }

      if (a instanceof Boolean || b instanceof Boolean) {
         double valA = (a instanceof Boolean) ? ((boolean) a ? 1.0 : 0.0) : ((Number) a).doubleValue();
         double valB = (b instanceof Boolean) ? ((boolean) b ? 1.0 : 0.0) : ((Number) b).doubleValue();
         return valA + valB;
      }

      throw new RuntimeException("Runtime Error: Cannot add types " + 
                                 a.getClass().getSimpleName() + " and " + 
                                 b.getClass().getSimpleName());
   }

   private Object solveExpression(Token opToken, int currentIndex) {
      String content = opToken.content;
      char opChar = content.charAt(content.length() - 1);
      String valAStr = content.substring(0, content.length() - 1);
      
      Object valA;
      if (opToken.type == Token.TokenType.OP_FLOAT) {
         valA = Float.parseFloat(valAStr);
      }  
      else if (opToken.type == Token.TokenType.OP_STR) {
         valA = valAStr;
      }

      Token nextToken =
      else if (opToken.type == Token.TokenType.OP_WORD) {
         String varName = content.substring(0, content.length() - 1); 
         int idx = varTable.find(varName);
         valA = (idx != -1) ? varTable.call(idx).call() : 0;
      }
      else {
         valA = Integer.parseInt(valAStr);
      }

      Token nextToken = this.getToken(currentIndex + 1);
      Object valB = extractLiteral(nextToken);

      oprtType type;
      if (opChar == '+') type = (opToken.type == Token.TokenType.OP_STR) ? oprtType.CONCAT : oprtType.SUM;
      else if (opChar == '-') type = oprtType.SUB;
      else if (opChar == '*') type = oprtType.MUL;
      else type = oprtType.DIV;

      return operationInterpreter(valA, valB, (opToken.type == Token.TokenType.OP_FLOAT), type);
   }

   private Object extractLiteral(Token t) {
      if (t == null) return null;
      switch (t.type) {
         case INT:   return Integer.parseInt(t.content);
         case FLOAT: return Float.parseFloat(t.content);
         case STR:   return t.content;
         case WORD:
            int idx = varTable.find(t.content);
            return (idx != -1) ? varTable.call(idx).call() : null;
         default: return t.content;
      }
   }

   private boolean isOpToken(Token.TokenType type) {
      return type == Token.TokenType.OP_INT || type == Token.TokenType.OP_FLOAT || type == Token.TokenType.OP_STR || type == Token.TokenType.OP_WORD;
   }

   private Variable.varType mapVariableType(String type) {
      switch (type) {
         case "int":   return Variable.varType.INT;
         case "float": return Variable.varType.FLOAT;
         case "str":   return Variable.varType.STR;
         case "bool":  return Variable.varType.BOOL;
         default:      return Variable.varType.CHAR;
      }
   }
}