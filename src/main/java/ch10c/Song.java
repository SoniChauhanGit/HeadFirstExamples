package ch10c;

public class Song {
  private final int id;
  private final String title;
  private final String artist;
  private final int bpm;
  private final String genre;
  private final int year;

  public Song(int id, String title, String artist, int bpm, String genre, int year) {
    this.id = id;
    this.title = title;
    this.artist = artist;
    this.bpm = bpm;
    this.genre = genre;
    this.year = year;
  }

  @Override
  public String toString() {
    return "Song{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", artist='" + artist + '\'' +
            ", bpm=" + bpm +
            ", genre='" + genre + '\'' +
            ", year=" + year +
            '}';
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
}
