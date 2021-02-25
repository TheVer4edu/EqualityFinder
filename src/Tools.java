import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Tools {

    public static <E> ArrayList<E> getArrayListFromEnumeration(Enumeration<E> enumeration) {
        ArrayList<E> list = new ArrayList<E>();
        while (enumeration.hasMoreElements()) {
            list.add(enumeration.nextElement());
        }
        return list;
    }

    public static char[] getCharArrayFromListCharacter(List<Character> keysList) {
        Character[] keysBoxedValues = new Character[keysList.size()];
        keysBoxedValues = keysList.toArray(keysBoxedValues);
        char[] keys = new char[keysBoxedValues.length];
        for(int i = 0; i < keys.length; i++)
            keys[i] = keysBoxedValues[i].charValue();
        return keys;
    }

    public static int[] createSequence(int from, int to) {
        int[] result = new int[Math.abs(to - from) + 1];
        for (int i = 0; i < result.length; i++)
            result[i] = to > from ? from++ : from--;
        return result;
    }

}
