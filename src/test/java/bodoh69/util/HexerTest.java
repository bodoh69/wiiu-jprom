package bodoh69.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexerTest {

    @Test
    void append() {
        StringBuilder builder = new StringBuilder();
        builder.append("0x");
        Hexer.append(builder,  // ascii bytes are the default
                new byte[]{(byte) 'A', (byte) 'Z', (byte) 'a', (byte) 'z'},
                0, 4);
        assertEquals("0x415a617a", builder.toString());
    }
}
