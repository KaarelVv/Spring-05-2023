package kaarel.kalkulaator;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class Kalkulaator {
    List<Integer> integers = new ArrayList<>(Arrays.asList(31, 52, 24));

    @GetMapping("get-numbers")
    public List<Integer> getIntegers() {
        return integers;
    }

    @GetMapping("add")//add?i=<number>
    public List<Integer> addNumber(@RequestParam Integer i) {
        integers.add(i);
        return integers;
    }

    @GetMapping("deleteOne")
    public List<Integer> deleteNumber() {
        if (!integers.isEmpty()) {
            integers.remove(0);
        }
        return integers;
    }

    @GetMapping("deleteAll")
    public List<Integer> deleteAllNumber() {
        if (!integers.isEmpty()) {
            integers.removeAll(integers);
        }
        return integers;
    }

    @GetMapping("addAll")
    public List<Integer> addAllNumbers() {
        Integer sum = 0;
        for (Integer integer : integers)
            sum += integer;
        return Collections.singletonList(sum);
    }

    @GetMapping("average")
    public List<Integer> averageNumbers() {
        Integer sum = 0;
        for (Integer integer : integers)
            sum += integer;
        return Collections.singletonList(sum / integers.size());
    }

    @GetMapping("howMany")
    public List<Integer> getCount() {
        return Collections.singletonList(integers.size());
    }

    @GetMapping("addOne")//suurendab listis olevaid numbreid ühe võrra
    public List<List<Integer>> addOne() {
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, integers.get(i) + 1);
        }
        return Collections.singletonList(integers);
    }

    @GetMapping("addRandom")
    public List<Integer> addRandomNumber(@RequestParam Integer i) { //addRandom?i= Random number
        for (int j = 0; j < integers.size(); j++) {
            integers.set(j, integers.get(j) + i);
        }
        return integers;
    }

    @GetMapping("divide10")//suurendab listis olevaid numbreid ühe võrra
    public List<List<Integer>> divideBy10() {
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, (int) (integers.get(i) / 10.0));
        }
        return Collections.singletonList(integers);
    }

    @GetMapping("randomDivision")
    public List<Integer> randomDivision(@RequestParam Integer i) { //addRandomDivision?i= Random number
        for (int j = 0; j < integers.size(); j++) {
            integers.set(j, integers.get(j) / i);
        }
        return integers;


    }
}