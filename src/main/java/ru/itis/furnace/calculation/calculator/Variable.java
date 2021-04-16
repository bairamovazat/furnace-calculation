package ru.itis.furnace.calculation.calculator;

import com.fathzer.soft.javaluator.StaticVariableSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Переменная для формулы
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Variable {

    private String key;
    private Double value;

    public void updateFromCurrent(StaticVariableSet<Double> variableSet) {
        variableSet.set(key, value);
    }

    /**
     * @param key название переменной
     * @param value значение переменной
     * @return переменная для Calculator
     */
    public static Variable build(String key, Double value) {
        return Variable.builder().key(key).value(value).build();
    }
}
