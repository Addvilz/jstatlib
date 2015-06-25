import java.util.HashMap;
import java.util.Map;

public class StatLib {

    public boolean test(int[] randomNumbers, int rangeBound) throws IllegalArgumentException {

        if (0 == randomNumbers.length) {
            throw new IllegalArgumentException("randomNumbers is expected to have value");
        }

        if (randomNumbers.length <= 10 * rangeBound)
            return false;

        Map<Integer, Integer> frequencyMap = getFrequencies(randomNumbers);

        return calculate(randomNumbers, rangeBound, frequencyMap);
    }

    private boolean calculate(int[] randomNumbers, int rangeBound, Map<Integer, Integer> frequencyMap) {

        double nRangeBound = (double) randomNumbers.length / rangeBound;
        double chiSquare = 0;

        for (int v : frequencyMap.values()) {
            double f = v - nRangeBound;
            chiSquare += f * f;
        }

        chiSquare /= nRangeBound;

        return Math.abs(chiSquare - rangeBound) <= 2 * Math.sqrt(rangeBound);
    }

    private Map<Integer, Integer> getFrequencies(int[] numbers) {
        Map<Integer, Integer> frequencies = new HashMap<Integer, Integer>();

        for (int i : numbers) {
            if (frequencies.containsKey(i)) {
                frequencies.put(i, frequencies.get(i) + 1);
            } else {
                frequencies.put(i, 1);
            }
        }

        return frequencies;
    }
}
