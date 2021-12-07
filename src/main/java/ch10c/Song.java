package ch10c;

public class Song {
  private final String title;
  private final String artist;
  private final int bpm;
  private final String genre;
  private final int year;
  private final int timesPlayed;

  public Song(String title, String artist, int bpm, String genre, int year, int timesPlayed) {
    this.title = title;
    this.artist = artist;
    this.bpm = bpm;
    this.genre = genre;
    this.year = year;
    this.timesPlayed = timesPlayed;
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public int getBpm() {
    return bpm;
  }

  public String getGenre() {
    return genre;
  }

  public int getYear() {
    return year;
  }

  @Override
  public String toString() {
    return "Song{" +
            "title='" + title + '\'' +
            ", artist='" + artist + '\'' +
            ", bpm=" + bpm +
            ", genre='" + genre + '\'' +
            ", year=" + year +
            ", timesPlayed=" + timesPlayed +
            '}';
  }
}
