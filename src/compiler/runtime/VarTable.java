package compiler.runtime;
import compiler.commands.Variable;
import java.util.List;
import java.util.ArrayList;

public class VarTable {
    private List<Variable> varList = new ArrayList<Variable>();

    public void define (Variable addVar) {
        varList.add(addVar);
        return;
    }

    public int find (String varName) {
        int size = varList.size();
        int i = 0;

        for (i = 0; i < size; i++) {
            Variable v = varList.get(i);

            if (v.variableName.equals(varName)) {
                return i;
            }
        }

        return -1;
    }

    public Variable call (int index) {
        return varList.get(index);
    }

    public void put (int index, Variable newVar) {
        varList.set(index, newVar);
    }

    public void delete (int index) {
        varList.remove(index);
    }
}