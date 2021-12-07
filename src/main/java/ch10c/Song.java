package ch10c;

public class Song {
  private final String title;
  private final String artist;
  private final int bpm;
  private final String genre;
  private final int year;

  public Song(String title, String artist, int bpm, String genre, int year) {
    this.title = title;
    this.artist = artist;
    this.bpm = bpm;
    this.genre = genre;
    this.year = year;
  }
}
