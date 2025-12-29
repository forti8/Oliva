package compiler.commands;

public class Operation {

    public boolean floatOperation = false;
    private Object leftValue;
    private Object rightValue;

    public Operation(Object lV, Object rV) {
        this.leftValue = (lV == null) ? 0 : lV;
        this.rightValue = (rV == null) ? 0 : rV;
    }

    public Object sum() {
        if (leftValue instanceof String || rightValue instanceof String) {
            return concat();
        }

        if (!floatOperation) {
            return ((Number) leftValue).intValue() + ((Number) rightValue).intValue();
        }
        return ((Number) leftValue).floatValue() + ((Number) rightValue).floatValue();
    }

    public Object sub() {
        if (!floatOperation) {
            return (int) this.leftValue - (int) this.rightValue;
        }
        return (float) this.leftValue - (float) this.rightValue;
    }

    public Object mul() {
        if (!floatOperation) {
            return (int) this.leftValue * (int) this.rightValue;
        }
        return (float) this.leftValue * (float) this.rightValue;
    }

    public Object div() {
        if (!floatOperation) {
            if ((int) this.rightValue == 0) throw new ArithmeticException("Divisão por zero");
            return (int) this.leftValue / (int) this.rightValue;
        }
        if ((float) this.rightValue == 0.0f) throw new ArithmeticException("Divisão por zero");
        return (float) this.leftValue / (float) this.rightValue;
    }

    public Object sqrt() {
        double val = floatOperation ? (float) leftValue : (int) leftValue;
        return (float) Math.sqrt(val);
    }

    public Object concat() {
        return String.valueOf(leftValue) + String.valueOf(rightValue);
    }
}