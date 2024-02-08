package MusicBand;

import java.io.Serializable;

public class Album implements Serializable {
    private String name;
    private Long length;

    public Album(String name, Long length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public static Boolean checkValidName(String name) {
        return name!=null && !name.equals("");
    }
    public static Boolean checkValidLength(Long length) {
        return length!=null && length > 0;
    }

    @Override
    public String toString() {
        return "name: " + this.name + "; " +
                "length: " + this.length;
    }
}