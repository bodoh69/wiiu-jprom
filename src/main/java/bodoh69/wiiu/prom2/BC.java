package bodoh69.wiiu.prom2;

import bodoh69.util.Bytes;

/**
 * BoardConfig
 */
public class BC {

    public static final int SEEPROM_START_OFFSET = 0x02c;
    public static final int SEEPROM_END_EXCLUSIVE = 0x080;
    private byte[] data;
    private int offset;

    BC() {
        this.data = new byte[SEEPROM_END_EXCLUSIVE - SEEPROM_START_OFFSET];
        this.offset = 0;
    }

    public BC(byte[] data) {
        if (data.length != SEEPROM_END_EXCLUSIVE - SEEPROM_START_OFFSET)
            throw new IllegalArgumentException("data length must be "
                    + (SEEPROM_END_EXCLUSIVE - SEEPROM_START_OFFSET)
                    + " but was " + data.length);
        Bytes.clone(data);
        this.offset = 0;
    }

    BC(byte[] data, int offset) {
        if (data.length - offset < SEEPROM_END_EXCLUSIVE - SEEPROM_START_OFFSET)
            throw new IllegalArgumentException("data length must be at least"
                    + (SEEPROM_END_EXCLUSIVE - SEEPROM_START_OFFSET)
                    + " but was " + data.length);
        this.data = data;
        this.offset = offset;
    }


    public BoardType getBoardType() {
        return BoardType.lookup(data, offset + 0x042);
    }
}
