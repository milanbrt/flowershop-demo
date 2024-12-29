package util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Crypto {
    private static final Crypto INSTANCE = new Crypto();
    private Crypto() {}
    public static Crypto getInstance() {
        return INSTANCE;
    }
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
