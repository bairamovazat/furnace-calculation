package ru.itis.furnace.calculation;

/**
 * Класс, который содержит
 */
public enum AirComponent {

    O2(37.2, 17.3, -3.57),
    CO2(35.0, 1.8, 1.40),
    H2O(28.4, 3.4, -0.36),
    N2(29.1, 4.8, -0.81),
    SO2(41.2, 11.9, -2.2);

    private Double a;
    private Double b;
    private Double c;

    AirComponent(Double a, Double b, Double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Double getAverageMolarHeatCapacity(Double t) {
        return a + b * t / Math.pow(10, 3) + c * Math.pow(t, 2) / Math.pow(10, 6);
    }

}
