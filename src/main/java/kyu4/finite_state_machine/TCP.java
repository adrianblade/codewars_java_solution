package kyu4.finite_state_machine;

public class TCP {

    private enum Event {
        APP_PASSIVE_OPEN,
        APP_ACTIVE_OPEN,
        APP_SEND,
        APP_CLOSE,
        APP_TIMEOUT,
        RCV_SYN,
        RCV_ACK,
        RCV_SYN_ACK,
        RCV_FIN,
        RCV_FIN_ACK
    }
    private enum State {
        CLOSED,
        LISTEN,
        SYN_SENT,
        SYN_RCVD,
        ESTABLISHED,
        CLOSE_WAIT,
        LAST_ACK,
        FIN_WAIT_1,
        FIN_WAIT_2,
        CLOSING,
        TIME_WAIT,
        ERROR
    }

    private static State[][] result;

    static {
        result = new State[11][11];
        result[State.CLOSED.ordinal()][Event.APP_PASSIVE_OPEN.ordinal()] = State.LISTEN;
        result[State.CLOSED.ordinal()][Event.APP_ACTIVE_OPEN.ordinal()] = State.SYN_SENT;

        result[State.LISTEN.ordinal()][Event.RCV_SYN.ordinal()] = State.SYN_RCVD;
        result[State.LISTEN.ordinal()][Event.APP_SEND.ordinal()] = State.SYN_SENT;
        result[State.LISTEN.ordinal()][Event.APP_CLOSE.ordinal()] = State.CLOSED;

        result[State.SYN_RCVD.ordinal()][Event.APP_CLOSE.ordinal()] = State.FIN_WAIT_1;
        result[State.SYN_RCVD.ordinal()][Event.RCV_ACK.ordinal()] = State.ESTABLISHED;

        result[State.SYN_SENT.ordinal()][Event.RCV_SYN.ordinal()] = State.SYN_RCVD;
        result[State.SYN_SENT.ordinal()][Event.RCV_SYN_ACK.ordinal()] = State.ESTABLISHED;
        result[State.SYN_SENT.ordinal()][Event.APP_CLOSE.ordinal()] = State.CLOSED;

        result[State.ESTABLISHED.ordinal()][Event.APP_CLOSE.ordinal()] = State.FIN_WAIT_1;
        result[State.ESTABLISHED.ordinal()][Event.RCV_FIN.ordinal()] = State.CLOSE_WAIT;

        result[State.FIN_WAIT_1.ordinal()][Event.RCV_FIN.ordinal()] = State.CLOSING;
        result[State.FIN_WAIT_1.ordinal()][Event.RCV_FIN_ACK.ordinal()] = State.TIME_WAIT;
        result[State.FIN_WAIT_1.ordinal()][Event.RCV_ACK.ordinal()] = State.FIN_WAIT_2;

        result[State.CLOSING.ordinal()][Event.RCV_ACK.ordinal()] = State.TIME_WAIT;

        result[State.FIN_WAIT_2.ordinal()][Event.RCV_FIN.ordinal()] = State.TIME_WAIT;

        result[State.TIME_WAIT.ordinal()][Event.APP_TIMEOUT.ordinal()] = State.CLOSED;

        result[State.CLOSE_WAIT.ordinal()][Event.APP_CLOSE.ordinal()] = State.LAST_ACK;

        result[State.LAST_ACK.ordinal()][Event.RCV_ACK.ordinal()] = State.CLOSED;
    }

    private static State apply(State state, Event event) {
        return result[state.ordinal()][event.ordinal()];
    }

    public static String traverseStates(String[] events) {
        State state = State.CLOSED;
        for (String event : events) {
            state = apply(state, Event.valueOf(event));
            if (state == null) return State.ERROR.name();
        }
        return state.name();
    }
}