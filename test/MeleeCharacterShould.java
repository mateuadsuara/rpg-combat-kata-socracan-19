import combatkata.Faction;
import combatkata.MeleeCharacter;
import combatkata.RangedCharacter;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MeleeCharacterShould {

    private static final int A_VALID_MELEE_RANGE = 1;

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
    public void canDealDamage_when_they_are_not_sharing_factions() {
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
    public void enemies_can_damage_each_other() {
        MeleeCharacter character1 = newCharacterInFaction(new Faction());
        MeleeCharacter character2 = newCharacterInFaction(new Faction());

        character1.damage(character2, 10, A_VALID_MELEE_RANGE);

        assertEquals(990, character2.getHealth());
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
        {
            RangedCharacter rangedCharacter = new RangedCharacter();
            MeleeCharacter meleeCharacter2 = newCharacter();

            rangedCharacter.damage(meleeCharacter2, 1, 15);

            assertEquals(999, meleeCharacter2.getHealth());
        }
        {
            RangedCharacter rangedCharacter = new RangedCharacter();
            MeleeCharacter meleeCharacter2 = newCharacter();

            rangedCharacter.damage(meleeCharacter2, 1, 20);

            assertEquals(999, meleeCharacter2.getHealth());
        }
    }

    @Test
    public void a_ranged_player_cannot_deal_damage_past_melee_rangeAndRangedRange() {
        RangedCharacter rangedCharacter = new RangedCharacter();
        MeleeCharacter meleeCharacter2 = newCharacter();

        rangedCharacter.damage(meleeCharacter2, 1, 21);

        assertEquals(newCharacter().getHealth(), meleeCharacter2.getHealth());
    }

    @Test
    public void allies_cannot_damage_each_other() {
        Faction faction = new Faction();
        MeleeCharacter character1 = newCharacterInFaction(faction);
        MeleeCharacter character2 = newCharacterInFaction(faction);

        character1.damage(character2, 10, A_VALID_MELEE_RANGE);

        assertEquals(1000, character2.getHealth());
    }

    @Test
    public void allies_can_heal_each_other() {
        Faction faction = new Faction();
        MeleeCharacter character1 = newCharacterInFaction(faction);
        MeleeCharacter character2 = newCharacterInFaction(faction, withHealth(990));

        character1.heal(character2, 10);

        assertEquals(1000, character2.getHealth());
    }


    @Test
    public void enemies_cannot_heal_each_other() {
        MeleeCharacter character1 = newCharacterInFaction(new Faction());
        MeleeCharacter character2 = newCharacterInFaction(new Faction(), withHealth(990));

        character1.heal(character2, 10);

        assertEquals(990, character2.getHealth());
    }

    private int withHealth(int desiredHealth) {
        return desiredHealth;
    }

    private MeleeCharacter newCharacterInFaction(Faction faction, int health) {
        MeleeCharacter character = newCharacterInFaction(faction);
        newCharacter().damage(character, 1000 - health);
        return character;
    }


    private MeleeCharacter newCharacterInFaction(Faction faction) {
        MeleeCharacter character = newCharacter();
        character.join(faction);
        return character;
    }

}
