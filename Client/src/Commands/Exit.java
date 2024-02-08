package Commands;

import Exceprions.InvalidParams;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Main.main.ERROR_MESSAGE;

public class Exit extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 7;

    @Override
    public void setParamFromFile(ArrayList<String> paramString) throws InvalidParams {
        if (paramString.size() != 1 || paramString.get(0).split(" ").length != 1) throw new InvalidParams(ERROR_MESSAGE+"Invalid data for command exit."+  ERROR_MESSAGE);
    }
}
