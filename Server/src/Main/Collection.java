package Main;

import MusicBand.MusicBand;
import MusicBand.MusicGenre;
import Utils.DataBaseManager;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

import static Main.FabricOfServer.INTERFACE_MESSAGE;

public class Collection {
    static private CopyOnWriteArraySet<MusicBand> treeSetCollection;
    static private LocalDateTime timeOfInitialization;

    public static Boolean isCollectionInitialized() {
        return treeSetCollection != null;
    }

    public static void initialize() throws IOException, ParseException, IllegalArgumentException {
        if (treeSetCollection == null) {
            treeSetCollection = new CopyOnWriteArraySet<>();
            timeOfInitialization = LocalDateTime.now();
        } else System.out.println(INTERFACE_MESSAGE+"Main.Collection is already created"+ INTERFACE_MESSAGE);
    }

    public static String getInfoCollectionStructure() {
        String info = "";
        if (timeOfInitialization != null) {
            info = "Type of the collection : " + treeSetCollection.getClass().toString() + "\n";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
            info += "Time of initialization : " + timeOfInitialization.format(formatter) + "\n";
            info += "Size of the collection : " + treeSetCollection.size();
        } else info = "Main.Collection hasn't been created";

        return info;
    }

    public static String getInfoCollectionContent() {
        StringBuilder info = new StringBuilder();
        treeSetCollection.forEach(musicBand -> info.append(musicBand.toString()).append("\n"));
        if (info.isEmpty()) return "Collection is empty";
        return info.deleteCharAt(info.length()-1).toString();
    }

    public static void add(MusicBand musicBand) {
        treeSetCollection.add(musicBand);
    }

    public static void removeById(Long id) {
        treeSetCollection.stream()
                .filter(musicBand -> Objects.equals(musicBand.getId(), id))
                .findAny()
                .ifPresent(removedMusicBand -> treeSetCollection.remove(removedMusicBand));
    }

    public static void clear() {
        treeSetCollection.clear();
    }


    public static MusicBand getMinMusicBand() {
        return treeSetCollection.stream()
                .min(Comparator.naturalOrder())
                .orElse(null);
    }

    public static long[] getGreater(MusicBand musicBand) {
        return treeSetCollection.stream()
                .filter(musicBand_i -> musicBand.compareTo(musicBand_i) < 0)
                .mapToLong(musicBand_i -> musicBand_i.getId())
                .toArray();
    }

    public static long[] getLower(MusicBand musicBand) {
        return treeSetCollection.stream()
                .filter(musicBand_i -> musicBand.compareTo(musicBand_i) > 0)
                .mapToLong(musicBand_i -> musicBand_i.getId())
                .toArray();
    }

    public static Map<MusicGenre, Long> groupCountingByGenre() {
        Map<MusicGenre, Long> groups = new HashMap<MusicGenre, Long>() {{
            put(MusicGenre.JAZZ, 0L);
            put(MusicGenre.MATH_ROCK, 0L);
            put(MusicGenre.PSYCHEDELIC_CLOUD_RAP, 0L);
            put(MusicGenre.PUNK_ROCK, 0L);
            put(null, 0L);
        }};

        treeSetCollection.forEach(musicBand -> groups.put(musicBand.getGenre(), groups.get(musicBand.getGenre())+1L));
        return groups;
    }

    public static Long countGreaterThanNumberOfParticipants(Integer numberOfParticipants) {
        return treeSetCollection.stream()
                .filter(musicBand -> musicBand.getNumberOfParticipants() > numberOfParticipants)
                .count();
    }

    public static String fieldAscendingGenre() {
        StringBuilder stringBuilder = new StringBuilder();
        treeSetCollection.forEach(musicBand -> {
            if (musicBand.getGenre() != null) stringBuilder.
                    append("MusicBand№ ").
                    append(musicBand.getId()).
                    append(": ").
                    append(musicBand.getGenre().toString()).
                    append("\n");
        });

        if (stringBuilder.isEmpty()) return "Collection is empty";
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }

}
