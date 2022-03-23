package bodoh69.util;

public class Hexer {
    private Hexer(){}

    public static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void write(byte b, char[] c, int offset) {
        c[offset] = HEX[(b & 0xff) >>> 4];
        c[offset + 1] = HEX[((b << 4) & 0xff) >>> 4];
    }

    public static void write(byte[] bytes, int offset, int count, char[] dest, int start) {
        int destOffset = start;
        for (int i = offset; i < offset + count; i++, destOffset += 2) {
            write(bytes[i], dest, destOffset);
        }
    }

    public static void append(StringBuilder str, byte[] bytes, int offset, int count) {
        for (int i = 0; i < count; i++) {
            str.append(HEX[(bytes[offset + i] & 0xff) >>> 4]);
            str.append(HEX[((bytes[offset + i] << 4) & 0xff) >>> 4]);
        }
    }

    public static String fromBytes(byte[] bytes) {
        char[] dest = new char[bytes.length * 2];
        write(bytes, 0, bytes.length, dest, 0);
        return new String(dest);
    }
}
