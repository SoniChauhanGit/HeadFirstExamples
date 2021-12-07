package ch10c;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
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

  @Test
  void shouldOrderByLeastPlayed() {
    List<Song> allSongs = songs.getSongs();

    List<Song> result = allSongs.stream()
                                .sorted(Comparator.comparingInt(Song::getTimesPlayed))
                                .collect(Collectors.toList());

    System.out.println(result);
  }

  @Test
  void shouldQueryForGenre() {
    List<Song> allSongs = songs.getSongs();
    // ORDER MATTERS
    List<String> genres = allSongs.stream().map(song -> song.getGenre())
                                  .distinct()
                                  .collect(toList());
    System.out.println("genres = " + genres);

    System.out.println(allSongs.stream().map(song -> song.getGenre())
                               .distinct()
                               .count());

    Map<String, Long> genreCount = allSongs.stream().map(song -> song.getGenre())
                                           .collect(groupingBy(Function.identity(), counting()));
    System.out.println("genreCount = " + genreCount);
  }

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

  @Test
  void shouldFindHowManySongsAreSomeSortOfRock() {
    List<Song> allSongs = songs.getSongs();

    List<Song> result = allSongs.stream()
                              .filter(song -> song.getGenre().contains("Rock"))
                              .collect(toList());

    System.out.println(result);
  }

  @Test
  void shouldFindNumberOfTimesSongTitleAppears() {
    List<Song> allSongs = songs.getSongs();

    Map<String, Long> result = allSongs.stream()
                                        .map(Song::getTitle)
                                        .collect(groupingBy(Function.identity(), counting()));

    System.out.println("result = " + result);
  }
}