package entity;
import screen.GamePanel;

public class Entity {
	
	EntityGraphic entityGra;
    //CHARACTER ATTRIBUTES
    public String name;
    protected int speed; // number of pixels the entity will move after each hit key
    protected int maxHp;
    public int hp;
	public int ammo;
	public int mana;
	protected int maxMana;
	protected int level;
	protected int strength;
	protected int dexteriry;
	protected int attack;
	protected int defense;
	protected int exp;
	protected int nextLevelExp;
	public int coin;

	public Entity() {}
	public void damageMonster(int i, int attack, GamePanel gp, EntityGraphic entityGra) {}
	 
	public void damagePlayer(int attack, GamePanel gp) {}
	
	public int getMaxHp() { return maxHp;}
	public int getMaxMana() { return maxMana;}
	public int getSpeed() {return speed;}
	public int getLevel() { return level;}
	public int getStrength() {return strength;}
	public int getDexteriry() {return dexteriry;}
	public int getAttack() {return attack;}
	public int getDefense() {return defense;}
	public int getExp() { return exp;}
	public int getNextLevelExp() { return nextLevelExp;}
	
}
