package ch10c;

import java.util.ArrayList;
import java.util.List;

// Interesting points: "special" characters á, punctuation marks, multiple cover versions, BPM ranges (and maybe doubled/halved rates?)
// TODO: introduce duplicates for number of times played
// or: add a timesPlayed field.
public class JukeboxData {
  class Songs {
    public List<Song> getSongs() {
      return List.of(
              new Song("Somersault", "Zero 7", 147),
              new Song("$10", "Hitchhiker", 140),
              new Song("Havana", "Camila Cabello", 105),
              new Song("Cassidy", "Grateful Dead", 158),
              new Song("50 ways", "Paul Simon", 102),
              new Song("If There's a Rocket Tie Me to It", "Snow Patrol", 140),
              new Song("Hurt", "Nine Inch Nails", 79),
              new Song("Hurt", "Johnny Cash", 91),
              new Song("Watercolour", "Pendulum", 174),
              new Song("The Outsider", "A Perfect Circle", 113),
              new Song("The Turning", "Oasis", 120),
              new Song("With a Little Help from My Friends", "The Beatles", 112),
              new Song("Come Together", "The Beatles", 92),
              new Song("Come Together", "The Smokin' Mojo Filters", 176),
              new Song("With a Little Help from My Friends", "Joe Cocker", 72),
              new Song("Immigrant Song", "Karen O feat Trent Reznor and Atticus Ross", 120),
              new Song("High Hopes", "Panic! at the Disco", 164),
              new Song("Machine", "Imagine Dragons", 81),
              new Song("Breathe", "The Prodigy", 130),
              new Song("Hallucinate", "Dua Lipa", 122),
              new Song("Walk Me Home", "P!nk", 88),
              new Song("I am not a woman, I'm a god", "Halsey", 180),
              new Song("Pasos de cero", "Pablo Alborán", 99),
              new Song("Immigrant song", "Led Zeppelin", 112));
    }
  }

  public ArrayList<SongV3> getSongsV3() {
    ArrayList<SongV3> mySongs = new ArrayList<>();
    mySongs.add(new SongV3(1, "Cassidy", "Grateful Dead", "Rock", 1972));
    mySongs.add(new SongV3(2, "Crazy Diamond", "Pink Floyd", "Rock", 1975));
    mySongs.add(new SongV3(3, "Rosen Im Asphalt", "Schnauss", "Electronic", 2017));
    mySongs.add(new SongV3(4, "Havana", "Cabillo", "R&B", 2017));
    mySongs.add(new SongV3(5, "Take Five", "Brubeck", "Jazz", 1959));
    mySongs.add(new SongV3(6, "Jumpin' Jack Flash", "Stones", "rock", 1964));
    mySongs.add(new SongV3(7, "Silence", "Delerium", "Trance", 1999));
    mySongs.add(new SongV3(8, "Lochs of Dread", "Strength in Numbers", "Newgrass", 1989));
    mySongs.add(new SongV3(9, "No Woman No Cry", "Marley", "Reggae", 1974));
    mySongs.add(new SongV3(10, "If I Ain't Got You", "Keys", "R&B", 2003));
    mySongs.add(new SongV3(11, "Don't Know Why", "Jones", "Jazz", 2002));
    mySongs.add(new SongV3(12, "Smooth", "Santana", "Rock", 1999));
    mySongs.add(new SongV3(13, "Come Together", "Beatles", "Rock", 1969));
    mySongs.add(new SongV3(14, "Love on a Real Train", "Tangerine Dream", "Electronic", 1984));
    mySongs.add(new SongV3(15, "Smoothie Song, Live", "Nickel Creek", "Newgrass", 2021));
    mySongs.add(new SongV3(16, "Switch Up", "Protoje", "Reggae", 2020));
    mySongs.add(new SongV3(17, "Stomp and Buck Dance", "Crusaders", "Jazz", 1974));
    mySongs.add(new SongV3(18, "Nature's Kingdom", "Delerium", "Trance", 2000));
    mySongs.add(new SongV3(19, "Eyes of the World", "Grateful Dead", "Rock", 1973));
    mySongs.add(new SongV3(20, "Midnight Train to Georgia", "Knight", "R&B", 1976));
    mySongs.add(new SongV3(21, "Black Clouds", "String Cheese", "Newgrass", 1997));
    mySongs.add(new SongV3(22, "What's Going On", "Gaye", "R&B", 1971));

    return mySongs;
  }

  class SongV3 {
    int id;
    String title;
    String band;
    String genre;
    int year;

    SongV3() {
    }

    SongV3(int id, String title, String band, String genre, int year) {
      this.id = id;
      this.title = title;
      this.band = band;
      this.genre = genre;
      this.year = year;
    }
  }
}
