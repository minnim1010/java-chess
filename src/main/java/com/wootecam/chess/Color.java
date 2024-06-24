package com.wootecam.chess;

public enum Color {
    WHITE("white"),
    BLACK("black");

    public final String displayName;

    Color(String displayName) {
        this.displayName = displayName;
    }
}