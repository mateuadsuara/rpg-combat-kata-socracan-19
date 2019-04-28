package combatkata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Factions {
    private final List<Faction> factions;

    public Factions(Faction... factions) {
        this.factions = new ArrayList<>();
        this.factions.addAll(Arrays.asList(factions));
    }

    public boolean atLeastOneIsSharedWith(Factions factions) {
        ArrayList<Faction> copy = new ArrayList<>(this.factions);
        copy.retainAll(factions.factions);
        return !copy.isEmpty();
    }

    public void add(Faction faction) {
        factions.add(faction);
    }
}
