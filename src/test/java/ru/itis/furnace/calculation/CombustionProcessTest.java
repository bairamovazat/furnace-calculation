package ru.itis.furnace.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itis.furnace.calculation.formula.CombustionProcess;

public class CombustionProcessTest {

    private final Double carbonContent = 75.0;
    private final Double hydrogenContent = 25.0;
    private final Double sulfurContent = 0.0;
    private final Double oxygenContent = 0.0;
    private final Double wetContent = 0.0;
    private final Double waterSteam = 0.0;


    @Test
    public void testProductNetCalorificValue() {
        Double result = CombustionProcess.productNetCalorificValue(carbonContent, hydrogenContent,
                sulfurContent, oxygenContent, wetContent);
        Assertions.assertEquals(result, 51175, 0.001);
    }

    @Test
    public void testAirAmountToFuel() {
        Double result = CombustionProcess.airAmountToFuel(carbonContent, hydrogenContent,
                sulfurContent, oxygenContent);
        Assertions.assertEquals(result, 17.252, 0.001);
    }

    @Test
    public void testAirVolumeFromAirAmount() {
        Double airAmount = CombustionProcess.airAmountToFuel(carbonContent, hydrogenContent,
                sulfurContent, oxygenContent);
        Double result = CombustionProcess.airVolumeFromAirAmount(airAmount);
        Assertions.assertEquals(result, 13.342, 0.001);
    }

    @Test
    public void testBurningProductsAmount() {
        Double airAmount = CombustionProcess.airAmountToFuel(carbonContent, hydrogenContent,
                sulfurContent, oxygenContent);
        Double airVolume = CombustionProcess.airVolumeFromAirAmount(airAmount);

        Assertions.assertEquals(
                CombustionProcess.burningProductsAmountCO2(carbonContent),
                0.0625, 0.001
        );

        Assertions.assertEquals(
                CombustionProcess.burningProductsAmountSO2(sulfurContent),
                0.0000, 0.001
        );

        Assertions.assertEquals(
                CombustionProcess.burningProductsAmountH2O(hydrogenContent, waterSteam, wetContent),
                0.125, 0.001
        );

        Assertions.assertEquals(
                CombustionProcess.burningProductsAmountN2(airVolume, 1.1),
                0.517626604, 0.000001
        );

        Assertions.assertEquals(
                CombustionProcess.burningProductsAmountO2(airVolume, 1.1),
                0.012508813, 0.0001
        );
    }
}
