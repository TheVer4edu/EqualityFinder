import java.util.*;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        Dictionary<Character, Function<Operands, Integer>> operations = getOperations();
        int[] sequence = Tools.createSequence(9, 2);
        int result = 0;
        int equalTo = 100;
        int count = 100;
        for(String expression : getAllExpressions(sequence, operations)) {
            result = (int) Calculator.deemValue(NotationConverter.toPostfix(expression), operations);
            if(result == equalTo) {
                System.out.println(expression + " = " + equalTo);
                System.out.println(++count);
            }
        }
    }

    public static ArrayList<String> getAllExpressions(int[] sequence, Dictionary<Character, Function<Operands, Integer>> operations) {
        List<Character> keysList = Tools.getArrayListFromEnumeration(operations.keys());
        char[] keys = Tools.getCharArrayFromListCharacter(keysList);
        char[][] allPermutations = getAllPermutations(keys, sequence.length);
        ArrayList<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for(char[] permutation : allPermutations) {
            for(int i = 1; i < sequence.length; i++) {
                char operator = permutation[(i - 1) % permutation.length];
                builder.append(sequence[i - 1]);
                builder.append(operator);
            }
            builder.append(sequence[sequence.length - 1]);
            result.add(builder.toString().replace("#", ""));
            builder.delete(0, builder.length());
        }
        return result;
    }

    public static Dictionary<Character, Function<Operands, Integer>> getOperations() {
        Dictionary<Character, Function<Operands, Integer>> methods = new Hashtable<>();
        methods.put('+', new Function<Operands, Integer>() {
            @Override
            public Integer apply(Operands operands) {
                return operands.getA() + operands.getB();
            }
        });

        methods.put('-', new Function<Operands, Integer>() {
            @Override
            public Integer apply(Operands operands) {
                return operands.getA() - operands.getB();
            }
        });

        methods.put('*', new Function<Operands, Integer>() {
            @Override
            public Integer apply(Operands operands) {
                return operands.getA() * operands.getB();
            }
        });

        methods.put('/', new Function<Operands, Integer>() {
            @Override
            public Integer apply(Operands operands) {
                if(operands.getB() != 0)
                    return operands.getA() / operands.getB();
                return 0;
            }
        });

        methods.put('#', new Function<Operands, Integer>() {
            @Override
            public Integer apply(Operands operands) {
                return Integer.parseInt(operands.getA() + "" + operands.getB());
            }
        });

        return methods;
    }

    public static char[][] getAllPermutations(char[] arr, int L) {
        char[][] result = new char[(int)Math.pow(arr.length, L)][];
        char[] currentPermutation;
        int n;
        for (int i = 0; i < result.length; i++) {
            n = i;
            currentPermutation = new char[L];
            for (int j = 0; j < L; j++) {
                currentPermutation[j] = (arr[n % arr.length]);
                n /= arr.length;
            }
            result[i] = Arrays.copyOf(currentPermutation, currentPermutation.length);
        }
        return result;
    }

}
