package ru.itis.furnace.calculation.calculator;

import com.fathzer.soft.javaluator.StaticVariableSet;
import lombok.Builder;

import java.util.Arrays;
import java.util.List;

/**
 * Позволяет расчитывать формулы
 */
@Builder
public class Calculator {

    private final String function;

    private final List<Variable> variableList;

    /**
     * @param function  формула, которую нужно расчитать
     * @param variables значение переменных
     */
    private Calculator(String function, List<Variable> variables) {
        this.function = function;
        this.variableList = variables;
    }

    /**
     * @param function  формула, которую нужно расчитать
     * @param variables значение переменных
     * @return Calculator, который будет считать эту формулу
     */
    public static Calculator build(String function, Variable... variables) {
        return new Calculator(function, Arrays.asList(variables));
    }

    /**
     * @return результат формулы
     */
    public Double getValue() {
        assert function != null;
        assert variableList != null;

        StaticVariableSet<Double> variables = new StaticVariableSet<>();

        variableList.forEach(v -> variables.set(v.getKey(), v.getValue()));

        return new Evaluator().evaluate(function, variables);
    }
}
