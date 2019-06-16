package kyu4.finite_state_machine;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

// TODO: Replace examples and use TDD development by writing your own tests

public class TCPTest {

    @Test
    public void sampleTests() {
        assertEquals("CLOSE_WAIT",   TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN"}));
        assertEquals("ESTABLISHED",  TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN", "RCV_SYN","RCV_ACK"}));
        assertEquals("LAST_ACK",     TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN","APP_CLOSE"}));
        assertEquals("SYN_SENT",     TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN"}));
        assertEquals("ERROR",        TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","RCV_SYN","RCV_ACK","APP_CLOSE","APP_SEND"}));
    }


    @Test
    public void sampleTests1() {
        assertEquals("CLOSE_WAIT",   TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN"}));
    }

    @Test
    public void sampleTests2() {
        assertEquals("ESTABLISHED",  TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN", "RCV_SYN","RCV_ACK"}));
    }

    @Test
    public void sampleTests3() {
        assertEquals("LAST_ACK",     TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN","APP_CLOSE"}));
    }

    @Test
    public void sampleTests4() {
        assertEquals("SYN_SENT",     TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN"}));
    }

    @Test
    public void sampleTests5() {
        assertEquals("ERROR",        TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","RCV_SYN","RCV_ACK","APP_CLOSE","APP_SEND"}));
    }


}
