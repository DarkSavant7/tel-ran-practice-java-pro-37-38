package de.telran.collections_example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiConsumer;

public class Test {

    public static void main(String[] args) {
        Map<League, List<Player>> map = new HashMap<>();

//        int i = 10;
//        var i = 10;
        Stack<String> strings = new Stack<>();
        int first = 0b00110011;
        int second = first << 2; //11001100
        int third = first >> 2; //001100

        System.out.println(first);
        System.out.println(second);
        System.out.println(third);

        map.put(League.LEAGUE1, new ArrayList<>(List.of(
                new Player("Vasia", 4),
                new Player("Vasia1", 6),
                new Player("Vasia2", 2),
                new Player("Vasia3", 14),
                new Player("Vasia4", 5)
        )));

        map.put(League.LEAGUE2, new ArrayList<>(List.of(
                new Player("Petya", 4),
                new Player("Petya1", 6),
                new Player("Petya2", 2),
                new Player("Petya3", 14),
                new Player("Petya4", 5)
        )));

        map.put(League.LEAGUE3, new ArrayList<>(List.of(
                new Player("Vova", 4),
                new Player("Vova1", 6),
                new Player("Vova2", 2),
                new Player("Vova3", 14),
                new Player("Vova4", 5)
        )));

        Map<League, List<Player>> leaders = getLeaders(map);

        System.out.println("ALL:");
        System.out.println(map);
        System.out.println();
        System.out.println("Leaders:");
        System.out.println(leaders);


        System.out.println();
        System.out.println("Removing");

        removeLeadersFromAll(map, leaders);
        System.out.println(map);
    }

    private static Map<League, List<Player>> getLeaders(Map<League, List<Player>> players) {
        Map<League, List<Player>> result = new HashMap<>();

        players.forEach(new BiConsumer<League, List<Player>>() {
            @Override
            public void accept(League league, List<Player> players) {
                players.sort(new Comparator<Player>() {
                    @Override
                    public int compare(Player p1, Player p2) {
                        return p2.getScore() - p1.getScore();
                    }
                });
                int thirdPlayerIndex = players.size() > 2 ? 2 : players.size() - 1;
                result.put(league, new ArrayList<>(players.subList(0, thirdPlayerIndex)));
            }
        });
        return result;
    }

    private static void removePlayerFromMap(Map<League, List<Player>> map,
                                            League league,
                                            Player player) {
        List<Player> players = map.get(league);
        if (players == null) {
            return;
        }

        players.remove(player);
    }

    private static void removeLeadersFromAll(Map<League, List<Player>> all,
                                             Map<League, List<Player>> leaders) {

        for (Map.Entry<League, List<Player>> entry : leaders.entrySet()) {
            List<Player> allPlayers = all.get(entry.getKey());
            allPlayers.removeAll(entry.getValue());
        }
//        leaders.forEach(new BiConsumer<League, List<Player>>() {
//            @Override
//            public void accept(League league, List<Player> players) {
//                List<Player> allPlayers = all.get(league);
//                allPlayers.removeAll(players);
//            }
//        });

//        leaders.forEach((league, players) -> {
//                    List<Player> allPlayers = all.get(league);
//                    allPlayers.removeLeadersFromAll(players);
//                }
//        );
    }
}
