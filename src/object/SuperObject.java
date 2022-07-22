package object;

public class SuperObject {
    
    protected String name;
    protected int value;
    protected int defenseValue;
    protected int speed;
    protected int attackValue; // 's weapon
    protected String description;
    
    //TYPE
    protected int type; // 0 = player, 1 = npc, 2 = monster, for collision between player and monster

    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
	
    
    public SuperObject() {
    }
    public void use() {}

    public String getName() { return name;}
	public int getDefenseValue() { return defenseValue;}
	public int getSpeed() {return speed;}
	public int getAttackValue() {return attackValue;}
	public String getDescription() { return description;}
	public int getType() {return type;}
}
	    
