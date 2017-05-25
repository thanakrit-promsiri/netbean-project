/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadpicture;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author Thanakrit-Promsiri
 */
public class Swf2image {

//    public static void main(String[] args) {
//
//        String ip = "127.0.0.1";
//        String pingResult = "";
//
//        String pingCmd = "dir";
//        try {
//
//            String command = "cmd /c start cmd.exe";
//            Process child = Runtime.getRuntime().exec(command);
//
//            // Get output stream to write from it
//            OutputStream out = child.getOutputStream();
//
//            out.write("cd C:/ /r/n".getBytes());
//            out.flush();
//            out.write("dir /r/n".getBytes());
//            out.close();
//
////    ------
//            Runtime r = Runtime.getRuntime();
//            Process p = r.exec(pingCmd);
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println(inputLine);
//                pingResult += inputLine;
//            }
//            in.close();
//
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }
    public static void main(String args[]) throws IOException {
        String executable = "swfextract.exe";
        String argument1 = " ";
        File workingDirectory = new File("C:\\Program Files (x86)\\SWFTools");

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", executable, argument1);
        pb.directory(workingDirectory);
        pb.start();
     
    }
}


