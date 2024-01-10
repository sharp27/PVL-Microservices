package de.mse.boerse;

import java.util.List;

public class MarketStackResponse {
    private Pagination pagination;
    private List<StockData> data;
    
    public MarketStackResponse(Pagination pagination, List<StockData> data) {
    	this.pagination = pagination;
    	this.data = data;
    }
    
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public List<StockData> getData() {
		return data;
	}
	public void setData(List<StockData> data) {
		this.data = data;
	}
}

