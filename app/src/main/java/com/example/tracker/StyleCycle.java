package com.example.tracker;

import com.mapbox.mapboxsdk.maps.Style;

public class StyleCycle {

    private static final String[] STYLES = new String[] {
            Style.MAPBOX_STREETS,
            Style.OUTDOORS,
            Style.LIGHT,
            Style.DARK,
            Style.SATELLITE_STREETS
    };

    private int index;

    String getNextStyle() {
        index++;
        if (index == STYLES.length) {
            index = 0;
        }
        return getStyle();
    }

    public String getStyle() {
        return STYLES[index];
    }
}
