package combatkata;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactionsShould {

    @Test
    public void when_they_have_one_in_common_the_factions_are_shared(){
        Faction faction = new Faction();
        Factions factions1 = new Factions(faction);
        Factions factions2 = new Factions(faction);

        assertEquals(true, factions1.atLeastOneIsSharedWith(factions2));
    }

}