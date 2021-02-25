import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class NotationConverter {

    private static char[][] priorities = new char[][] { new char[] { '+', '-' }, new char[] { '*', '/' }};

    public static String toPostfix(String infixNotation) {
        Stack<String> operators = new Stack<>();
        Queue<String> operands = new LinkedList<>();
        StringBuilder digitBuilder = new StringBuilder();
        for(char symbol : infixNotation.toCharArray()) {
            if(Character.isDigit(symbol))
                digitBuilder.append(symbol);
            else {
                if (digitBuilder.length() != 0) {
                    operands.add(digitBuilder.toString());
                    digitBuilder.delete(0, digitBuilder.length());
                }

                if (!operators.empty()) {
                    String lastOperator = operators.peek();
                    while (!operators.empty() && IsLowerPriority(lastOperator.charAt(0), symbol)) {
                        String operator = operators.pop();
                        operands.add(operator);
                        if (!operators.empty())
                            lastOperator = operators.peek();
                    }
                }
                operators.push(String.valueOf(symbol));
            }
        }
        operands.add(digitBuilder.toString());
        while (operators.size() != 0) {
            String operator = operators.pop();
            operands.add(operator);
        }
        return String.join(" ", operands);
    }

    public static boolean IsLowerPriority(char lastOperator, char currentOperator) {
        byte lastPriority = 0, currentPriority = 0;
        for(byte i = 0; i < priorities.length; i++)
        {
            char[] operators = priorities[i];
            if (contains(operators, lastOperator)) lastPriority = i;
            if (contains(operators, currentOperator)) currentPriority = i;
        }
        return currentPriority <= lastPriority;
    }

    public static boolean contains(char[] array, char value) {
        for(char symbol : array)
            if(symbol == value)
                return true;
        return false;
    }


}
