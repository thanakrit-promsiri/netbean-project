/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paliindex;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import paliindex.org.apache.dts.BTree;
import paliindex.org.apache.dts.BTIteratorIF;

/**
 *
 * @author Thanakrit-Promsiri
 */
public class PaliIndex {

    public EntityManager em;
    public BTree<String, Integer> mBTree;
    public int count;

    public PaliIndex() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaliIndexPU");
        em = emf.createEntityManager();
        mBTree = new BTree<>();
        count = 0;
    }

    public static void main(String[] args) {

        PaliIndex paliIndex = new PaliIndex();
        for (int i = 1; i <= 45; i++) {
            List<Object[]> rawResultList = paliIndex.getContent(i);
            paliIndex.insertContents(rawResultList);
            System.out.println(paliIndex.mBTree.size());
        }

        System.out.println("_______");

        paliIndex.em.close();

        BTIteratorIF mIter;
        mIter = new BTIteratorImpl();
        paliIndex.mBTree.list(mIter);
         System.out.println(paliIndex.mBTree.size());
        System.out.println("count : " + paliIndex.count);

    }

    public void insertContents(List<Object[]> rawResultList) {
        String[] contents = prepareContents(rawResultList);
        insert(contents);
    }

    public void insert(String[] contents) {

        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != "") {
                if (mBTree.search(contents[i]) == null) {
                    mBTree.insert(contents[i], 1);
                } else {
                    Integer x = mBTree.search(contents[i]);
                    mBTree.insert(contents[i], x.intValue() + 1);
                }
                count++;
            }
        }
    }

    public List getContent(int volume) {

        Query query;
//        query = em.createNativeQuery("select * from pali_siam where volume = '01'  or volume = '02' or volume = '03'  or volume = '04' or volume = '05'    ");
        query = em.createNativeQuery("select * from pali_siam where volume = " + volume);
        List orders = query.getResultList();

        return orders;
    }

    public String[] prepareContents(List<Object[]> rawResultList) {

        StringBuilder content = new StringBuilder();
        for (Object[] resultElement : rawResultList) {

            content.append(" ");
            content.append((String) resultElement[4]);
        }
        String contentAp = prepareText(content.toString());

        String[] contents = contentAp.split(" ");
//        System.out.println(contents[12935] + " " + contents[12936] + " " + contents[12937] + " " + contents[12938] + " " + contents[12939]);
        return contents;
    }

    public String prepareText(String txt) {
        String retxt;
        retxt = txt.replaceAll("[๐-๙\\]\\[ฯ_#()]", " ").
                replaceAll("[0-9a-zA-Z]", " ").
                replaceAll("\\n", " ").
                replaceAll("- ", "").
                replaceAll("\\t", " ").
                replaceAll("[0-9]", " ").
                replaceAll("--", "").
                replaceAll("\\.", "").
                replaceAll("\\*", "");

        retxt = retxt.replaceAll("-", "")
                .replaceAll("[$%&,/:;<=>?@]", "")
                .replaceAll("[ๆ็่้๊๋์ํ๏๚]", "")
                .replaceAll("[\\{\\|\\}]", "")
                .replaceAll("[`^]", "")
                .replaceAll("\" ", "")
                .replaceAll(" ไ ", "")
                .replaceAll(" ใ ", "")
                .replaceAll("\"", "");

        retxt = retxt.replaceAll("\\s+", " ");
                

        return retxt;
    }

}

class BTIteratorImpl<K extends Comparable, V> implements BTIteratorIF<K, V> {

    private StringBuilder mBuf = new StringBuilder();

    @Override
    public boolean item(K key, V value) {
        mBuf.setLength(0);
        mBuf.append("'").append(key).append("'")
                .append("  |  ")
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
