package com.logicgates.service.impl;

import com.logicgates.service.GeneratorService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

/**
 * Implementation of service for generating inputs for truth table.
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
    /**
     * Method for generating all possible values of inputs for truth table.
     *
     * @param numInputs number of inputs.
     * @return all possible values of inputs.
     */
    @Override
    public List<List<Boolean>> generate(int numInputs) {
        List<List<Boolean>> inputsList = new ArrayList<>();

        for (int it = 0; it < Math.pow(2, numInputs); it++) {
            int i = it;

            List<Boolean> inputs = IntStream
                    .range(0, numInputs)
                    .collect(
                            ArrayList::new,
                            (l, j) -> l.add(getValueByPosition(i, j, numInputs)),
                            List::addAll
                    );

            inputsList.add(inputs);
        }

        return inputsList;
    }

    /**
     * Method for giving binary value at right position of binary representation of number.
     *
     * @param number number, which will be converted to binary form.
     * @param position position in converted number, in which needs to be return value.
     * @param numInputs number of inputs.
     * @return binary value of converted number in a given position.
     */
    private boolean getValueByPosition(int number, int position, int numInputs) {
        String binString = Integer.toBinaryString(number);
        List<Boolean> binary = new ArrayList<>();

        for (String bin : ("0".repeat(numInputs - binString.length()) + binString).split("")) {
            binary.add(bin.equals("1"));
        }

        return binary.get(position);
    }
}
