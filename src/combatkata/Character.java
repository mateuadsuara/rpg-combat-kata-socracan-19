package combatkata;

public class Character {
    static int INITIAL_HEALTH = 1000;
    static int INITIAL_LEVEL = 1;

    private int health;
    private int level;

    public Character() {
        health = INITIAL_HEALTH;
        level = INITIAL_LEVEL;
    }

    public void damages(Character character, int amount) {
        character.health -= amount;

    }

    public void heals(Character character, int amount) {
        if (!character.isAlive()) {
            return;
        }

        character.health += amount;
        if (character.health > 1000) {
            character.health = 1000;
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
