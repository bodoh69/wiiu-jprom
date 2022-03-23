package bodoh69.wiiu.prom;

import bodoh69.util.Hexer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class OtpTest {
    @Test
    @Disabled
    void readOtp() throws Exception {
        try (InputStream is = Otp.class.getResourceAsStream("/otp.bin")) {
            byte[] content = new byte[1024];
            is.read(content);
            Otp otp = Otp.readBytes(content);
            System.out.println("SEEPROM key: 0x" + Hexer.fromBytes(otp.getWiiuSeepromKey()));
            System.out.println("vWii common key: 0x" + Hexer.fromBytes(otp.getVWiiCommonKey()));
            System.out.println("Wii U common key: 0x" + Hexer.fromBytes(otp.getWiiuCommonKey()));
        }
    }
}
