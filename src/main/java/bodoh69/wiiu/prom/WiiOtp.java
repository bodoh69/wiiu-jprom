package bodoh69.wiiu.prom;

import static java.lang.System.arraycopy;
import static java.util.Objects.requireNonNull;

/**
 * <a href="https://wiiubrew.org/wiki/Hardware/OTP">OTP</a>
 */
@lombok.NoArgsConstructor
public class WiiOtp {
    /** Wii boot1 SHA-1 hash */
    private final byte[] boot1Sha1Hash = new byte[0x14];
    /**  Wii common key */
    private final byte[] commonKey = new byte[0x10];
    /** Wii NG ID */
    private final byte[] ngId = new byte[0x04];
    /** Wii NG private key */
    private final byte[] ngPrivateKey = new byte[0x1C];
    /** Wii NAND HMAC (overlaps with NG private key) */
    private final byte[] nandHmac = new byte[0x14];
    /** Wii NAND key */
    private final byte[] nandKey = new byte[0x10];
    /** Wii RNG key */
    private final byte[] rngKey = new byte[0x10];
    /** Unknown (padding) */
    private final byte[] unknown = new byte[0x08];

    public WiiOtp(byte[] boot1Hash, byte[] commonKey,
                  byte[] id, byte[] privateKey,
                  byte[] nandHmac, byte[] nandKey,
                  byte[] rngKey, byte[] unknown) {
        requireAndCopy(this.boot1Sha1Hash, boot1Hash, "boot1Hash");
        requireAndCopy(this.commonKey, commonKey, "commonKey");
        requireAndCopy(this.ngId, id, "ngId");
        requireAndCopy(this.ngPrivateKey, privateKey, "ngPrivateKey");
        requireAndCopy(this.nandHmac, nandHmac, "nandHmac");
        requireAndCopy(this.nandKey, nandKey, "nandKey");
        requireAndCopy(this.rngKey, rngKey, "rngKey");
        // requireAndCopy(this.unknown, unknown, "unknown");
        if (unknown != null && unknown.length >= this.unknown.length)
            System.arraycopy(unknown, 0, this.unknown, 0, this.unknown.length);
    }

    public static WiiOtp fromBytes(byte[] bytes) {
        return fromBytes(bytes, 0);
    }

    public static WiiOtp fromBytes(byte[] bytes, int offset) {
        if (bytes.length < (offset + 0x80))
            throw new IndexOutOfBoundsException(
                    bytes.length + " is < " + (offset + 0x80));
        WiiOtp otp = new WiiOtp();
        offset = copyAndOffset(bytes, offset, otp.boot1Sha1Hash);
        offset = copyAndOffset(bytes, offset, otp.commonKey);
        offset = copyAndOffset(bytes, offset, otp.ngId);
        offset = copyAndOffset(bytes, offset, otp.ngPrivateKey);
        offset = copyAndOffset(bytes, offset, otp.nandHmac);
        offset = copyAndOffset(bytes, offset, otp.nandKey);
        offset = copyAndOffset(bytes, offset, otp.rngKey);
        /*----*/ copyAndOffset(bytes, offset, otp.unknown);
        return otp;
    }

    static int copyAndOffset(byte[] src, int offset, byte[] dest) {
        arraycopy(src, offset, dest, 0, dest.length);
        return offset + dest.length;
    }

    static void requireAndCopy(byte[] dest, byte[] src, String name) {
        requireNonNull(src, name);
        if (dest.length != src.length)
            throw new IllegalArgumentException(name + " length should be "
                    + dest.length + " but was " + src.length);
        arraycopy(src, 0, dest, 0, dest.length);
    }
}
