package bodoh69.wiiu.prom2;

public enum BoardType {
    /**
     * CF (CAFE: Retail/Kiosk)
     */
    CAFE(0x4346),
    /**
     * CT (CAT: Devkit)
     */
    CAT(0x4354),
    /**
     * EV (EV: Evaluation board? EV_Y also exists)
     */
    EV(0x4556),
    ID(0x4944),
    /**
     * IH (WUIH?)
     */
    IH(0x4948),
    UNKNOWN(-1);
    private short id;

    BoardType(int id) {
        this.id = (short) id;
    }

    private static final BoardType[] boards = new BoardType[]{
            CAFE, CAT, EV, ID, IH
    };

    public static BoardType lookup(byte[] bytes, int offset) {
        short id = (short) ((bytes[offset] & 0xff) << 8
                + (bytes[offset + 1] & 0xff));
        for (BoardType board : boards) {
            if (board.id == id)
                return board;
        }
        return UNKNOWN;
    }
}
