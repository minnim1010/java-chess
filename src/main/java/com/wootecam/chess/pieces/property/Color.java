package com.wootecam.chess.pieces.property;

public enum Color {
    WHITE,
    BLACK,
    NO_COLOR;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
