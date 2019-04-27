import combatkata.MeleeCharacter;
import combatkata.RangedCharacter;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MeleeCharacterShould {

    @Test
    public void beBornWithHealth1000() {
        MeleeCharacter meleeCharacter = newCharacter();
        assertEquals(1000, meleeCharacter.getHealth());
    }

    @Test
    public void beBornWithLevel1() {
        MeleeCharacter meleeCharacter = newCharacter();
        assertEquals(1, meleeCharacter.getLevel());
    }

    @Test
    public void beBornAlive() {
        MeleeCharacter meleeCharacter = newCharacter();
        assertEquals(true, meleeCharacter.isAlive());
    }

    @Test
    public void canDealDamage() {
        MeleeCharacter meleeCharacter1 = newCharacter();
        MeleeCharacter meleeCharacter2 = newCharacter();
        meleeCharacter1.damage(meleeCharacter2, 1);
        assertEquals(999, meleeCharacter2.getHealth());


        MeleeCharacter powerfulMeleeCharacter = new MeleeCharacter(5);
        MeleeCharacter weakMeleeCharacter = new MeleeCharacter(1);

        weakMeleeCharacter.damage(powerfulMeleeCharacter, 10);

        assertEquals(1000 - 10, powerfulMeleeCharacter.getHealth());
    }

    @Test
    public void diesWhenNoHealthLeft() {
        MeleeCharacter meleeCharacter1 = newCharacter();
        MeleeCharacter meleeCharacter2 = newCharacter();
        meleeCharacter1.damage(meleeCharacter2, 1000);
        assertEquals(false, meleeCharacter2.isAlive());
    }

    private MeleeCharacter newCharacter() {
        return new MeleeCharacter();
    }

    @Test
    public void cannotHealPast1000() {
        MeleeCharacter meleeCharacter1 = newCharacter();
        MeleeCharacter meleeCharacter2 = newCharacter();

        meleeCharacter1.heal(10);

        assertEquals(1000, meleeCharacter2.getHealth());
    }

    @Test
    public void cannotBeHealedWhenDead() {
        MeleeCharacter meleeCharacter1 = newCharacter();
        MeleeCharacter meleeCharacter2 = newCharacter();
        meleeCharacter2.damage(meleeCharacter1, meleeCharacter1.getHealth());

        meleeCharacter1.heal(10);

        assertEquals(false, meleeCharacter1.isAlive());
    }

    @Test
    public void thePlayerCannotDamageThemselves() {
        MeleeCharacter meleeCharacter = newCharacter();

        meleeCharacter.damage(meleeCharacter, meleeCharacter.getHealth());

        assertEquals(1000, meleeCharacter.getHealth());
    }

    @Test
    public void canOnlyHealThemselves() {
        MeleeCharacter meleeCharacter1 = newCharacter();
        MeleeCharacter meleeCharacter2 = newCharacter();
        meleeCharacter2.damage(meleeCharacter1, 10);

        meleeCharacter1.heal(10);

        assertEquals(1000, meleeCharacter1.getHealth());
    }

    @Test
    public void overpoweredCharacterReceivesHalfDamage() {
        MeleeCharacter powerfulMeleeCharacter = new MeleeCharacter(6);
        MeleeCharacter weakMeleeCharacter = new MeleeCharacter(1);

        weakMeleeCharacter.damage(powerfulMeleeCharacter, 10);

        assertEquals(1000 - 10 / 2, powerfulMeleeCharacter.getHealth());
    }

    @Test
    public void underpoweredCharacterReceives50PercentMoreDamage() {
        MeleeCharacter powerfulMeleeCharacter = new MeleeCharacter(6);
        MeleeCharacter weakMeleeCharacter = new MeleeCharacter(1);

        powerfulMeleeCharacter.damage(weakMeleeCharacter, 10);

        assertEquals((int) (1000 - 10 * 1.5), weakMeleeCharacter.getHealth());

    }

    @Test
    public void a_melee_character_cannot_attack_a_character_out_of_its_range() {
        MeleeCharacter meleeCharacter = new MeleeCharacter();
        MeleeCharacter attacked = new MeleeCharacter();

        meleeCharacter.damage(attacked, 10, 3);

        assertEquals(1000, attacked.getHealth());
    }

    @Test
    public void canDealDamage_when_inside_the_range() {
        MeleeCharacter meleeCharacter1 = newCharacter();
        MeleeCharacter meleeCharacter2 = newCharacter();

        meleeCharacter1.damage(meleeCharacter2, 1, 2);

        assertEquals(999, meleeCharacter2.getHealth());
    }

    @Test
    public void a_ranged_player_can_deal_damage_past_melee_range() {
        RangedCharacter rangedCharacter = new RangedCharacter();
        MeleeCharacter meleeCharacter2 = newCharacter();

        rangedCharacter.damage(meleeCharacter2, 1, 15);

        assertEquals(999, meleeCharacter2.getHealth());
    }

    @Test
    public void a_ranged_player_cannot_deal_damage_past_melee_rangeAndRangedRange() {
        RangedCharacter rangedCharacter = new RangedCharacter();
        MeleeCharacter meleeCharacter2 = newCharacter();

        rangedCharacter.damage(meleeCharacter2, 1, 21);

        assertEquals(newCharacter().getHealth(), meleeCharacter2.getHealth());
    }

}
