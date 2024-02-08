package MusicBand;

import Exceprions.InvalidParams;
import Main.Client;

import javax.swing.*;
import java.util.List;

import static Main.main.*;

public class MusicBandBuilder {

    static private Long sequenceId = 0L;
    private String name;
    private Coordinates coordinates;
    private Integer x_coordinate;
    private long y_coordinate;

    private Integer numberOfParticipants;

    private MusicGenre genre;
    private Album bestAlbum;
    private String bestAlbumName;
    private Long bestAlbumLength;

    private String username;

    public MusicBandBuilder() {}

    public MusicBandBuilder setNameFromKeyboard() {
        System.out.print(INTERFACE_MESSAGE+"Enter the name:   "+ INTERFACE_MESSAGE); String input = scan.nextLine();
        if (input == null || input.isEmpty()) {
            System.out.println(ERROR_MESSAGE+"Field name of the MusicBand cannot be null or empty line."+ ERROR_MESSAGE);
            return setNameFromKeyboard();
        } this.name = input;

        return this;
    }

    public MusicBandBuilder setCoordinateFromKeyboard() {
        this.setXCoordinateFromKeyboard();
        this.setYCoordinateFromKeyboard();
        this.coordinates = new Coordinates(x_coordinate, y_coordinate); return this;
    }

    private void setXCoordinateFromKeyboard() {
        System.out.print(INTERFACE_MESSAGE+"Enter the x-coordinate:   "+ INTERFACE_MESSAGE); String input = scan.nextLine();
        try {
            this.x_coordinate = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_MESSAGE+"Wrong type of field: x-coordinate should be Integer."+ ERROR_MESSAGE);
            setXCoordinateFromKeyboard();
        }
    }

    private void setYCoordinateFromKeyboard() {
        System.out.print(INTERFACE_MESSAGE+"Do you want to set y-coordinate? (y/n)   "+ INTERFACE_MESSAGE); String answer = scan.nextLine();

        if (answer.equals("y")) {
            System.out.print(INTERFACE_MESSAGE+"Enter the y-coordinate:   "+ INTERFACE_MESSAGE); String input = scan.nextLine();
            try {
                this.y_coordinate = Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println(ERROR_MESSAGE+"Wrong type of field: y-coordinate should be long."+ ERROR_MESSAGE);
                setYCoordinateFromKeyboard();
            }
        }
        else if (!answer.equals("n")) {
            System.out.println(ERROR_MESSAGE+"Wrong type of answer. Try it again."+ ERROR_MESSAGE);
            setYCoordinateFromKeyboard();
        }
    }

    public MusicBandBuilder setNumberOfParticipantsFromKeyboard() {
        System.out.print(INTERFACE_MESSAGE+"Enter the numberOfParticipants:   "+ INTERFACE_MESSAGE); String input = scan.nextLine();
        try {
            int numberOfParticipants = Integer.parseInt(input);

            if (numberOfParticipants <= 0) {
                System.out.println(ERROR_MESSAGE+"Field numberOfParticipants of the MusicBand should be more than 0."+ ERROR_MESSAGE);
                return setNumberOfParticipantsFromKeyboard();
            }
            this.numberOfParticipants = Integer.parseInt(input); return this;
        } catch (NumberFormatException e) {
            System.out.println(ERROR_MESSAGE+"Wrong type of field: numberOfParticipants should be Integer."+ ERROR_MESSAGE);
            return setNumberOfParticipantsFromKeyboard();
        }
    }

    public MusicBandBuilder setGenreFromKeyboard() {
        System.out.print(INTERFACE_MESSAGE+"Do you want to set genre? (y/n)   "+ INTERFACE_MESSAGE); String answer = scan.nextLine();
        if (answer.equals("n")) return this;
        if (answer.equals("y")) {
            System.out.print(INTERFACE_MESSAGE+"Enter the genre:   "+ INTERFACE_MESSAGE);
            String input = scan.nextLine();
            try {
                this.genre = MusicGenre.valueOf(input);
                return this;
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_MESSAGE+"Wrong type of field: genre should be PSYCHEDELIC_CLOUD_RAP, JAZZ, MATH_ROCK or PUNK_ROCK."+ ERROR_MESSAGE);
                return setGenreFromKeyboard();
            }
        }
        else {
            System.out.println(ERROR_MESSAGE+"Wrong type of answer. Try it again."+ ERROR_MESSAGE);
            return setGenreFromKeyboard();
        }
    }

    public MusicBandBuilder setAlbumFromKeyboard() {
        this.setNameOfAlbumFromKeyboard().setLengthOfAlbumFromKeyboard();
        this.bestAlbum = new Album(bestAlbumName, bestAlbumLength); return this;
    }

    private MusicBandBuilder setNameOfAlbumFromKeyboard() {
        System.out.print(INTERFACE_MESSAGE+"Enter the name of best album:   "+ INTERFACE_MESSAGE); String input = scan.nextLine();
        if (input==null || input.equals("")) {
            System.out.println(ERROR_MESSAGE+"Field name of the Album cannot be null or empty line."+ ERROR_MESSAGE);
            return setNameOfAlbumFromKeyboard();
        }
        this.bestAlbumName = input; return this;
    }

    private MusicBandBuilder setLengthOfAlbumFromKeyboard() {
        System.out.print(INTERFACE_MESSAGE+"Enter the length of best album:   "+ INTERFACE_MESSAGE); String input = scan.nextLine();
        try {
            long length = Long.parseLong(input);
            if (length <= 0) {
                System.out.println(ERROR_MESSAGE+"Field Length of the Album should be more than 0."+ ERROR_MESSAGE);
                return setLengthOfAlbumFromKeyboard();
            }
            this.bestAlbumLength = Long.parseLong(input); return this;
        } catch (NumberFormatException e) {
            System.out.println(ERROR_MESSAGE+"Wrong type of field: length of best album should be long"+ ERROR_MESSAGE);
            return setLengthOfAlbumFromKeyboard();
        }
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

    public MusicBandBuilder setMusicBandFromStringArray(List<String> arrayLines) throws InvalidParams {
        if (arrayLines.size() != 7) throw new InvalidParams(ERROR_MESSAGE+"Invalid data for creating musicBand example."+ ERROR_MESSAGE);

        try {
            this.name = arrayLines.get(0);
            this.x_coordinate = Integer.parseInt(arrayLines.get(1));

            String yString = arrayLines.get(2);
            if (yString.equals("")) this.coordinates = new Coordinates(this.x_coordinate);
            else {
                this.y_coordinate = Long.parseLong(arrayLines.get(2));
                this.coordinates = new Coordinates(this.x_coordinate, this.y_coordinate);
            }

            this.numberOfParticipants = Integer.parseInt(arrayLines.get(3));
            String genreString = arrayLines.get(4);
            if (!genreString.equals("")) this.genre = MusicGenre.valueOf(genreString);

            String nameBestAlbum = arrayLines.get(5);
            Long lengthBestAlbum = Long.parseLong(arrayLines.get(6));
            this.bestAlbum = new Album(nameBestAlbum, lengthBestAlbum);
        } catch (NumberFormatException e) {
            throw new InvalidParams(ERROR_MESSAGE + "Invalid data for creating musicBand example."+ ERROR_MESSAGE);
        }

        return this;
    }

    private void setLengthOfAlbum(Long bestAlbumLength) throws NumberFormatException {
        try {
            if (bestAlbumLength <= 0) throw new NumberFormatException();
            this.bestAlbumLength = bestAlbumLength;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_MESSAGE + "Field length of the best album cannot be null. File wasn't fully read." + ERROR_MESSAGE);
        }
    }

    public MusicBandBuilder setUsername() throws NumberFormatException {
        this.username = Client.username;
        return this;
    }

    public MusicBand build() throws IllegalStateException{
        if (name == null || bestAlbum == null || coordinates == null || numberOfParticipants == null) {
            throw new IllegalStateException(ERROR_MESSAGE+"Not all fields of the object MusicBand.MusicBand are filled in"+ ERROR_MESSAGE);
        }
        return new MusicBand(name, coordinates, numberOfParticipants, genre, bestAlbum, username);
    }
}

