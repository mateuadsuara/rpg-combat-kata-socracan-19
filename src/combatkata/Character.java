package combatkata;

public class Character {
    private static int MAX_HEALTH = 1000;
    private static int INITIAL_LEVEL = 1;

    private int health;
    private int level;

    public Character() {
        this(INITIAL_LEVEL);
    }

    public Character(int level) {
        health = MAX_HEALTH;
        this.level = level;
    }

    public void damage(Character character, int amount) {
        if (character == this) {
            return;
        }

        character.health -= calculateDamage(character, amount);
    }

    private int calculateDamage(Character character, int amount) {
        if (this.isOverpoweredBy(character)) {
            return reducedDamage(amount);
        }
        if (character.isOverpoweredBy(this)){
            return increasedDamage(amount);
        }
        return amount;
    }

    private int increasedDamage(int damage) {
        return (int) (damage * 1.5);
    }

    private int reducedDamage(int damage) {
        return damage / 2;
    }

    private boolean isOverpoweredBy(Character character) {
        return character.level >= this.level + 5;
    }

    public void heal(int amount) {
        if (!this.isAlive()) {
            return;
        }

        this.health += amount;
        if (this.health > MAX_HEALTH) {
            this.health = MAX_HEALTH;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
