package Commands;

import Exceprions.InvalidParams;
import MusicBand.MusicBand;
import MusicBand.MusicBandBuilder;
import static Main.main.ERROR_MESSAGE;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class AddIfMin extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 3;
    MusicBand musicBand;

    public void setMusicBandFromKeyboard() {
        MusicBandBuilder musicBandBuilder = new MusicBandBuilder()
                .setNameFromKeyboard()
                .setCoordinateFromKeyboard()
                .setNumberOfParticipantsFromKeyboard()
                .setGenreFromKeyboard()
                .setAlbumFromKeyboard()
                .setUsername();
        this.musicBand = musicBandBuilder.build();
    }

    @Override
    public void setParamFromFile(ArrayList<String> paramString) throws InvalidParams {
        if (paramString.size()<1) throw new InvalidParams(ERROR_MESSAGE+"Invalid data for creating musicBand example."+ ERROR_MESSAGE);
        MusicBandBuilder musicBandBuilder = new MusicBandBuilder()
                .setMusicBandFromStringArray(paramString.subList(1, paramString.size()-1))
                .setUsername();
        this.musicBand = musicBandBuilder.build();
    }

    @Override
    public void setParam(String[] paramString) throws InvalidParams {
        int PARAM_COUNT = 0;
        if (paramString.length != PARAM_COUNT+1) throw new InvalidParams(ERROR_MESSAGE+"Wrong call of the addIfMin command. Type help to find out which commands are available to you."+ ERROR_MESSAGE);
        this.setMusicBandFromKeyboard();
    }
}
