import java.util.Dictionary;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class Calculator {

    public static double deemValue(String expression, Dictionary<Character, Function<Operands, Integer>> operations) {
        List<Character> keysList = Tools.getArrayListFromEnumeration(operations.keys());
        char[] keys = Tools.getCharArrayFromListCharacter(keysList);
        Stack<Double> stack = new Stack<>();
        String[] sequence = expression.split(" ");
        for(String element : sequence)
        {
            if(NotationConverter.contains(keys, element.charAt(0)))
            {
                double digit = stack.pop();
                stack.push((double) operations.get(element.charAt(0)).apply(new Operands(stack.pop(), digit)));
            }
            else
                stack.push(Double.valueOf(element));
        }
        return stack.pop();
    }

}
