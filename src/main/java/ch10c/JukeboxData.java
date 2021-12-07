package ch10c;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

// Interesting points: "special" characters á, punctuation marks, multiple cover versions, BPM ranges (and maybe doubled/halved rates?)
// TODO: introduce duplicates for number of times played
// or: add a timesPlayed field.
public class JukeboxData {
  static class Songs {
    private final Map<Integer, Song> songs;

    public Songs() {
      songs = Map.ofEntries(
              entry(1, new Song(1, "$10", "Hitchhiker", 140, "Electronic", 2016)),
              entry(2, new Song(2, "Havana", "Camila Cabello", 105, "R&B", 2017)),
              entry(3, new Song(3, "Cassidy", "Grateful Dead", 158, "Rock", 1972)),
              entry(4, new Song(4, "50 ways", "Paul Simon", 102, "Soft Rock", 1975)),
              entry(5, new Song(5, "Hurt", "Nine Inch Nails", 79, "Industrial Rock", 1995)),
              entry(6, new Song(6, "Silence", "Delerium", 98, "Trance", 1999)),
              entry(7, new Song(7, "Hurt", "Johnny Cash", 91, "Soft Rock", 2002)),
              entry(8, new Song(8, "Watercolour", "Pendulum", 174, "Electronic", 2010)),
              entry(9, new Song(9, "The Outsider", "A Perfect Circle", 113, "Alternative Rock", 2004)),
              entry(10, new Song(10, "With a Little Help from My Friends", "The Beatles", 112, "Rock", 1967)),
              entry(11, new Song(11, "Come Together", "The Beatles", 92, "Blues rock", 1968)),
              entry(12, new Song(12, "Come Together", "The Smokin' Mojo Filters", 176, "Rock", 1995)),
              entry(13, new Song(13, "With a Little Help from My Friends", "Joe Cocker", 72, "Rock", 1968)),
              entry(14, new Song(14, "Immigrant Song", "Karen O", 120, "Industrial Rock", 2011)),
              entry(15, new Song(15, "Breathe", "The Prodigy", 130, "Electronic", 1996)),
              entry(16, new Song(16, "What's Going On", "Gaye", 203, "R&B", 1971)),
              entry(17, new Song(17, "Hallucinate", "Dua Lipa", 122, "Pop", 2020)),
              entry(18, new Song(18, "Walk Me Home", "P!nk", 88, "Pop", 2019)),
              entry(19, new Song(19, "I am not a woman, I'm a god", "Halsey", 180, "Alternative Rock", 2021)),
              entry(20, new Song(20, "Pasos de cero", "Pablo Alborán", 99, "Latin", 2014)),
              entry(21, new Song(21, "Nature's Kingdom", "Delerium", 85, "Trance", 2000)),
              entry(22, new Song(22, "Smooth", "Santana", 116, "Latin", 1999)),
              entry(23, new Song(23, "Immigrant song", "Led Zeppelin", 112, "Rock", 1970)));
    }

    public List<Song> getSongs() {
      return new ArrayList<>(songs.values());
    }

    public Song getSongById(int id) {
      return songs.get(id);
    }

  }


  public static void main(String[] args) {
//    Songs songs = new Songs();
//    List<Song> songList = songs.getSongs();
//
//    // ORDER MATTERS
//    List<String> genres = songList.stream().map(song -> song.getGenre())
//                                  .distinct()
//                                  .collect(Collectors.toList());
//    System.out.println("genres = " + genres);
//
//    System.out.println(songList.stream().map(song -> song.getGenre())
//                               .distinct()
//                               .count());
//
//    Map<String, Long> genreCount = songList.stream().map(song -> song.getGenre())
//                                           .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//    System.out.println("genreCount = " + genreCount);

  }

}
