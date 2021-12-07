package ch10c;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// Interesting points: "special" characters á, punctuation marks, multiple cover versions, BPM ranges (and maybe doubled/halved rates?)
// TODO: introduce duplicates for number of times played
// or: add a timesPlayed field.
public class JukeboxData {
  static class Songs {
    public List<Song> getSongs() {
      return List.of(
              new Song("$10", "Hitchhiker", 140, "Electronic", 2016),
              new Song("Havana", "Camila Cabello", 105, "R&B", 2017),
              new Song("Cassidy", "Grateful Dead", 158, "Rock", 1972),
              new Song("50 ways", "Paul Simon", 102, "Soft Rock", 1975),
              new Song("Hurt", "Nine Inch Nails", 79, "Industrial Rock", 1995),
              new Song("Silence", "Delerium", 98, "Trance", 1999),
              new Song("Hurt", "Johnny Cash", 91, "Soft Rock", 2002),
              new Song("Watercolour", "Pendulum", 174, "Electronic", 2010),
              new Song("The Outsider", "A Perfect Circle", 113, "Alternative Rock", 2004),
              new Song("With a Little Help from My Friends", "The Beatles", 112, "Rock", 1967),
              new Song("Come Together", "The Beatles", 92, "Blues rock", 1968),
              new Song("Come Together", "The Smokin' Mojo Filters", 176, "Rock", 1995),
              new Song("With a Little Help from My Friends", "Joe Cocker", 72, "Rock", 1968),
              new Song("Immigrant Song", "Karen O feat Trent Reznor and Atticus Ross", 120, "Industrial Rock", 2011),
              new Song("Breathe", "The Prodigy", 130, "Electronic", 1996),
              new Song("What's Going On", "Gaye", 203, "R&B", 1971),
              new Song("Hallucinate", "Dua Lipa", 122, "Pop", 2020),
              new Song("Walk Me Home", "P!nk", 88, "Pop", 2019),
              new Song("I am not a woman, I'm a god", "Halsey", 180, "Alternative Rock", 2021),
              new Song("Pasos de cero", "Pablo Alborán", 99, "Latin", 2014),
              new Song("Nature's Kingdom", "Delerium", 85, "Trance", 2000),
              new Song("Smooth", "Santana", 116, "Latin", 1999),
              new Song("Immigrant song", "Led Zeppelin", 112, "Rock", 1970));
    }
  }

  public static void main(String[] args) {
    Songs songs = new Songs();
    List<Song> songList = songs.getSongs();

    // ORDER MATTERS
    List<String> genres = songList.stream().map(song -> song.getGenre())
                                  .distinct()
                                  .collect(Collectors.toList());
    System.out.println("genres = " + genres);

    System.out.println(songList.stream().map(song -> song.getGenre())
                               .distinct()
                               .count());

    Map<String, Long> genreCount = songList.stream().map(song -> song.getGenre())
                                           .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    System.out.println("genreCount = " + genreCount);

  }

}
