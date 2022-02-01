package ch10b;

class SongV2 {
  private String title;
  private String artist;
  private String bpm;

  SongV2(String title, String artist, String bpm) {
    this.title = title;
    this.artist = artist;
    this.bpm = bpm;
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public String getBpm() {
    return bpm;
  }

  public String toString() {
    return title;
  }
}
