package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Command;

public class SelectTable implements Command {

	private Object[][] detailsOfTable;
	private Object[] namesOfCols;
	private ArrayList<String> selectedCols = new ArrayList<>();
	private ArrayList<Object> col = new ArrayList<>();
	private Object[][] selectedTable;
	public SelectTable(Object[][] detailsOfTable, Object[] namesOfCols, ArrayList<String> selectedCols) {

		this.detailsOfTable = detailsOfTable;
		this.namesOfCols = namesOfCols;
		this.selectedCols = selectedCols;






	}

	@Override
	public Object execute() {

			for (int j = 0; j < this.namesOfCols.length; j++) {
				if (this.selectedCols.get(0).equals(this.namesOfCols[j])) {
					for (int x = 0; x < this.detailsOfTable.length; x++) {
						this.col.add(this.detailsOfTable[x][j]);
						if (x == this.detailsOfTable.length - 1) {
							selectedTable = new Object[this.col.size()][this.selectedCols.size()];
						}

					}
				}
			}

		for (int i = 0; i < this.selectedCols.size(); i++) {
			for (int j = 0; j < this.namesOfCols.length; j++) {
				if (this.selectedCols.get(i).equals(this.namesOfCols[j])) {
					for (int x = 0; x < this.detailsOfTable.length; x++) {
						this.selectedTable[x][i] = this.detailsOfTable[x][j];
						}

					}
				}
			}

		return this.selectedTable;
	}

}
