package event;

import java.awt.Rectangle;

public class Event {
	private String name;
	private int locationMap;
	private int eventCol;
	private int eventRow;
	private Rectangle solidArea;
	
	public Event() {
		name = null;
		locationMap = 0;
		eventCol = 0;
		eventRow = 0;
		solidArea = new Rectangle(20,20,4,4);
	}
	public Event(String name, int eventCol, int eventRow){
		this();
		this.name = name;
		this.eventCol = eventCol;
		this.eventRow = eventRow;
	}
	public Event(String name, int locationMap, int eventCol, int eventRow){
		this(name, eventCol, eventRow);
		this.locationMap = locationMap;
	}
	public Event(String name, int locationMap, int eventCol, int eventRow, Rectangle solidArea){
		this(name, locationMap, eventCol, eventRow);
		this.solidArea = solidArea;
	}
	public String getName() {
		return this.name;
	}
	public int getEventCol() {
		return this.eventCol;
	}
	public int getEventRow() {
		return this.eventRow;
	}
	public Rectangle getSolidArea() {
		return this.solidArea;
	}
	public int getLocationMap() {
		return locationMap;
	}
}