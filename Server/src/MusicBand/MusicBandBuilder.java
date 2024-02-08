package MusicBand;
import java.time.LocalDateTime;
import static Main.FabricOfServer.ERROR_MESSAGE;

public class MusicBandBuilder {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private Integer x_coordinate;
    private long y_coordinate;

    private LocalDateTime creationDate;

    private Integer numberOfParticipants;

    private MusicGenre genre;
    private Album bestAlbum;
    private String bestAlbumName;
    private Long bestAlbumLength;

    private String username;

    public MusicBandBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MusicBandBuilder setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public MusicBandBuilder setName(String nameString) throws NumberFormatException {
        if (!nameString.equals("")) {
            this.name = nameString;
            return this;
        } else throw new NumberFormatException(ERROR_MESSAGE+"Field name of the MusicBand.MusicBand cannot be null or empty line. File wasn't fully read."+ ERROR_MESSAGE);
    }

    public MusicBandBuilder setCoordinate(Integer x, long y) {
        this.setXCoordinate(x); this.setYCoordinate(y);
        this.coordinates = new Coordinates(this.x_coordinate, this.y_coordinate);
        return this;
    }

    private void setXCoordinate(Integer x) {
        this.x_coordinate = x;
    }

    private void setYCoordinate(long y) {
        this.y_coordinate = y;
    }

    public MusicBandBuilder setNumberOfParticipants(Integer numberOfParticipants) throws NumberFormatException{
        if (numberOfParticipants > 0) this.numberOfParticipants = numberOfParticipants;
        else throw new NumberFormatException(ERROR_MESSAGE+"Wrong value of numberOfParticipants: it should be > 0. File wasn't fully read."+ ERROR_MESSAGE);

        return this;
    }

    public MusicBandBuilder setGenre(MusicGenre genre) {
        this.genre = genre;
        return this;
    }

    public MusicBandBuilder setAlbum(String bestAlbumName, Long bestAlbumLength) throws NumberFormatException {
        this.setNameOfAlbum(bestAlbumName); this.setLengthOfAlbum(bestAlbumLength);
        this.bestAlbum = new Album(this.bestAlbumName, this.bestAlbumLength);
        return this;
    }

    private void setNameOfAlbum(String bestAlbumName) throws NumberFormatException {
        if (!bestAlbumName.equals("")) {
            this.bestAlbumName = bestAlbumName;
        } else throw new NumberFormatException(ERROR_MESSAGE+"Field name of the best album cannot be null. File wasn't fully read."+ ERROR_MESSAGE);
    }

    private void setLengthOfAlbum(Long bestAlbumLength) throws NumberFormatException {
        try {
            if (bestAlbumLength <= 0) throw new NumberFormatException();
            this.bestAlbumLength = bestAlbumLength;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_MESSAGE + "Field length of the best album cannot be null. File wasn't fully read." + ERROR_MESSAGE);
        }
    }

    public MusicBandBuilder setUsername(String username) throws NumberFormatException {
        this.username = username;
        return this;
    }

    public MusicBand build() throws IllegalStateException{
        if (id == null || creationDate == null || name == null || bestAlbum == null || coordinates == null || numberOfParticipants == null) {
            throw new IllegalStateException(ERROR_MESSAGE+"Not all fields of the object MusicBand.MusicBand are filled in"+ ERROR_MESSAGE);
        }
        return new MusicBand(id, creationDate, name, coordinates, numberOfParticipants, genre, bestAlbum, username);
    }
}

