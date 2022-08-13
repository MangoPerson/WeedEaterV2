import com.github.mangoperson.weedeaterv2.util.Comp;

import java.util.Arrays;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        double[][][] mat = Comp.matrix((x, y) -> new double[] {x, y, 0}, Comp.range(20), Comp.range(25));

        System.out.println(Arrays.deepToString(mat));
    }
}
