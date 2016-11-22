package com.labun.test;


import com.labun.surf.IntegralImage;
import com.labun.surf.InterestPoint;
import com.labun.surf.Params;
import com.labun.surf.plugin.IJFacade;
import ij.ImagePlus;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Simple example of SURF in action !
 */
public class SimpleTest {

    private static final boolean SHOW_POINTS_IN_WINDOW = false;

    @Test
    public void test() throws IOException, InvocationTargetException, InterruptedException {

        final ImagePlus imp = getImagePlus();
        IntegralImage intImg = new IntegralImage(imp.getProcessor(), true);

        if (imp == null) {
            throw new NullPointerException("Unable to find image !");
        }

        final Params surfParams = new Params(4, 4, 0.0001f, 2, false, false, false, 1, false);

        final List<InterestPoint> points = IJFacade.detectAndDescribeInterestPoints(intImg, surfParams);

        assertTrue(points.size() > 0);

        if (SHOW_POINTS_IN_WINDOW) {
            IJFacade.drawInterestPoints(imp.getProcessor(), points, surfParams);
            ImagePlus imp2 = new ImagePlus("SURF interest points", imp.getProcessor());
            imp2.show();

            Thread.sleep(20000);
        }
    }

    private ImagePlus getImagePlus() throws IOException {

        // get buffered image
        BufferedImage bimg = ImageIO.read(SimpleTest.class.getResourceAsStream("/belledonne.jpg"));
        ImagePlus imp = new ImagePlus("", bimg);

        // convert it if necessary
        // TODO: Try with black and white image to save memory ?
        if (imp.getProcessor().getBitDepth() != ImagePlus.COLOR_RGB) {
            imp.getProcessor().convertToRGB();
            imp.updateAndDraw();
        }

        return imp;
    }

}
