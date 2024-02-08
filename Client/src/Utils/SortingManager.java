package Utils;

import java.io.IOException;

public class SortingManager {
    public long i = 0;
    public void getLenOfSerializableArray(Object o) {
        try {
            if (o != null) {
                byte[] serializableArray = DeserializeManager.serialize(o);
                this.i = serializableArray.length;
            }
        } catch (IOException e) {
            System.out.println("Проблема в методе ForSort.getLenOfSerializableArray()");
        }
    }
}
