package bodoh69.util;

public class Bytes {
    public static final byte[] clone(byte[] bytes) {
        byte[] d = new byte[bytes.length];
        System.arraycopy(bytes, 0, d, 0, d.length);
        return d;
    }
}
