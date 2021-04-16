package ru.itis.furnace.calculation.formula;

import ru.itis.furnace.calculation.calculator.Calculator;
import ru.itis.furnace.calculation.calculator.Variable;
import ru.itis.furnace.calculation.MagicConstant;

/**
 * Расчёт процессов горения
 */
public final class CombustionProcess {

    private CombustionProcess() {

    }

    /**
     * @param carbonContent   процент содержания углерода в нефти
     * @param hydrogenContent процент содержания водорода в нефти
     * @param sulfurContent   процент содержания серы в нефти
     * @param oxygenContent   процент содержания кислорода в нефти
     * @param wetContent      процент содержания влаги в нефти
     * @return Низшая теплотворная способность топлива
     */
    public static Double productNetCalorificValue(Double carbonContent, Double hydrogenContent, Double sulfurContent,
                                                  Double oxygenContent, Double wetContent) {
        return Calculator.build("339 * C + 1030 * H + 109 * (S - O) - 25 * W",
                Variable.build("C", carbonContent),
                Variable.build("H", hydrogenContent),
                Variable.build("S", sulfurContent),
                Variable.build("O", oxygenContent),
                Variable.build("W", wetContent)
        ).getValue();
    }

    /**
     * @param carbonContent   процент содержания углерода в нефти
     * @param hydrogenContent процент содержания водорода в нефти
     * @param sulfurContent   процент содержания серы в нефти
     * @param oxygenContent   процент содержания кислорода в нефти
     * @return Количество воздуха для сжигания киллограмма топлива
     */
    public static Double airAmountToFuel(Double carbonContent, Double hydrogenContent, Double sulfurContent,
                                         Double oxygenContent) {
        return Calculator.build("(2.67 * C + 8 * H + S - O) / 23.2",
                Variable.build("C", carbonContent),
                Variable.build("H", hydrogenContent),
                Variable.build("S", sulfurContent),
                Variable.build("O", oxygenContent)
        ).getValue();
    }

    /**
     * @param airAmount количество воздуха (кг)
     * @return Объём воздуха (m^3)
     */
    public static Double airVolumeFromAirAmount(Double airAmount) {
        return airAmount / MagicConstant.AIR_DENSITY;
    }

    /**
     * @param carbonContent процент содержания углерода в нефти
     * @return Количество CO2 выделяемого во время горения одного килограмма топлива (кмоль/кг)
     */
    public static Double burningProductsAmountCO2(Double carbonContent) {
        return Calculator.build("0.01 * C / 12",
                Variable.build("C", carbonContent)
        ).getValue();
    }

    /**
     * @param sulfurContent процент содержания серы в нефти
     * @return Количество SO2 выделяемого во время горения одного килограмма топлива (кмоль/кг)
     */
    public static Double burningProductsAmountSO2(Double sulfurContent) {
        return Calculator.build("0.01 * S / 32",
                Variable.build("S", sulfurContent)
        ).getValue();
    }

    /**
     * @param hydrogenContent процент содержания водорода в нефти
     * @param waterSteam      количество форсуночного водяного пара при паровом распыливании жидкого топлива, кг/кг
     * @param wetContent      процент содержания влаги в нефти
     * @return Количество H2O выделяемого во время горения одного килограмма топлива (кмоль/кг)
     */
    public static Double burningProductsAmountH2O(Double hydrogenContent, Double waterSteam, Double wetContent) {
        return Calculator.build("(0.01 * H / 2) + (Zf / 18) + (0.01 * W / 18)",
                Variable.build("H", hydrogenContent),
                Variable.build("Zf", waterSteam),
                Variable.build("W", wetContent)
        ).getValue();
    }

    /**
     * @param airVolume             теоретический объём воздуха
     * @param airSurplusCoefficient коэффициент избытка воздуха
     * @return Количество N2 выделяемого во время горения одного килограмма топлива (кмоль/кг)
     */
    public static Double burningProductsAmountN2(Double airVolume, Double airSurplusCoefficient) {
        return Calculator.build("0.79 * a * Vо / 22.4",
                Variable.build("a", airSurplusCoefficient),
                Variable.build("Vо", airVolume)
        ).getValue();
    }

    /**
     * @param airVolume             теоретический объём воздуха
     * @param airSurplusCoefficient коэффициент избытка воздуха
     * @return Количество O2 выделяемого во время горения одного килограмма топлива (кмоль/кг)
     */
    public static Double burningProductsAmountO2(Double airVolume, Double airSurplusCoefficient) {
        return Calculator.build("0.21 * (a - 1) * Vо / 22.4",
                Variable.build("a", airSurplusCoefficient),
                Variable.build("Vо", airVolume)
        ).getValue();
    }

}
