package src.main.boundary.utility;

import java.awt.*;

public class ColorUtility {
    public static Color darken(Color color, double percentage) {
        float[] hsbArray = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbArray);
        hsbArray[2] -= (float) (hsbArray[2]*percentage/100);

        return Color.getHSBColor(hsbArray[0], hsbArray[1], hsbArray[2]);
    }
}
