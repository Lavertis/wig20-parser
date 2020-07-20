package com.lavertis;

enum Field {
    symbol,
    name,
    lastRate,
    changePercentage,
    changeNumeral,
    volume,
    changeDate
}

class Stock {
    private final String symbol;
    private final String name;
    private final float lastRate;
    private final String changePercentage;
    private final float changeNumeral;
    private final String volume;
    private final String date;

    Stock(String symbol, String name, float lastRate, String changePercentage, float changeNumeral, String volume, String date) {
        this.symbol = symbol;
        this.name = name;
        this.lastRate = lastRate;
        this.changePercentage = changePercentage;
        this.changeNumeral = changeNumeral;
        this.volume = volume;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol = " + String.format("%-3s", symbol) +
                "\t name = " + String.format("%-10s", name) +
                "\t lastRate = " + String.format("%-7s", lastRate) +
                "\t changePercentage = " + String.format("%-8s", changePercentage) +
                "\t changeNumeral = " + String.format("%-7s", changeNumeral) +
                "\t volume = " + String.format("%-6s", volume) +
                "\t date = " + date +
                '}';
    }
}
