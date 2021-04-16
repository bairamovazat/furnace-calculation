package ru.itis.furnace.calculation;

import java.util.Arrays;
import java.util.HashMap;

public class AirComposition {
    /**
     * Содержание компонента в воздухе
     */
    public HashMap<AirComponent, Double> airComponentContent = new HashMap<>();

    public AirComposition() {
        Arrays.stream(AirComponent.values()).forEach(airComponent -> airComponentContent.put(airComponent, 0.0));
    }

    public void setAirComponentContent(AirComponent airComponent, Double content) {
        airComponentContent.put(airComponent, content);
    }

    public Double getAirComponentContent(AirComponent airComponent) {
        return airComponentContent.get(airComponent);
    }
}
