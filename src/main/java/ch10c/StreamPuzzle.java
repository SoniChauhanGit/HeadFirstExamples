package ch10c;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamPuzzle {
  public static void main(String[] args) {
    List<Song> songs = new JukeboxData.Songs().getSongs();

    List<String> topFive = songs.stream()
                                .sorted(Comparator.comparingInt(Song::getTimesPlayed))
                                .map(song -> song.getTitle())
                                .limit(5)
                                .collect(Collectors.toList());
    System.out.println("Top 5 songs: " + topFive);

    searchForArtist(songs, "The Beatles");
    searchForArtist(songs, "The Beach Boys");
  }

  private static void searchForArtist(List<Song> songs, String artist) {
    Optional<Song> result = songs.stream()
                                 .filter(song -> song.getArtist().equals(artist))
                                 .findFirst();
    if (result.isPresent()) {
      System.out.println(result.get().getTitle());
    } else {
      System.out.println("No songs found by: " + artist);
    }
  }
}
