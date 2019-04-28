package combatkata;

enum AttackModifier {
    NONE(1), INCREASED(1.5), REDUCED(0.5);

    private final double value;

    AttackModifier(double value) {
        this.value = value;
    }

    double modify(int damage) {
        return damage * this.value;
    }
}
