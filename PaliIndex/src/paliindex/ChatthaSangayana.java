/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paliindex;

import com.wellebee.sanskrit.Sanscript;
import java.io.File;
import paliindex.org.apache.dts.BTree;
import paliindex.org.apache.dts.BTIteratorIF;
import paliindex.xmlparser.FileUtil;

/**
 *
 * @author Thanakrit-Promsiri
 */
public class ChatthaSangayana {

    public BTree<String, Integer> mBTree;
    public int count;
    public FileUtil fileUtil = new FileUtil();

    public ChatthaSangayana() {
        mBTree = new BTree<>();
        fileUtil = new FileUtil();
        count = 0;
    }

    public static void main(String[] args) {

        File file = new File("tipitaka");
        String[] list = file.list();
        ChatthaSangayana tipitaka = new ChatthaSangayana();

        for (String filename : list) {
            String content = tipitaka.getContent(filename);
            tipitaka.insertContents(content, filename);
            System.out.println(tipitaka.mBTree.size());
            System.out.println(filename);
//            break;
        }

        BTIteratorIF mIter;
        mIter = new BTIteratorImpl2();
        tipitaka.mBTree.list(mIter);
        System.out.println(tipitaka.mBTree.size());
        System.out.println("count : " + tipitaka.count);

//        PaliIndex paliIndex = new PaliIndex();
//        for (int i = 1; i <= 45; i++) {
//            List<Object[]> rawResultList = paliIndex.getContent(i);
//            paliIndex.insertContents(rawResultList);
//            System.out.println(paliIndex.mBTree.size());
//        }
//
//        System.out.println("_______");
//
//        paliIndex.em.close();
//
//        BTIteratorIF mIter;
//        mIter = new BTIteratorImpl();
//        paliIndex.mBTree.list(mIter);
//        System.out.println(paliIndex.mBTree.size());
//        System.out.println("count : " + paliIndex.count);
    }

    public void insertContents(String rawResultList,String filename) {
        String[] contents = prepareContents(rawResultList);
        insert(contents,filename);
    }

    public void insert(String[] contents, String filename) {

        for (int i = 0; i < contents.length; i++) {
            if (!"".equals(contents[i])) {
                if (mBTree.search(contents[i]) == null) {
                    mBTree.insert(contents[i], 1);
                } else {
                    Integer x = mBTree.search(contents[i]);
                    mBTree.insert(contents[i], x.intValue() + 1);
//                    mBTree.insert(contents[i], filename);
                }
                count++;

            
            }
        }
    }

    public String getContent(String filename) {
        return fileUtil.getFile("tipitaka/" + filename, "UTF8");
    }

    public String[] prepareContents(String content) {

//        StringBuilder content = new StringBuilder();
//        for (Object[] resultElement : rawResultList) {
//
//            content.append(" ");
//            content.append((String) resultElement[4]);
//        }
        String contentAp = prepareText(content);

        String[] contents = contentAp.split(" ");
//        System.out.println(contents[12935] + " " + contents[12936] + " " + contents[12937] + " " + contents[12938] + " " + contents[12939]);
        return contents;
    }

    public String prepareText(String txt) {
        String retxt;

        retxt = txt
                .replaceAll("[०-९]", " ")
                .replaceAll("\\n", " ")
                .replaceAll("\\t", " ")
                .replaceAll("[\\]\\[]", " ")
                .replaceAll("[()]", " ")
                .replaceAll("[-–,+?!;]", " ")
                .replaceAll("\\‘", " ")
                .replaceAll("…", " ")
                .replaceAll("॰", " ")
                .replaceAll("’’", " ")
                .replaceAll("[\\.॥।§]", " ")
                .replaceAll("`", " ")
                .replaceAll("\\’", " ");

//        retxt = txt.replaceAll("[๐-๙\\]\\[ฯ_#()]", " ").
//                replaceAll("[0-9a-zA-Z]", " ").
//                replaceAll("\\n", " ").
//                replaceAll("- ", "").
//                replaceAll("\\t", " ").
//                replaceAll("[0-9]", " ").
//                replaceAll("--", "").
//                replaceAll("\\.", "").
//                replaceAll("\\*", "");
//
//        retxt = retxt.replaceAll("-", "")
//                .replaceAll("[$%&,/:;<=>?@]", "")
//                .replaceAll("[ๆ็่้๊๋์ํ๏๚]", "")
//                .replaceAll("[\\{\\|\\}]", "")
//                .replaceAll("[`^]", "")
//                .replaceAll("\" ", "")
//                .replaceAll(" ไ ", "")
//                .replaceAll(" ใ ", "")
//                .replaceAll("\"", "");
//
        retxt = retxt.replaceAll("\\s+", " ");
        return retxt;
    }

}

class BTIteratorImpl2<K extends Comparable, V> implements BTIteratorIF<K, V> {

    private StringBuilder mBuf = new StringBuilder();

    @Override
    public boolean item(K key, V value) {
        Sanscript sanscript = new Sanscript();
        mBuf.setLength(0);
        mBuf.append(sanscript.t((String) key, "devanagari", "iast"))
                .append(",")
                .append(value);
        System.out.println(mBuf.toString());
        /*
            if (key.compareTo(30) == 0) {
                return false;
            }
         */
        return true;
    }
}
