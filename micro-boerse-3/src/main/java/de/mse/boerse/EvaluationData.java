package de.mse.boerse;

import java.util.List;

public class EvaluationData {
    private String symbol;
    private List<DailyChange> changes;

    public EvaluationData(String symbol, List<DailyChange> changes) {
        this.setSymbol(symbol);
        this.setChanges(changes);
    }

	public List<DailyChange> getChanges() {
		return changes;
	}

	public void setChanges(List<DailyChange> changes) {
		this.changes = changes;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}



