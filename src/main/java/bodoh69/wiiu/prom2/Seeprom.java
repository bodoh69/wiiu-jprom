package bodoh69.wiiu.prom2;

import bodoh69.util.Hexer;

import java.util.zip.CRC32;

import static java.lang.System.arraycopy;

public class Seeprom {
    /** 256 16-bit words */
    private final byte[] data = new byte[256 * 2];

    private Seeprom(byte[] data) {
        arraycopy(data, 0, this.data, 0, this.data.length);
    }

    public byte[] rngSeed() {
        return copy(0x08, 0x012);
    }

    public byte[] wiiuDriveKey() {
        return copy(0x10, 0x080);
    }

    public byte[] wiiuDriveKeyStatus() {
        return copy(0x02, 0x060);
    }

    public byte[] wiiuUsbKeySeed() {
        return copy(0x10, 0x0b0);
    }

    public byte[] wiiuUsbKySeedStatus() {
        return copy (0x02, 0x0c0);
    }

    private byte[] copy(int length, int offset) {
        byte[] d = new byte[length];
        arraycopy(data, offset, d, 0, d.length);
        return d;
    }

    public static void validate(byte[] data) throws IllegalArgumentException {
        StringBuilder err = new StringBuilder();
        if (data.length != 512)
            err.append("\tsize of bytes should be 512 but was " + data.length);
        if ((data[0x192] & 0xff) != 0xaa || (data[0x193] & 0xff) != 0x55) {
            err.append("\tmarker 0xaa55 at offset 0x192 was 0x");
            Hexer.append(err, data, 0x192, 2);
        }
        if ((data[0x1a2] & 0xff) != 0xbb || (data[0x1a3] & 0xff) != 0x66) {
            err.append("\tmarker 0xbb66 at offset 0x1a2 was 0x");
            Hexer.append(err, data, 0x1a2, 2);
        }
        if (true) {
            CRC32 crc32 = new CRC32();
            crc32.update(data, 0x180, 14);
            int val = (int) (crc32.getValue() >>> 16);
            int crc = ((data[0x18e] & 0xff) << 8) + (data[0x18f] & 0xff);
            if (val != crc) {
                err.append("\tCRC32 at offset 0x18e should be 0x");
                Hexer.append(err, data, 0x18e, 2);
                err.append(" but was 0x" + Long.toHexString(val));
            }
        }
        if (err.length() > 0) {
            throw new IllegalArgumentException("SEEPROM validation failed:" +
                    err.toString().replace('\t', '\n'));
        }
    }
}
