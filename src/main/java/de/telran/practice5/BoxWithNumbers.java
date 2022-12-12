package de.telran.practice5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BoxWithNumbers<TYPE extends Number> {
    private List<TYPE> numbers;

    public List<TYPE> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<TYPE> numbers) {
        this.numbers = numbers;
    }

    public BoxWithNumbers(List<TYPE> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public BoxWithNumbers(TYPE... numbers) {
        this.numbers = new ArrayList<>(Arrays.asList(numbers));
    }

    public boolean equalsAvg(BoxWithNumbers<?> another) {
//        return avg() == another.avg();
        return Math.abs(avg() - another.avg()) < 0.000001;
    }

    public double avg() {
       return sum() / numbers.size();
    }

    public double sum() {
        double sum = 0;
        for (TYPE number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }

}
