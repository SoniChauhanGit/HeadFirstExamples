package ch10b;

import java.util.*;

public class CollectionsFactoryMethods {
  public static List<String> createAListOldSchool() {
    List<String> songs = new ArrayList<>();
    songs.add("somersault");
    songs.add("cassidy");
    songs.add("$10");
    return Collections.unmodifiableList(songs);
  }

  public static void createAList() {
    List<String> strings = List.of("somersault", "cassidy", "$10");
  }

  public static void createAListOfSongs() {
    List<SongV2> songs = List.of(new SongV2("somersault", "zero 7", "50"),
                                 new SongV2("cassidy", "grateful dead", "65"),
                                 new SongV2("$10", "hitchhiker", "80"));
  }

  public static void createASet() {
    Set<Book> books = Set.of(new Book("How Cats Work"),
                             new Book("Remix your Body"),
                             new Book("Finding Emo"));
  }

  public static void createAMap() {
    Map<String, Integer> scores = Map.of("Kathy", 42,
                                         "Bert", 343,
                                         "Skyler", 420);
  }

  public static void createAMapOfSameTypes() {
    Map<String, String> favouriteStores = Map.of("Riley", "Supersports",
                                                 "Brooklyn", "Camera World",
                                                 "Jay", "Homecase");
  }

  public static void createAMapOfEntries() {
    Map<String, String> favouriteStores = Map.ofEntries(Map.entry("Riley", "Supersports"),
                                                        Map.entry("Brooklyn", "Camera World"),
                                                        Map.entry("Jay", "Homecase"));
  }

}
