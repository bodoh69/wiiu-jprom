package bodoh69.wiiu.prom2;

public enum ConsoleType {
    /** {@code 0x0001}: WUP (Retail/production) */
    WUP(0x0001),
    /** {@code 0x0002}: CAT-R (Test) */
    CAT_R(0x0002),
    /** {@code 0x0003}: CAT-DEV (Debug) */
    CAT_DEV(0x0003),
    /** {@code 0x0004}: EV board */
    EV(0x0004),
    /** {@code 0x0005}: Promotion (Kiosk CAT-I) */
    KIOSK(0x0005),
    /** {@code 0x0006}: OrchestraX */
    ORCHESTRA(0x0006),
    /** {@code 0x0007}: WUIH (Internal retail) */
    WUIH(0x0007),
    /** {@code 0x0008}: WUIH_DEV (Internal test) */
    WUIH_DEV(0x0008),
    /** {@code 0x0009}: CAT_DEV_WUIH (Internal debug) */
    CAT_DEV_WUIH(0x0009),
    UNKNOWN(-1);

    private short id;

    ConsoleType(int id) {
        this.id = (short) id;
    }

    private static final ConsoleType[] types = new ConsoleType[]{
            WUP, CAT_R, CAT_DEV, EV, KIOSK, ORCHESTRA, WUIH, WUIH_DEV, CAT_DEV_WUIH
    };

    public static ConsoleType lookup(byte[] bytes, int offset) {
        short id = (short) ((bytes[offset] & 0xff) << 8
                + (bytes[offset + 1] & 0xff));
        for (ConsoleType board : types) {
            if (board.id == id)
                return board;
        }
        return UNKNOWN;
    }
}
