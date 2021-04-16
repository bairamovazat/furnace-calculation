package ru.itis.furnace.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itis.furnace.calculation.formula.CombustionProcess;
import ru.itis.furnace.calculation.formula.FurnaceEfficiency;

public class FurnaceEfficiencyTest {


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
    public void testExitingHeatLoss() {
        AirComposition airComposition = getAirComposition();

        Double exitingHeatLoss = FurnaceEfficiency.exitingHeatLoss(airComposition, 155.0 + 180.0, 10.0);
        Assertions.assertEquals(7088.856035, exitingHeatLoss, 0.0001);
    }

    @Test
    public void testFurnaceEfficiency() {
        AirComposition airComposition = getAirComposition();

        Double productNetCalorificValue = CombustionProcess.productNetCalorificValue(carbonContent, hydrogenContent,
                sulfurContent, oxygenContent, wetContent);

        Assertions.assertEquals(productNetCalorificValue, 51175, 0.001);

        Double kpd = FurnaceEfficiency.furnaceEfficiency(airComposition, productNetCalorificValue, 155.0 + 180.0, 10.0);
        Assertions.assertEquals(kpd, 0.811, 0.001);
    }
}
