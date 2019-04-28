package combatkata;

public class Thing {
    private int health;

    public Thing(int initialHealth) {
        this.health = initialHealth;
    }

    public int getHealth() {
        return health;
    }

    public void receiveDamage(int amount) {
        if (isDestroyed()) return;
        health -= amount;
    }

    public boolean isDestroyed() {
        return health <= 0;
    }
}
