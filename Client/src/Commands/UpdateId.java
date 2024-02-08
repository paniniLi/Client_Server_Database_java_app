package Commands;

import Exceprions.InvalidParams;
import MusicBand.MusicBand;
import MusicBand.MusicBandBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Main.main.ERROR_MESSAGE;

public class UpdateId extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 17;
    Long id;
    MusicBand musicBand;

    private Boolean setId(String input) {

        try {
            long id = Long.parseLong(input);
            if (id <= 0) return false;
            this.id = id; return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void setParamFromFile(ArrayList<String> paramString) throws InvalidParams {
        int PARAM_COUNT = 7;
        if ((paramString.size() != PARAM_COUNT+1) || paramString.get(0).split(" ").length != 2 || !this.setId(paramString.get(0).split(" ")[1])) throw new InvalidParams(ERROR_MESSAGE+"Invalid calling of the updateId command. Command was not executed."+ ERROR_MESSAGE);
        MusicBandBuilder musicBandBuilder = new MusicBandBuilder()
                .setMusicBandFromStringArray(paramString.subList(1, paramString.size()))
                .setUsername();
        this.musicBand = musicBandBuilder.build();
        System.out.println(musicBand.getId());
    }

    @Override
    public void setParam(String[] paramString) throws InvalidParams {
        int PARAM_COUNT = 1;
        if ((paramString.length != PARAM_COUNT+1) || !this.setId(paramString[1])) throw new InvalidParams(ERROR_MESSAGE+"Wrong call of the updateId command. Type help to find out which commands are available to you."+ ERROR_MESSAGE);

        MusicBandBuilder musicBandBuilder = new MusicBandBuilder()
                .setNameFromKeyboard()
                .setCoordinateFromKeyboard()
                .setNumberOfParticipantsFromKeyboard()
                .setGenreFromKeyboard()
                .setAlbumFromKeyboard()
                .setUsername();
        this.musicBand = musicBandBuilder.build();
    }
}
