package ru.itmo.wp.web.page;

import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    public static final int[] DX = new int[]{-1, 0, 1, -1};
    public static final int[] DY = new int[]{-1, -1, -1, 0};
    private static State curState;

    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (request.getSession().getAttribute("state") == null) {
            newGame(request, view);
        } else {
            view.put("state", request.getSession().getAttribute("state"));
        }
    }

    private static void onMove(HttpServletRequest request, Map<String, Object> view) {
        makeMove(request, view);
        view.put("state", curState);
        request.getSession().setAttribute("state", curState);
        throw new RedirectException(request.getRequestURI());
    }

    private static void makeMove(HttpServletRequest request, Map<String, Object> view) {
        if (curState.phase != Status.RUNNING) {
            return;
        }
        String position = null;
        for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) {
            if (e.getKey().startsWith("cell_")) {
                position = e.getKey().substring(5);
            }
        }

        if (position != null && position.length() == 2) {
            if (!Character.isDigit(position.charAt(0)) || !Character.isDigit(position.charAt(1))) {
                return;
            }
            int x = Character.getNumericValue(position.charAt(0));
            int y = Character.getNumericValue(position.charAt(1));
            char move = curState.crossesMove ? 'X' : 'O';
            if (isPosition(x, y) && positionClear(x, y)) {
                curState.cells[x][y] = move;
                curState.countMove++;
                curState.crossesMove ^= true;
            }
            curState.phase = getResult(x, y, move);
            if (curState.phase == Status.RUNNING && !hasEmptyCell()) {
                curState.phase = Status.DRAW;
            }
        } else {
            newGame(request, view);
        }
    }

    private static boolean hasEmptyCell() {
        for (int i = 0; i < curState.size; i++) {
            for (int j = 0; j < curState.size; j++) {
                if (positionClear(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean positionClear(int x, int y) {
        return curState.cells[x][y] == null;
    }

    private static Status getResult(int x, int y, char move) {
        for (int i = 0; i < DX.length; i++) {
            int res = getResult(x, y, DX[i], DY[i], move) + getResult(x, y, -DX[i], -DY[i], move) - 1;
            if (res >= 3) {
                return move == 'X' ? Status.WON_X : Status.WON_O;
            }
        }
        return Status.RUNNING;
    }

    private static int getResult(int x, int y, int dx, int dy, Character cell) {
        int cnt = 0;
        while (isPosition(x, y)) {
            if (cell.equals(getCell(x, y))) {
                cnt++;
            } else {
                break;
            }
            x += dx;
            y += dy;
        }
        return cnt;
    }

    private static Character getCell(int x, int y) {
        return curState.cells[x][y];
    }

    private static boolean isPosition(int x, int y) {
        return 0 <= x && x < curState.size && 0 <= y && y < curState.size;
    }

    private static void newGame(HttpServletRequest request, Map<String, Object> view) {
        curState = new State();
        view.put("state", curState);
        //throw new RedirectException(request.getRequestURI());
    }


    public static class State {
        private Status phase;
        private final int size;
        private final Character[][] cells;
        private boolean crossesMove;
        private int countMove;

        public State() {
            countMove = 0;
            crossesMove = true;
            this.phase = Status.RUNNING;
            size = 3;
            this.cells = new Character[size][size];
        }

        public Status getPhase() {
            return phase;
        }

        public int getSize() {
            return size;
        }

        public Character[][] getCells() {
            return cells;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        public int getCountMove() {
            return countMove;
        }
    }

    enum Status {
        RUNNING,
        WON_O,
        WON_X,
        DRAW
    }
}
