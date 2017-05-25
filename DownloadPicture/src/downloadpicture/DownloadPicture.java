/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadpicture;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanakrit-Promsiri
 */
public class DownloadPicture {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        for (int i = 1; i <= 397; i++) {
//        for (int i = 396; i <= 735; i++) {
        for (int i = 1; i <= 63; i++) {
            try {
//                String page = String.format("%03d", i);
//                URL url = new URL("http://www.finearts.go.th/songkhlalibraryhm/images/File-Admin/Ebook/2559/e15/p"+page+".JPG");
//                URL url = new URL("http://www.finearts.go.th/songkhlalibraryhm/images/File-Admin/Ebook/2559/e15-2/p" + page + ".JPG");
//                URL url = new URL("http://www.finearts.go.th/songkhlalibraryhm/images/File-Admin/Ebook/2559/e16/p" + page + ".JPG");
                String page = String.format("%04d", i);
                URL url = new URL("http://www.car.chula.ac.th/rarebook/book/av_00060/files/assets/pages/page" + page + ".swf");

                InputStream in = new BufferedInputStream(url.openStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1 != (n = in.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                in.close();
                byte[] response = out.toByteArray();
//                FileOutputStream fos = new FileOutputStream("D://lalivistara/1/p" + page + ".jpg");
//                FileOutputStream fos = new FileOutputStream("D://lalivistara/2/p" + page + ".jpg");
//                FileOutputStream fos = new FileOutputStream("D://lalivistara/3/p" + page + ".jpg");
 FileOutputStream fos = new FileOutputStream("D://buddhavataro/page" + page + ".swf");
                fos.write(response);
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(DownloadPicture.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
