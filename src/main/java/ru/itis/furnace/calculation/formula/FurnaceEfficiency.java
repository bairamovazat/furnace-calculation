package ru.itis.furnace.calculation.formula;

import ru.itis.furnace.calculation.AirComponent;
import ru.itis.furnace.calculation.AirComposition;
import ru.itis.furnace.calculation.MagicConstant;

import java.util.Arrays;


/**
 * Расчёт кпд печи
 */
public final class FurnaceEfficiency {

    /**
     * @param airComposition        состав воздуха
     * @param exitingGasTemperature температура отходящих дымовых газов
     * @param outsideTemperature    температура окружающего воздуха
     * @return потери тепла с отходящими газами
     */
    public static Double exitingHeatLoss(AirComposition airComposition, Double exitingGasTemperature,
                                         Double outsideTemperature) {

        double temperatureDelta = exitingGasTemperature - outsideTemperature;
        Double temperatureAverage = (exitingGasTemperature + outsideTemperature) / 2;

        return Arrays.stream(AirComponent.values())
                .mapToDouble(airComponent ->
                        airComposition.getAirComponentContent(airComponent) * airComponent.getAverageMolarHeatCapacity(temperatureAverage)
                ).sum() * temperatureDelta;
    }

    /**
     * @param productNetCalorificValue низшая теплотворная способность топлива
     * @param airComposition           состав воздуха
     * @param exitingGasTemperature    температура отходящих дымовых газов
     * @param outsideTemperature       температура окружающего воздуха
     * @return КПД печи
     */
    public static Double furnaceEfficiency(AirComposition airComposition, Double productNetCalorificValue,
                                           Double exitingGasTemperature, Double outsideTemperature) {
        Double exitingGasEfficiencyLoss = exitingHeatLoss(airComposition, exitingGasTemperature, outsideTemperature) / productNetCalorificValue;
        return 1.0 - MagicConstant.RADIATION_HEAT_LOSS - exitingGasEfficiencyLoss;
    }
}
