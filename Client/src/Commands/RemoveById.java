package Commands;

import Exceprions.InvalidParams;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Main.main.ERROR_MESSAGE;

public class RemoveById extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 12;
    Long id;

    public Boolean setId(String input) {
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
        if (paramString.size()==0) throw new InvalidParams(ERROR_MESSAGE+"Wrong call of the removeById command."+ ERROR_MESSAGE);
        setParam(paramString.get(0).split(" "));
    }

    @Override
    public void setParam(String[] paramString) throws InvalidParams {
        int PARAM_COUNT = 1;
        if ((paramString.length != PARAM_COUNT+1) || !this.setId(paramString[1])) throw new InvalidParams(ERROR_MESSAGE+"Wrong call of the removeById command. Type help to find out which commands are available to you."+ ERROR_MESSAGE);
    }
}
