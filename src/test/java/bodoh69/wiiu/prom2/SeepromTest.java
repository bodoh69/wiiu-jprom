package bodoh69.wiiu.prom2;

import bodoh69.wiiu.prom.Otp;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class SeepromTest {

    @Test
    @Disabled
    void validate() throws Exception {
        try (InputStream is = Otp.class.getResourceAsStream("/seeprom.bin")) {
            byte[] content = new byte[512];
            is.read(content);
            Seeprom.validate(content);
        }
    }
}
