package ru.itis.furnace.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itis.furnace.calculation.formula.ThermalLoad;

public class ThermalLoadTest {

    @Test
    public void testUsefulThermalLoad() {
        Double G_c = 41.7;
        Double e = 0.5159;
        Double Q_tk = 1164.0;
        Double q_tk = 907.0;
        Double q_th = 359.0;
        Double result = ThermalLoad.usefulThermalLoad(G_c, e, Q_tk, q_tk, q_th);
        Assertions.assertEquals(result, 28380.448, 0.001 );
    }

    @Test
    public void testProductHeatContent() {
        Double temp = 375.0;
        Double density = 0.921;
        Double result = ThermalLoad.productHeatContent(temp, density);
        Assertions.assertEquals(result, 906.9404, 0.001 );
    }

    @Test
    public void testProductSteamHeatContent() {
        Double temp = 375.0;
        Double density = 0.834;
        Double result = ThermalLoad.productSteamHeatContent(temp, density);
        Assertions.assertEquals(result, 1164.4665, 0.001 );
    }

}
