import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

import combatkata.Character;

public class CharacterShould {
    @Test
    public void beBornWithHealth1000() {
        Character character = newCharacter();
        assertEquals(1000, character.getHealth());
    }

    @Test
    public void beBornWithLevel1() {
        Character character = newCharacter();
        assertEquals(1, character.getLevel());
    }

    @Test
    public void beBornAlive() {
        Character character = newCharacter();
        assertEquals(true, character.isAlive());
    }

    @Test
    public void canDealDamage() {
        Character character1 = newCharacter();
        Character character2 = newCharacter();
        character1.damages(character2, 1);
        assertEquals(999, character2.getHealth());
    }

    @Test
    public void diesWhenNoHealthLeft() {
        Character character1 = newCharacter();
        Character character2 = newCharacter();
        character1.damages(character2, 1000);
        assertEquals(false, character2.isAlive());
    }

    @Test
    public void canHeal() {
        Character character1 = newCharacter();
        Character character2 = newCharacter();
        character1.damages(character2, 10);

        character1.heals(character2, 10);

        assertEquals(1000, character2.getHealth());
    }

    private Character newCharacter() {
        return new Character();
    }

    @Test
    public void cannotHealPast1000() {
        Character character1 = newCharacter();
        Character character2 = newCharacter();
        character1.heals(character2, 10);
        assertEquals(1000, character2.getHealth());
    }

    @Test
    public void cannotBeHealedWhenDead() {
        Character character1 = newCharacter();
        Character character2 = newCharacter();
        character1.damages(character2, character2.getHealth());

        character1.heals(character2, 10);

        assertEquals(false, character2.isAlive());

    }
}
