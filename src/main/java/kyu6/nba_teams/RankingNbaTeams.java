package kyu6.nba_teams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RankingNbaTeams {

    private static final String teams = "Los Angeles Clippers,Dallas Mavericks,New York Knicks,Atlanta Hawks,Indiana Pacers,Memphis Grizzlies,"
            + "Los Angeles Lakers,Minnesota Timberwolves,Phoenix Suns,Portland Trail Blazers,New Orleans Pelicans,"
            + "Sacramento Kings,Los Angeles Clippers,Houston Rockets,Denver Nuggets,Cleveland Cavaliers,Milwaukee Bucks,"
            + "Oklahoma City Thunder,San Antonio Spurs,Boston Celtics,Philadelphia 76ers,Brooklyn Nets,Chicago Bulls,"
            + "Detroit Pistons,Utah Jazz,Miami Heat,Charlotte Hornets,Toronto Raptors,Orlando Magic,Washington Wizards,"
            + "Golden State Warriors,Dallas Mavericks";
    private static final String COMMA = ",";
    private static final int THREE = 3;
    private static final String SPACE = " ";

    static String nbaCup(String resultSheet, String toFind) {
        if (resultSheet == null || resultSheet.isEmpty() || toFind == null || toFind.isEmpty()) return "";
        String[] matches = resultSheet.split(COMMA);
        List<String> matchesOfUniqueTeam = Arrays.stream(matches)
                .filter(match -> match.contains(toFind))
                .collect(Collectors.toList());
        return sumStats(matchesOfUniqueTeam, toFind);
    }

    private static Result getResult(String line) {
        String localTeam = null;
        int localResultTeam = 0;
        String visitorTeam = null;
        int visitorResultTeam = 0;
        final String[] teams = RankingNbaTeams.teams.split(COMMA);
        for (String team : teams) {
            if (line.contains(team)) {
                if (localTeam == null) {
                    localTeam = team;
                    String number = line.substring(line.indexOf(localTeam) + localTeam.length() + 1).split(SPACE)[0];
                    localResultTeam = toNumeric(number);
                } else if (!team.equals(localTeam)) {
                    visitorTeam = team;
                    String number = line.substring(line.indexOf(visitorTeam) + visitorTeam.length() + 1).split(SPACE)[0];
                    visitorResultTeam = toNumeric(number);
                    break;
                }
            }
        }
        return new Result(localTeam, localResultTeam, visitorTeam, visitorResultTeam);
    }

    private static String sumStats(List<String> matchesOfUniqueTeam, String toFind) {
        int winners = 0;
        int draws = 0;
        int losses = 0;
        int scored = 0;
        int conceded = 0;
        int points;
        final List<String> teamsArray = Arrays.asList(RankingNbaTeams.teams.split(","));
        if (!teamsArray.contains(toFind)) return toFind + ":This team didn't play!";
        for (String match : matchesOfUniqueTeam) {
            if (match.contains(".")) return "Error(float number):" + match;
            final Result result = getResult(match);
            if (result.winLocal() && result.isLocal(toFind)) { // Soy local y gana local
                winners++;
                scored = scored + result.getLocalResultTeam();
                conceded = conceded + result.getVisitorResultTeam();

            } else if (result.winVisitor() && result.isLocal(toFind)) { // Soy local y pierde local
                losses++;
                scored = scored + result.getLocalResultTeam();
                conceded = conceded + result.getVisitorResultTeam();

            } else if (result.winLocal() && result.isVisitor(toFind)) { // Soy visitante y gana visitante
                losses++;
                scored = scored + result.getVisitorResultTeam();
                conceded = conceded + result.getLocalResultTeam();
            } else if (result.winVisitor() && result.isVisitor(toFind)) { // Soy visitante y pierde visitante
                winners++;
                scored = scored + result.getVisitorResultTeam();
                conceded = conceded + result.getLocalResultTeam();

            } else { // EMPATE
                draws++;
                scored = scored + result.getLocalResultTeam();
                conceded = conceded + result.getLocalResultTeam();

            }
        }
        points = winners * THREE + draws;
        //"Team Name:W=nb of wins;D=nb of draws;L=nb of losses;Scored=nb;Conceded=nb;Points=nb"
        return toFind + ":W=" + winners + ";D=" + draws + ";L=" + losses + ";Scored=" + scored + ";Conceded=" + conceded + ";Points=" + points;
    }

    private static int toNumeric(String str) throws NumberFormatException {
        return Integer.parseInt(str);
    }

    private static class Result {
        private String localTeam;
        private int localResultTeam;
        private String visitorTeam;
        private int visitorResultTeam;

        Result(String localTeam, int localResultTeam, String visitorTeam, int visitorResultTeam) {
            this.localTeam = localTeam;
            this.localResultTeam = localResultTeam;
            this.visitorTeam = visitorTeam;
            this.visitorResultTeam = visitorResultTeam;
        }

        boolean isLocal(String team) {
            return localTeam.equals(team);
        }

        boolean isVisitor(String team) {
            return visitorTeam.equals(team);
        }

        boolean winLocal() {
            return localResultTeam > visitorResultTeam;
        }

        boolean winVisitor() {
            return localResultTeam < visitorResultTeam;
        }

        int getLocalResultTeam() {
            return localResultTeam;
        }

        int getVisitorResultTeam() {
            return visitorResultTeam;
        }
    }
}
