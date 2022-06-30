package com.murilonerdx.kindof.enums;

public enum Rank {
    OWNER("&c&lOwner "),
    HELPER("&c&lHelper "),
    MEMBER("&c&lMember ");

    private String display;

    Rank(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
