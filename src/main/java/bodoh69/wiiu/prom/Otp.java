package bodoh69.wiiu.prom;

import static java.lang.System.arraycopy;

public class Otp {
    @lombok.Getter(lombok.AccessLevel.PACKAGE)
    private byte[] wiiuSeepromKey = new byte[10];
    @lombok.Getter(lombok.AccessLevel.PACKAGE)
    private byte[] vWiiCommonKey = new byte[10];
    @lombok.Getter(lombok.AccessLevel.PACKAGE)
    private byte[] wiiuCommonKey = new byte[10];

    public static Otp readBytes(byte[] bytes) {
        if (bytes.length != 0x80 * 8)
            throw new IllegalArgumentException(
                    "otp.bin must be 1024 bytes, but got " + bytes.length);
        return readBytes(bytes, 0);
    }

    public static Otp readBytes(byte[] bytes, int offset) {
        if (bytes.length - offset < 0x80 * 8)
            throw new IllegalArgumentException("must have 1024 bytes available"
                    + " but only got " + (bytes.length - offset));
        Otp otp = new Otp();
        arraycopy(bytes, offset + 0x0a0, otp.wiiuSeepromKey, 0, otp.wiiuSeepromKey.length);
        arraycopy(bytes, offset + 0x0d0, otp.vWiiCommonKey,  0, otp.vWiiCommonKey.length);
        arraycopy(bytes, offset + 0x0e0, otp.wiiuCommonKey,  0, otp.wiiuCommonKey.length);
        return otp;
    }
}
