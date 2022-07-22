package event;


public class EventGate extends Event {
	private int toMap;
	private int toWorldCol;
	private int toWorldRow;
	public EventGate(int locationMap, int worldCol, int worldRow, int toMap, int toWorldCol, int toWorldRow) {
		super("Gate",locationMap, worldCol, worldRow);
		this.toMap = toMap;
		this.toWorldCol = toWorldCol;
		this.toWorldRow = toWorldRow;
	}
	public int getToMap() {
		return this.toMap;
	}
	
	public int getToWorldCol() {
		return this.toWorldCol;
	}
	public int getToWorldRow() {
		return this.toWorldRow;
	}
	
}