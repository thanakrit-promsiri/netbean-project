/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paliindex.xmlparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Thanakrit.P
 */
public class FileUtil {

    public String getFile(String fileName, String charset) {
        StringBuilder strBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),
                charset))) {

            String str;
            while ((str = in.readLine()) != null) {
                strBuilder.append(str).append("\n");
            }
        } catch (IOException e) {

        }

        return strBuilder.toString();
    }

    public List<String> getListLine(String fileName) {

        List<String> result = new ArrayList<>();
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try (Scanner scanner = new Scanner(file, "UTF-8")) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.add(line);
            }
            scanner.close();
        } catch (IOException e) {

        }

        return result;
    }

}
