package ru.itis.furnace.calculation.calculator;

import com.fathzer.soft.javaluator.StaticVariableSet;
import lombok.Builder;

import java.util.Arrays;
import java.util.List;

@Builder
public class Calculator {

    private final String function;

    private final List<Variable> variableList;

    public static Calculator build(String function, Variable... variables) {
        return new Calculator(function, Arrays.asList(variables));
    }

    private Calculator(String function, List<Variable> variables) {
        this.function = function;
        this.variableList = variables;
    }

    public Double getValue() {
        assert function != null;
        assert variableList != null;

        StaticVariableSet<Double> variables = new StaticVariableSet<>();

        variableList.forEach(v -> variables.set(v.getKey(), v.getValue()));

        return new Evaluator().evaluate(function, variables);
    }
}
