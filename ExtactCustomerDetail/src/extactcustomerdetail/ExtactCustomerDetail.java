/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extactcustomerdetail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Thanakrit
 */
public class ExtactCustomerDetail {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        try (BufferedReader in = new BufferedReader(new FileReader("repo/test.txt"))) {
//            String line;
//            int i = 0;
//            StringBuilder str = new StringBuilder();
//            while ((line = in.readLine()) != null) {
////                System.out.println((Arrays.toString(line.trim().split(" "))));
//
//                line = line.replaceAll("OTC      1", "OTC1");
//
//                str.append(line);
//
//                String[] linsStr = line.trim().split(" ");
//                if (linsStr.length > 1) {
//                    boolean checkStatus = linsStr[0].equalsIgnoreCase("Tel.")
//                            || linsStr[0].equalsIgnoreCase("Tel") || linsStr[0].equalsIgnoreCase("Term");
//                    if (checkStatus) {
////                        System.out.println(str.toString().trim());
//                        String linetest = seperateLineTab(str.toString());
//                        getlastTab(linetest);
//
////                        System.out.println((Arrays.toString(linreform)));
//                        str = new StringBuilder();
//                    }
//                }
//
//            }
//        }
//    }

    public static String seperateLineTab(String line) {

        String accTxt = "";
        String[] linsStr = line
                .replaceAll("Term", "")
                .replaceAll("CR Limit  :          0.00", "")                
                .trim()
                .split("\\t");

//            System.out.println((linsStr[linsStr.length-7]));
        for (String string : linsStr) {
            if (!string.trim().equalsIgnoreCase("")) {
                accTxt += string.trim() + "\t";
            }

        }

//        System.out.println(accTxt);
        return accTxt;
    }

    public static String getlastTab(String line) {

        String accTxt = "";
        String[] linsStr = line
                .split("\\t");

        System.out.println(linsStr[0] + " " + (linsStr[linsStr.length - 1]));
//        for (String string : linsStr) {
//            if (!string.trim().equalsIgnoreCase("")) {
//                accTxt += string.trim() + "\t";
//            }
//
//        }
//
//        System.out.println(accTxt);
        return accTxt;
    }

    public static String seperateLine(String line) {
        String returnVar = "";
        String accTxt = "";
        String[] linsStr = line
                .replaceAll("Term", "")
                .replaceAll("CR Limit  :          0.00", "")
                .trim()
                .split("\\t");

        System.out.println((Arrays.toString(linsStr)));
//            System.out.println((linsStr[linsStr.length-7]));
        for (String string : linsStr) {
            if (!string.trim().equalsIgnoreCase("")) {
                accTxt += string.trim() + "@";
            }

        }

//        System.out.println(accTxt);
        String[] accTxtArr = accTxt.trim().split("  ");
//        System.out.println((Arrays.toString(accTxtArr)));
        accTxt = "";
        for (String string : accTxtArr) {
            if (!string.trim().equalsIgnoreCase("")) {
                accTxt += string.trim() + "@";
            }
        }
        accTxt = accTxt.replaceAll("Term", "")
                .replaceAll("@:", ":")
                .replaceAll("::", ":")
                .replaceAll(":", "\t")
                .replaceAll("@", "\t")
                .replaceAll("\t\t", "\t");

//        System.out.println(accTxt.replaceAll("@", "\t"));
        return returnVar;
    }
}
