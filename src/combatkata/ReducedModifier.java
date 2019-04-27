package combatkata;

public class ReducedModifier implements AttackModifier {
    @Override
    public int modify(int damage) {
        return damage / 2;
    }
}
