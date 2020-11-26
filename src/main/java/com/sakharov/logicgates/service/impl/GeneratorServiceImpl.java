package com.sakharov.logicgates.service.impl;

import com.sakharov.logicgates.service.GeneratorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Override
    public List<List<Boolean>> generate(int length) {
        List<List<Boolean>> inputsList = new ArrayList<>();

        for (int it = 0; it < Math.pow(2, length); it++) {
            int i = it;

            List<Boolean> inputs = IntStream
                    .range(0, length)
                    .collect(
                            ArrayList::new,
                            (l, j) -> l.add(getValueByPosition(i, j, length)),
                            List::addAll
                    );

            inputsList.add(inputs);
        }

        return inputsList;
    }

    boolean getValueByPosition(int number, int position, int length) {
        String binString = Integer.toBinaryString(number);
        List<Boolean> binary = new ArrayList<>();

        for (String bin: ("0".repeat(length-binString.length()) + binString).split(""))
            binary.add(bin.equals("1"));

        return binary.get(position);
    }
}
