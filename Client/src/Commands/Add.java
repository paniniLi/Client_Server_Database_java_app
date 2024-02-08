package Commands;

import Exceprions.InvalidParams;
import MusicBand.MusicBand;
import MusicBand.MusicBandBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Main.main.ERROR_MESSAGE;

public class Add extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 2;

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
                .setMusicBandFromStringArray(paramString.subList(1, paramString.size()))
                .setUsername();
        this.musicBand = musicBandBuilder.build();
    }

    @Override
    public void setParam(String[] paramString) throws InvalidParams {
        this.setMusicBandFromKeyboard();
    }
}
