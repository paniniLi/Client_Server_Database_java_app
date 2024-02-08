package MusicBand;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MusicBand implements Comparable<MusicBand>, Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    static private Long sequenceId = 0L;
    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private Integer numberOfParticipants;
    private MusicGenre genre;
    private Album bestAlbum;

    private String username;

    public MusicBand(Long id, LocalDateTime creationDate, String name, Coordinates coordinates, Integer numberOfParticipants, MusicGenre genre, Album bestAlbum, String username){
        this.name = name;
        this.coordinates = coordinates;
        this.numberOfParticipants = numberOfParticipants;
        this.genre = genre;
        this.bestAlbum = bestAlbum;
        this.id = id;
        this.creationDate = creationDate;
        this.username = username;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public Album getBestAlbum() {
        return bestAlbum;
    }

    public void setBestAlbum(Album bestAlbum) {
        this.bestAlbum = bestAlbum;
    }
    public String getUsername() {
        return username;
    }
    private void setUsername(String username) {
        this.username = username;
    }

     @Override
     public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("MusicBand №").append(this.id).append(":\n");
        info.append("\tName: ").append(this.name).append("\n");
        info.append("\tMusicBand.Coordinates: ").append("\n\t\t").append(this.coordinates.toString()).append("\n");
        info.append("\tCreation date: ").append(this.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"))).append("\n");
        info.append("\tNumber of participants: ").append(this.numberOfParticipants).append("\n");

        if (genre != null) {
            info.append("\tGenre: ").append(this.genre).append("\n");
        }

        info.append("\tMusicBand.Album: ").append("\n\t\t").append(this.bestAlbum.toString());
        return info.toString();
     }

    @Override
    public int compareTo(MusicBand musicBand) {
        return this.coordinates.compareTo(musicBand.coordinates);
    }

}