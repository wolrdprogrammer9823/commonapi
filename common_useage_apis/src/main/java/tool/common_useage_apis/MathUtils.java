package tool.common_useage_apis;
import java.util.Stack;

public class MathUtils {

    /*int转二进制值*/
    public static Stack<Integer> int2bit(int data) {

        Stack<Integer> stack = new Stack<>();

        while (data / 2 != 0) {

            int value = data % 2;
            stack.push(value);
            data = data / 2;
        }

        stack.push(data % 2);

        return stack;
    }

    /*获取int的某一位bit值*/
    public static int obtainBitValue(int value, int position) {

        return (value >> position) & 0x01;
    }
}