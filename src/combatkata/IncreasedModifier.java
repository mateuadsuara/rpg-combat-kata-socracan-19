package combatkata;

public class IncreasedModifier implements AttackModifier{

    @Override
    public int modify(int damage) {
        return (int) (damage * 1.5);
    }
}
