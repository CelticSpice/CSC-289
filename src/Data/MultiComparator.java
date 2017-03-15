/**
    Comparator to compare objects using multiple comparators
    CSC-289 - Group 4
    @author Timothy Burns
*/

package Data;

import java.util.Comparator;
import java.util.List;
import static java.util.Arrays.asList;

public class MultiComparator<T> implements Comparator<T> {
    private final List<Comparator<T>> comparators;

    public MultiComparator(Comparator<T>... cmprtrs) {
        comparators = asList(cmprtrs);
    }

    public int compare(T o1, T o2) {
        for (Comparator<T> c : comparators) {
            int result = c.compare(o1, o2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}