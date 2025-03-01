package model;

import java.util.List;

public class CityStats {
    private long count;
    private double averageAge;
    private List<String> namesOver27;

    public CityStats(long count, double averageAge, List<String> namesOver27) {
        this.count = count;
        this.averageAge = averageAge;
        this.namesOver27 = namesOver27;
    }

    @Override
    public String toString() {
        return "[count=" + count + ", avg=" + averageAge + ", names=" + namesOver27 + "]";
    }
}