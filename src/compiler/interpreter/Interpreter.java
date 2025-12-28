package compiler.interpreter;

import compiler.commands.Variable;
import compiler.runtime.VarTable;
import compiler.token.Token; 
import java.util.ArrayList;
import java.util.List;

public class Interpreter {
   private List<Token> lineTokens = new ArrayList<Token>();
   private VarTable varTable;

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
                                 case "str": variableType = "str"; break;
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
                                          case STR:
                                             if(variableType.equals("str")) {
                                                Variable var = new Variable();
                                                var.variableName = variableName;
                                                var.variableType = Variable.varType.STR;
                                                var.define(currentToken.content);

                                                varTable.define(var);
                                                i++;
                                                continue;
                                             }

                                             else {
                                                System.out.println("Error: attempt to set a value of a different type than the original type of the variable");
                                             }
                                          break;

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

                                             else {
                                                System.out.println("Error: attempt to set a value of a different type than the original type of the variable");
                                             }
                                          break;

                                          case FLOAT:
                                             if (variableType.equals("float")) {
                                                Variable var = new Variable();
                                                var.variableName = variableName;
                                                var.variableType = Variable.varType.FLOAT;
                                                var.define(Float.parseFloat(currentToken.content));

                                                varTable.define(var);
                                                i++;
                                                continue;
                                             }

                                             else {
                                                System.out.println("Error: attempt to set a value of a different type than the original type of the variable");
                                             }
                                          break;

                                          case CHAR: 
                                             if (variableType.equals("char")) {
                                                Variable var = new Variable();
                                                var.variableName = variableName;
                                                var.variableType = Variable.varType.CHAR;
                                                char[] c = currentToken.content.toCharArray();
                                                var.define(c[0]);

                                                varTable.define(var);
                                                i++;
                                                continue;
                                             }

                                             else {
                                                System.out.println("Error: attempt to set a value of a different type than the original type of the variable");
                                             }
                                          break;

                                          case BOOL: 
                                             if (variableType.equals("bool")) {
                                                Variable var = new Variable();
                                                var.variableName = variableName;
                                                var.variableType = Variable.varType.BOOL;
                                                var.define(Boolean.parseBoolean(currentToken.content));

                                                varTable.define(var);
                                                i++;
                                                continue;
                                             }

                                             else {
                                                System.out.println("Error: attempt to set a value of a different type than the original type of the variable");
                                             }
                                          break;
                                       }
                                 }
                              }
                              continue; 
                           } 
                           
                           else {
                              System.out.println("Error: Variable type is missing or invalid.");
                           }
                     } 
                     
                     else {
                           System.out.println("Error: Expected ':' after variable name.");
                     }
                  } 
                  
                  else {
                     System.out.println("Error: Expected a valid variable name after 'var'.");
                     return;
                  }
               }

               if (currentToken.content.equals("print")) {
                  // nothing, yet...
               }
         }

         else if (currentToken.type == Token.TokenType.WORD) {
               int variableIndex = varTable.find(currentToken.content);

               if (variableIndex != -1) {
                  Variable variableGet = varTable.call(variableIndex);

                  i++;
                  currentToken = this.getToken(i);

                  if (currentToken != null && currentToken.type == Token.TokenType.EQUALS) {
                     String variableType = "";

                     if (currentToken != null) {
                        switch (variableGet.variableType) {
                           case INT: variableType = "int"; break;
                           case FLOAT: variableType = "float"; break;
                           case CHAR: variableType = "char"; break;
                           case BOOL: variableType = "bool"; break;
                           case STR: variableType = "str"; break;
                        }
                     }

                     if (!variableType.isEmpty()) {
                        if (currentToken != null) {

                           i++;
                           currentToken = this.getToken(i);

                           if (currentToken != null) {
                                 switch (currentToken.type) {
                                    case STR:
                                       if(variableType.equals("str")) {
                                          variableGet.put(currentToken.content);
                                          varTable.put(variableIndex, variableGet);
                                          i++;
                                          continue;
                                       }
                                       
                                       else {
                                          System.out.println("Error: attempt to set a value of a different type than the original type of the variable");
                                       }
                                    break;

                                    case INT:
                                       if (variableType.equals("int")) {
                                          variableGet.put(Integer.parseInt(currentToken.content));
                                          varTable.put(variableIndex, variableGet);
                                          i++;
                                          continue;
                                       }

                                       else {
                                          System.out.println("Error: attempt to set a value of a different type than the original type of the variable");
                                       }
                                    break;

                                    case FLOAT:
                                       if (variableType.equals("float")) {
                                          variableGet.put(Float.parseFloat(currentToken.content));
                                          varTable.put(variableIndex, variableGet);
                                          i++;
                                          continue;
                                       }

                                       else {
                                          System.out.println("Error: attempt to set a value of a different type than the original type of the variable");
                                       }
                                    break;

                                    case CHAR: 
                                       if (variableType.equals("char")) {
                                          char[] c = currentToken.content.toCharArray();
                                          variableGet.put(c[0]);
                                          varTable.put(variableIndex, variableGet);
                                          i++;
                                          continue;
                                       }

                                       else {
                                          System.out.println("Error: attempt to set a value of a different type than the original type of the variable");
                                       }
                                    break;

                                    case BOOL: 
                                       if (variableType.equals("bool")) {
                                          variableGet.put(Boolean.parseBoolean(currentToken.content));
                                          varTable.put(variableIndex, variableGet);
                                          i++;
                                          continue;
                                       }

                                       else {
                                          System.out.println("Error: attempt to set a value of a different type than the original type of the variable");
                                       }
                                    break;
                                 }
                           }
                        }
                        continue; 
                     } 
                  }

                  System.out.println(variableGet.call());
                  System.out.println(variableGet.getMemoryLocation());
                  continue;
               }
         }

         i++;
      }
   }
}