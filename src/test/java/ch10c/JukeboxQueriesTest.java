package ch10c;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

class JukeboxQueriesTest {
  private JukeboxData.Songs songs;

  @BeforeEach
  void setUp() {
    songs = new JukeboxData.Songs();
  }

  @Test
  void shouldOutputAllSongsPlayed() {
    List<Song> allSongs = songs.getSongs();

    System.out.println(allSongs);
  }

  // STRAIGHTFORWARD
  // Sorting, Collect to List
  @Test
  void shouldOrderByLeastPlayed() {
    List<Song> allSongs = songs.getSongs();

    List<Song> result = allSongs.stream()
                                .sorted(Comparator.comparingInt(Song::getTimesPlayed))
                                .collect(Collectors.toList());

    System.out.println(result);
  }

  // CANDIDATE
  // REUSE - method references
  // Comparator/sorting; collect
  @Test
  void shouldFindTopFiveSongs() {
    List<Song> allSongs = songs.getSongs();

    // simple
    List<Song> result = allSongs.stream()
                                .sorted((o1, o2) -> o2.getTimesPlayed() - o1.getTimesPlayed())
                                .limit(5)
                                .collect(Collectors.toList());
    System.out.println(result);

    // reuse
    result = allSongs.stream()
                     .sorted(Comparator.comparingInt(Song::getTimesPlayed).reversed())
                     .limit(5)
                     .collect(Collectors.toList());

    System.out.println(result);
  }

  // REGEX
  @Test
  void shouldFindNonStandardCharacters() {
    List<Song> allSongs = songs.getSongs();

    // Includes space!!
    Pattern pattern = Pattern.compile("[^A-Za-z0-9 ]");

    List<String> result = allSongs.stream()
                                  .map(Song::getArtist)
                                  .filter(artist -> pattern.matcher(artist).find())
                                  .collect(Collectors.toList());

    System.out.println("result = " + result);
  }

  @Test
  void shouldQueryForGenre() {
    List<Song> allSongs = songs.getSongs();
    // ORDER MATTERS
    // find all genres in played music
    List<String> genres = allSongs.stream()
                                  .map(song -> song.getGenre())
                                  .distinct()
                                  .collect(toList());
    System.out.println("genres = " + genres);

    // find number of distinct genres
    System.out.println(allSongs.stream()
                               .map(song -> song.getGenre())
                               .distinct()
                               .count());

    // find number songs for each genre
    // GROUPING
    Map<String, Long> genreCount = allSongs.stream().map(song -> song.getGenre())
                                           .collect(groupingBy(Function.identity(), counting()));
    System.out.println("genreCount = " + genreCount);
  }

  // OPTIONAL
  // max terminal
  // Comparator
  @Test
  void shouldFindMostPopularSong() {
    List<Song> allSongs = songs.getSongs();

    Optional<Song> result = allSongs.stream()
                                    .max(Comparator.comparingInt(Song::getTimesPlayed));

    System.out.println(result.get());
  }

  @Test
  void shouldFindMostPopularGenre() {
    List<Song> allSongs = songs.getSongs();

    HashMap<String, Integer> genreCount = new HashMap<>();

    for (Song song : allSongs) {
      int timesPlayed = song.getTimesPlayed();
      String genre = song.getGenre();
      if (genreCount.get(genre) == null) {
        genreCount.put(genre, timesPlayed);
      } else {
        Integer integer = genreCount.get(genre);
        genreCount.put(genre, integer + timesPlayed);
      }
    }
    System.out.println("result = " + genreCount);
  }

  // CANDIDATE
  // STRAIGHTFORWARD
  // filter
  // toList
  @Test
  void shouldFindHowManySongsAreSomeSortOfRock() {
    List<Song> allSongs = songs.getSongs();

    List<Song> result = allSongs.stream()
                                .filter(song -> song.getGenre().contains("Rock"))
                                .collect(toList());

    System.out.println(result);
  }

  // GROUPING
  @Test
  void shouldFindNumberOfTimesSongTitleAppears() {
    List<Song> allSongs = songs.getSongs();

    Map<String, Long> result = allSongs.stream()
                                       .map(Song::getTitle)
                                       .collect(groupingBy(Function.identity(), counting()));

    System.out.println("result = " + result);
  }

  // CANDIDATE
  // STRAIGHTFORWARD
  // min terminal
  // OPTIONAL
  @Test
  void shouldGetOldestSong() {
    List<Song> allSongs = songs.getSongs();
    Optional<Song> result = allSongs.stream()
                                    .min(Comparator.comparingInt(Song::getYear));

    System.out.println("result = " + result.get());
  }

  // STRAIGHTFORWARD
  // filter (with param)
  // toList
  @Test
  void shouldBeAbleToFindAllVersionsOfASong() {
    List<Song> allSongs = songs.getSongs();

    String songTitle = "Hurt";
    List<Song> result = allSongs.stream()
                                .filter(song -> song.getTitle().equals(songTitle))
                                .collect(toList());

    System.out.println("result = " + result);
  }

  // CANDIDATE
  @Test
  void shouldFindAllArtistsForASpecificSong() {
    List<Song> allSongs = songs.getSongs();

    String songTitle = "With a Little Help from My Friends";
    List<String> result = allSongs.stream()
                                  .filter(song -> song.getTitle().equals(songTitle))
                                  .map(Song::getArtist)
                                  .collect(toList());

    System.out.println("result = " + result);
  }

  // CANDIDATE
  @Test
  void shouldComplexExample() {
    List<Song> allSongs = songs.getSongs();

    String songTitle = "With a Little Help from My Friends";
//    List<String> result = allSongs.stream()
//                                  .filter(song -> song.getTitle().equals(songTitle))
//                                  .sorted(Comparator.comparing(Song::getTimesPlayed))
//                                  .map(Song::getArtist)
//                                  .collect(toList());
//
//    System.out.println("result = " + result);

    List<Song> toSort = new ArrayList<>();
    for (Song song : allSongs) {
      if (song.getTitle().equals(songTitle)) {
        toSort.add(song);
      }
    }
    toSort.sort(Comparator.comparing(Song::getTimesPlayed));
    List<String> result = new ArrayList<>();
    for (Song song : toSort) {
      String artist = song.getArtist();
      result.add(artist);
    }

    System.out.println("result = " + result);
  }

  // CANDIDATE
  // PRIMITIVE STREAMS
  @Test
  void shouldQueryForYear() {
    List<Song> allSongs = songs.getSongs();

    // adding distinct changes this
    IntSummaryStatistics result = allSongs.stream()
                                          .mapToInt(Song::getYear)
                                          .sorted()
                                          .summaryStatistics();

    System.out.println("result = " + result);
  }

  // PRIMITIVE STREAMS
  @Test
  void shouldBeAbleToCalculateCountForAllVersionsOfASong() {
    List<Song> allSongs = songs.getSongs();

    String songTitle = "Hurt";
    int result = allSongs.stream()
                         .filter(song -> song.getTitle().equals(songTitle))
                         .mapToInt(Song::getTimesPlayed)
                         .sum();

    System.out.println("result = " + result);
  }
}