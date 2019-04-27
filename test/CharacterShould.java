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
        character1.damage(character2, 1);
        assertEquals(999, character2.getHealth());



        Character powerfulCharacter = new Character(5);
        Character weakCharacter = new Character(1);

        weakCharacter.damage(powerfulCharacter, 10);

        assertEquals(1000 - 10, powerfulCharacter.getHealth());
    }

    @Test
    public void diesWhenNoHealthLeft() {
        Character character1 = newCharacter();
        Character character2 = newCharacter();
        character1.damage(character2, 1000);
        assertEquals(false, character2.isAlive());
    }
    private Character newCharacter() {
        return new Character();
    }

    @Test
    public void cannotHealPast1000() {
        Character character1 = newCharacter();
        Character character2 = newCharacter();

        character1.heal(10);

        assertEquals(1000, character2.getHealth());
    }

    @Test
    public void cannotBeHealedWhenDead() {
        Character character1 = newCharacter();
        Character character2 = newCharacter();
        character2.damage(character1, character1.getHealth());

        character1.heal(10);

        assertEquals(false, character1.isAlive());
    }

    @Test
    public void thePlayerCannotDamageThemselves() {
        Character character = newCharacter();

        character.damage(character, character.getHealth());

        assertEquals(1000, character.getHealth());
    }

    @Test
    public void canOnlyHealThemselves() {
        Character character1 = newCharacter();
        Character character2 = newCharacter();
        character2.damage(character1, 10);

        character1.heal(10);

        assertEquals(1000, character1.getHealth());
    }

    @Test
    public void overpoweredCharacterReceivesHalfDamage() {
        Character powerfulCharacter = new Character(6);
        Character weakCharacter = new Character(1);

        weakCharacter.damage(powerfulCharacter, 10);

        assertEquals(1000 - 10/2, powerfulCharacter.getHealth());
    }

    @Test
    public void underpoweredCharacterReceives50PercentMoreDamage() {
        Character powerfulCharacter = new Character(6);
        Character weakCharacter = new Character(1);

        powerfulCharacter.damage(weakCharacter, 10);

        assertEquals((int)(1000 - 10*1.5), weakCharacter.getHealth());

    }

}
