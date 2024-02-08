package Commands;

import Exceprions.InvalidParams;
import Main.Client;

import java.io.Serial;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import static Main.main.scan;
import static Utils.EncryptorManager.encryptPassword;

public class Register  extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 19;

    @Override
    public void setParam(String[] paramString) throws InvalidParams {
        try {
            System.out.print("Enter the username:    ");
            Client.username = scan.nextLine();
            System.out.print("Enter the password:    ");
            Client.password = encryptPassword(scan.nextLine());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
