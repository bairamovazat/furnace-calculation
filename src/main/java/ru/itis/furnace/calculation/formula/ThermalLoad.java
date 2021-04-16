package ru.itis.furnace.calculation.formula;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import ru.itis.furnace.calculation.calculator.Calculator;
import ru.itis.furnace.calculation.calculator.Variable;

/**
 * Расчёт полезной тепловой нагрузки
 */
public final class ThermalLoad {

    private ThermalLoad() {

    }

    /**
     * Расчет полезной тепловой нагрузки, кДж/кг
     *
     * @param productConsumption           G_c – расход продукта
     * @param outputMassFractionDistillate е – массовая доля отгона на выходе из печи
     * @param Q_tk                         Q_tk - удельные теплосодержания продукта на выходе из печи
     * @param q_tk                         q_tk -  удельные теплосодержания неиспарившейся жидкости
     * @param q_th                         q_th - удельные теплосодержания продукта на входе в печ
     * @return полезная тепловая нагрузка
     */
    public static Double usefulThermalLoad(Double productConsumption, Double outputMassFractionDistillate,
                                           Double Q_tk, Double q_tk, Double q_th) {
        String expression = "G_c * (Q_tk * e_var + q_tk * (1 - e_var) - q_th)";
        StaticVariableSet<Double> variables = new StaticVariableSet<>();

        variables.set("G_c", productConsumption);
        variables.set("Q_tk", Q_tk);
        variables.set("e_var", outputMassFractionDistillate);
        variables.set("q_tk", q_tk);
        variables.set("q_th", q_th);

        DoubleEvaluator eval = new DoubleEvaluator();

        return eval.evaluate(expression, variables);
    }

    /**
     * @param temp    начальная температура нефти
     * @param density плотность сырья
     * @return удельное теплосодержание для нефти
     */
    public static Double productHeatContent(Double temp, Double density) {
        return Calculator.build("(1.689 * t + 0.0017 * t ^ 2) / (sqrt(0.9948 * p + 0.00915))",
                Variable.build("t", temp),
                Variable.build("p", density)
        ).getValue();
    }

    /**
     * @param temp    начальная температура нефти
     * @param density плотность сырья
     * @return удельное теплосодержание для паров нефти
     */
    public static Double productSteamHeatContent(Double temp, Double density) {

        return Calculator.build("(210 + 0.457 * t + 0.000584 * t ^ 2) * (4.013 - p) - 309",
                Variable.build("t", temp),
                Variable.build("p", density)
        ).getValue();
    }
}
