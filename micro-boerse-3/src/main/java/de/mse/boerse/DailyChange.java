package de.mse.boerse;

import java.time.LocalDate;

public class DailyChange {
    private LocalDate date;
    private Double percentageChange;
    private Double close;

    public DailyChange(LocalDate date, Double percentageChange, Double close) {
        this.date = date;
        this.percentageChange = percentageChange;
        this.setClose(close);
    }

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getPercentageChange() {
		return percentageChange;
	}

	public void setPercentageChange(Double percentageChange) {
		this.percentageChange = percentageChange;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	
}