package combatkata;

public class MeleeCharacter {
    private static int MAX_HEALTH = 1000;
    private static int INITIAL_LEVEL = 1;

    private int health;
    private int level;
    private Factions factions = new Factions();

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
        if (this.isAllyOf(character)) {
            return;
        }
        this.damage(character, amount);
    }

    public void damage(Thing thing, int amount) {
        thing.receiveDamage(amount);
    }

    private boolean isAllyOf(MeleeCharacter character) {
        return factions.atLeastOneIsSharedWith(character.factions);
    }

    /**
     * Damage a character, as long as you're always within the range
     *
     * @param meleeCharacter
     * @param amount
     */
    @Deprecated
    public void damage(MeleeCharacter meleeCharacter, int amount) {
        if (meleeCharacter == this) {
            return;
        }

        meleeCharacter.receiveDamage((int)calculateAttackModifier(meleeCharacter).modify(amount));
    }

    public void receiveDamage(int amount) {
        this.health -= amount;
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

    public void heal(MeleeCharacter character, int amount) {
        if (!isAllyOf(character)) {
            return;
        }

        character.heal(amount);
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

    public void join(Faction faction) {
        this.factions.add(faction);
    }

}
