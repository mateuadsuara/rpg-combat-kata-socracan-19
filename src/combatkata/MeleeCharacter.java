package combatkata;

import java.lang.reflect.Modifier;

public class MeleeCharacter {
    private static int MAX_HEALTH = 1000;
    private static int INITIAL_LEVEL = 1;

    private int health;
    private int level;

    public MeleeCharacter() {
        this(INITIAL_LEVEL);
    }

    public MeleeCharacter(int level) {
        health = MAX_HEALTH;
        this.level = level;
    }

    public void damage(MeleeCharacter character, int amount, int range) {
        if (range > 2) {
            return;
        }
        this.damage(character, amount);
    }

    public void damage(MeleeCharacter meleeCharacter, int amount) {
        if (meleeCharacter == this) {
            return;
        }

        meleeCharacter.health -= calculateAttackModifier(meleeCharacter).modify(amount);
    }

    private AttackModifier calculateAttackModifier(MeleeCharacter meleeCharacter) {
        if (this.isOverpoweredBy(meleeCharacter)) {
            return AttackModifier.REDUCED;
        }
        if (meleeCharacter.isOverpoweredBy(this)) {
            return AttackModifier.INCREASED;
        }
        return AttackModifier.NONE;
    }

    private boolean isOverpoweredBy(MeleeCharacter meleeCharacter) {
        return meleeCharacter.level >= this.level + 5;
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
