package ru.itis.furnace.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itis.furnace.calculation.formula.CombustionProcess;
import ru.itis.furnace.calculation.formula.FurnaceEfficiency;
import ru.itis.furnace.calculation.formula.ThermalLoad;

public class DemonstrationTest {

    private final Double carbonContent = 75.0;
    private final Double hydrogenContent = 25.0;
    private final Double sulfurContent = 0.0;
    private final Double oxygenContent = 0.0;
    private final Double wetContent = 0.0;


    public AirComposition getAirComposition() {
        AirComposition airComposition = new AirComposition();

        airComposition.setAirComponentContent(AirComponent.O2, 0.012508813);
        airComposition.setAirComponentContent(AirComponent.CO2, 0.0625);
        airComposition.setAirComponentContent(AirComponent.H2O, 0.125);
        airComposition.setAirComponentContent(AirComponent.N2, 0.517626604);
        airComposition.setAirComponentContent(AirComponent.SO2, 0.0);

        return airComposition;
    }

    @Test
    public void testDemonstration() {
        Double G_c = 41.7;
        Double e = 0.5159;
        Double Q_tk = 1164.0;
        Double q_tk = 907.0;
        Double q_th = 359.0;
        Double usefulThermalLoad = ThermalLoad.usefulThermalLoad(G_c, e, Q_tk, q_tk, q_th);
        System.out.println("Полезная тепловая нагрузка: " + usefulThermalLoad);
        Assertions.assertEquals(usefulThermalLoad, 28380.448, 0.001);


        Double temp = 375.0;
        Double density = 0.921;
        Double productHeatContent = ThermalLoad.productHeatContent(temp, density);
        Assertions.assertEquals(productHeatContent, 906.9404, 0.001);
        System.out.println("Удельное теплосодержание для нефти: " + productHeatContent);


        AirComposition airComposition = getAirComposition();

        Double exitingHeatLoss = FurnaceEfficiency.exitingHeatLoss(airComposition, 155.0 + 180.0, 10.0);
        System.out.println("Потери тепла с отходящими газами: " + exitingHeatLoss);
        Assertions.assertEquals(7088.856035, exitingHeatLoss, 0.0001);


        Double productNetCalorificValue = CombustionProcess.productNetCalorificValue(carbonContent, hydrogenContent,
                sulfurContent, oxygenContent, wetContent);
        System.out.println("Низшая теплотворная способность топлива: " + productNetCalorificValue);
        Assertions.assertEquals(productNetCalorificValue, 51175, 0.001);

        Double kpd = FurnaceEfficiency.furnaceEfficiency(airComposition, productNetCalorificValue, 155.0 + 180.0, 10.0);
        Assertions.assertEquals(kpd, 0.811, 0.001);
        System.out.println("\nКПД печи: " + kpd);

    }
}
