package Commands;

import Exceprions.InvalidParams;
import Main.Client;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

abstract public class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    public String username;
    public String password;

    void setParamFromFile(ArrayList<String> paramString) throws InvalidParams {}

    public void setParam(String[] paramString) throws InvalidParams {}
}
