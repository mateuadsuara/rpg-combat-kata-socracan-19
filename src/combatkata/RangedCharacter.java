package combatkata;

public class RangedCharacter extends MeleeCharacter {
    @Override
    public void damage(MeleeCharacter character, int amount, int range) {
        if (range > 20) {
            return;
        }
        this.damage(character, amount);
    }
}
