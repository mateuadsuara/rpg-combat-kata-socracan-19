package combatkata;

public class NoModifier implements AttackModifier{
    public int modify(int damage){
        return damage;
    }
}
