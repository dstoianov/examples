package com.tipsandtricks.compare.images;

import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class CompareImages {


    @Test
    public void checkImages() throws IOException {
        String dir = System.getProperty("user.dir") + "/tips-and-tricks/src/test/java/com/tipsandtricks/CompareImages/";
        String i1 = dir + "SignInEN.png";
        String i2 = dir + "SignInUA.png";
        String s = compareImage(i1, i2);
        System.out.println(s);
    }

    public String compareImage(String img1, String img2) throws IOException {

        System.out.println("Comparing Images......");
        boolean ret = true;
        String strMsg = null;
        try {
            Raster ras1 = ImageIO.read(new File(img1)).getData();
            Raster ras2 = ImageIO.read(new File(img2)).getData();

            //Comparing the the two images for number of bands,width & height.

            if (ras1.getNumBands() != ras2.getNumBands()
                    || ras1.getWidth() != ras2.getWidth()
                    || ras1.getHeight() != ras2.getHeight()) {
                ret = false;
            } else {
                // Once the band ,width & height matches, comparing the images.

                search:
                for (int i = 0; i < ras1.getNumBands(); ++i) {
                    for (int x = 0; x < ras1.getWidth(); ++x) {
                        for (int y = 0; y < ras1.getHeight(); ++y) {
                            if (ras1.getSample(x, y, i) != ras2.getSample(x, y, i)) {
                                // If one of the result is false setting the result as false and breaking the loop.
                                ret = false;
                                break search;
                            }
                        }
                    }
                }
            }
            if (ret == true) {
                strMsg = "Images are matched";
            } else {
                strMsg = "Images are not matched";
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return strMsg;
    }
}
