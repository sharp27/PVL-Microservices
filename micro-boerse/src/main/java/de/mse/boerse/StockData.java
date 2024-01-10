package de.mse.boerse;

public class StockData {
    private String date;
    private double close;
    private String symbol;
    
    public StockData(String date, double close, String symbol) {
    	this.date = date;
    	this.close = close;
    	this.symbol = symbol;
    }
    
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
