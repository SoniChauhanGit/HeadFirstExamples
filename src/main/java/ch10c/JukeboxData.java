package ch10c;

import java.util.List;

// Interesting points: "special" characters á, punctuation marks, multiple cover versions, BPM ranges (and maybe doubled/halved rates?)
// TODO: introduce duplicates for number of times played
// or: add a timesPlayed field.
public class JukeboxData {
  class Songs {
    public List<Song> getSongs() {
      return List.of(
              new Song("Somersault", "Zero 7", 147, "New wave", 2004),
              new Song("$10", "Hitchhiker", 140, "Dance",2016),
              new Song("Havana", "Camila Cabello", 105, "R&B", 2017),
              new Song("Cassidy", "Grateful Dead", 158, "Rock", 1972),
              new Song("50 ways", "Paul Simon", 102, "Soft Rock", 1975),
              new Song("If There's a Rocket Tie Me to It", "Snow Patrol", 140, "Soft Rock", 2009),
              new Song("Hurt", "Nine Inch Nails", 79, "Industrial Rock", 1995),
              new Song("Silence", "Delerium", 98, "Trance", 1999),
              new Song("Hurt", "Johnny Cash", 91, "Acoustic Rock", 2002),
              new Song("Watercolour", "Pendulum", 174, "Electronic", 2010),
              new Song("The Outsider", "A Perfect Circle", 113, "Alternative Rock", 2004),
              new Song("With a Little Help from My Friends", "The Beatles", 112, "Rock", 1967),
              new Song("Come Together", "The Beatles", 92, "Blues rock", 1968),
              new Song("Take Five", "Brubeck", 174, "Jazz", 1959),
              new Song("Come Together", "The Smokin' Mojo Filters", 176, "Rock", 1995),
              new Song("With a Little Help from My Friends", "Joe Cocker", 72, "Soul", 1968),
              new Song("Immigrant Song", "Karen O feat Trent Reznor and Atticus Ross", 120, "Industrial Rock", 2011),
              new Song("High Hopes", "Panic! at the Disco", 164, "Pop", 2018),
              new Song("Machine", "Imagine Dragons", 81, "Rock", 2018),
              new Song("Breathe", "The Prodigy", 130, "Electronic", 1996),
              new Song("What's Going On", "Gaye", 203, "R&B", 1971),
              new Song("Hallucinate", "Dua Lipa", 122, "Disco", 2020),
              new Song("Walk Me Home", "P!nk", 88, "Pop", 2019),
              new Song("I am not a woman, I'm a god", "Halsey", 180, "Alternative Rock", 2021),
              new Song("Pasos de cero", "Pablo Alborán", 99, "Latin", 2014),
              new Song("Nature's Kingdom", "Delerium", 85, "Trance", 2000),
              new Song("Smooth", "Santana", 116, "Rock", 1999),
              new Song("Immigrant song", "Led Zeppelin", 112, "Rock", 1970));
    }
  }

}
