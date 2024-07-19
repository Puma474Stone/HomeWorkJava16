package ru.netology.statistic;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private List<Player> players = new ArrayList<>();

    public void register(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public Player findByName(String name) {
        List<Player> foundPlayers = players.stream()
                .filter(player -> player.getName().equals(name))
                .collect(Collectors.toList());

        if (foundPlayers.size() == 0) {
            return null;
        } else if (foundPlayers.size() > 1) {
            throw new DuplicatePlayerException("Multiple players with the name: " + name);
        } else {
            return foundPlayers.get(0);
        }
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if (player1 == null || player2 == null) {
            throw new NotRegisteredException("One or both players are not registered.");
        }

        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }
}


