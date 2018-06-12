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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanakrit
 */
public class OtcTransform {

    public void run() {
        List<String> listLine = this.read();
        int i = 1;
        int j = 1;
        StringBuilder str = new StringBuilder();
        String resultz = "SEQ"
                + "\t" + "CODE"
                + "\t" + "NAME"
                + "\t" + "SALESMAN"
                + "\t" + "CUSTOMER_TYPE"
                + "\t" + "TERR"
                + "\t" + "CUSTOMER_CONTACT"
                + "\t" + "ADDRESS_1"
                + "\t" + "ADDRESS_2"
                + "\t" + "DISTRICT"
                + "\t" + "PROVINCE"
                 + "\t" + "POSTCODE"
                 + "\t" + "CREDIT"
                 + "\t" + "TEL";
        

        System.out.println(resultz);
        for (String line : listLine) {

//            if (!this.conditionExcept(line)) {
////                System.out.println(i + " " + line);
//                str.append(line.trim().concat("\t@"));
//                if (i == 3) {
//                    System.out.println(str.toString());
//                    String[] lineArr = str.toString().trim().split("\t");
////                    System.out.println((Arrays.toString(lineArr)));
////                     System.out.println(lineArr.length);
//                    str = new StringBuilder();
//                    i = 0;
//                }
//                i++;
//            }
            if (!this.conditionCSVExcept(line)) {
//                System.out.println(i + " " + line);
                str.append(line.trim().concat("\t@"));
                if (i == 5) {
//                    System.out.println(str.toString());
                    String[] lineArr = str.toString().trim().split("\t");
//                    System.out.println(Arrays.toString(lineArr));

//                    System.out.println();
//                    csv5Rec(lineArr[4]);
                    String result = String.format("%05d", j)
                            + "\t" + this.csv1Rec(lineArr[0])
                            + "\t" + this.csv2Rec(lineArr[1])
                            + "\t" + this.csv3Rec(lineArr[2])
                            + "\t" + this.csv4Rec(lineArr[3])
                            + "\t" + this.csv5Rec(lineArr[4]);
                    System.out.println(result);
                    str = new StringBuilder();
                    i = 0;
                    j++;
                }
                i++;
            }
        }

    }

    public String readReplace(String line) {
        return line.replaceAll("\"", " ")
                .replaceAll(" ร้าน", "      ร้าน")
                .replaceAll(" ร้าน", "      ร้าน")// html
                .replaceAll(" คุณ", "      คุณ")
                .replaceAll(" คุณ", "      คุณ")// html
                .replaceAll(" นาย", "      นาย")
                .replaceAll(" Khun", "      Khun")
                .replaceAll(" นาวสาว", "      นาวสาว")
                .replaceAll("  นาวสาว", "      นาวสาว")// html
                .replaceAll(" โรงพยาบาล", "      โรงพยาบาล")
                .replaceAll(" หจก.", "      หจก.")
                .replaceAll(" บริษัท", "      บริษัท")
                .replaceAll(" หสม.", "      หสม.");
    }

    public List<String> read() {
        List<String> listLine = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("repo/csv.txt"))) {
            String line;
            StringBuilder str = new StringBuilder();
            while ((line = in.readLine()) != null) {
                listLine.add(readReplace(line));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OtcTransform.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OtcTransform.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listLine;
    }

    public boolean conditionExcept(String line) {
        String bar = "------------------------------------------------------------------------------------------------------------------------------------";
        String custCdTitle = "Cus-CodePrename/CustomerNameSalesmanCredittype-Disc.CRLimit";
        String addressTitle = "Address(day)PriceA/CNo.ShipVia";
        String phoneTitle = "PhoneNo.ContactPaymentTerm";
        String territoryTitle = "Territory:OTCCustomers";
        String customerTitle = "CustomerType:RegularCustomer";
        String[] lineArr = line.trim().split(" ");
        boolean cLength = lineArr.length > 0;
        boolean cP = lineArr[0].equalsIgnoreCase("Paradigm");
        boolean cD = lineArr[0].equalsIgnoreCase("Date");
        boolean cC = line.trim().equalsIgnoreCase("Customer Information by Territory");
        boolean cB = line.trim().equalsIgnoreCase(bar);
        boolean cCT = line.trim().replaceAll(" ", "").equalsIgnoreCase(custCdTitle);
        boolean cAT = line.trim().replaceAll(" ", "").equalsIgnoreCase(addressTitle);
        boolean cph = line.trim().replaceAll(" ", "").equalsIgnoreCase(phoneTitle);
        boolean cte = line.trim().replaceAll(" ", "").equalsIgnoreCase(territoryTitle);
        boolean ccu = line.trim().replaceAll(" ", "").equalsIgnoreCase(customerTitle);

        boolean cct = line.trim().replaceAll(" ", "").equalsIgnoreCase("CustomerType:CompanyEmployees");
        boolean cctt = line.trim().replaceAll(" ", "").equalsIgnoreCase("CustomerType:TemporaryCustomer");
        boolean cpn = line.trim().equalsIgnoreCase("พนง.ขาย รสรินทร์ 087-678-0444");

        return cLength && (cP || cD || cC || cB || cCT || cAT || cph || cte || ccu || cct || cctt || cpn);
    }

    public boolean conditionCSVExcept(String line) {

        line = line.replaceAll("\"", "");
        String bar = "------------------------------------------------------------------------------------------------------------------------------------";
        String a = "CustomerInformationReportbyType";
        String b = "CustomerToไฮกี่เภสัช";
        String c = "TerritoryOTCToOTC";
        String d = "SalesmanToSALE01";
        String e = "--------------------------------------------------------------------------------------------------------------------------";
        String f = "CodePrename/CustomerNameSalesmanTerrPrictTypeDisc.";
        String g = "Type:TemporaryCustomer";
        String h = "Type:RegularCustomer";
        String i = "Type:CompanyEmployees";

        String[] lineArr = line.trim().split(" ");
        boolean cLength = lineArr.length > 0;
        boolean blank = line.trim().equalsIgnoreCase("");
        boolean arrA = lineArr[0].trim().equalsIgnoreCase("Paradigm");
        boolean arrB = lineArr[0].trim().equalsIgnoreCase("Customer");
        boolean arrc = lineArr[0].trim().equalsIgnoreCase("Type");

        boolean cD = lineArr[0].equalsIgnoreCase("Date");
        boolean cC = line.trim().equalsIgnoreCase("Customer Information by Territory");
        boolean cB = line.trim().equalsIgnoreCase(bar);
        boolean ab = line.trim().replaceAll(" ", "").equalsIgnoreCase(a);
        boolean bb = line.trim().replaceAll(" ", "").equalsIgnoreCase(b);
        boolean cb = line.trim().replaceAll(" ", "").equalsIgnoreCase(c);
        boolean db = line.trim().replaceAll(" ", "").equalsIgnoreCase(d);
        boolean eb = line.trim().replaceAll(" ", "").equalsIgnoreCase(e);
        boolean fb = line.trim().replaceAll(" ", "").equalsIgnoreCase(f);
        boolean gb = line.trim().replaceAll(" ", "").equalsIgnoreCase(g);
        boolean hb = line.trim().replaceAll(" ", "").equalsIgnoreCase(h);
        boolean ib = line.trim().replaceAll(" ", "").equalsIgnoreCase(i);
//        boolean ccu = line.trim().replaceAll(" ", "").equalsIgnoreCase(e);

        boolean cct = line.trim().replaceAll(" ", "").equalsIgnoreCase("CustomerType:CompanyEmployees");
        boolean cctt = line.trim().replaceAll(" ", "").equalsIgnoreCase("CustomerType:TemporaryCustomer");
        boolean cpn = line.trim().equalsIgnoreCase("พนง.ขาย รสรินทร์ 087-678-0444");

        return cLength && (blank || arrA || arrB || arrc || cD || cC || cB || ab || bb || cb || db || eb || fb || gb || hb || ib || cct || cctt || cpn);
    }

    public String csv1Rec(String rec1) {
        String[] lineArr = rec1.split("                   ");

//        if (lineArr.length == 1) {
////            System.out.println(Arrays.toString(lineArr));
//        } else {
////            System.out.println(Arrays.toString(lineArr));
//        }
        String lineArr0 = csv1RecFirst(lineArr[0]);
        String lineArr1 = csv1RecLast(lineArr[lineArr.length - 1]);
        String result = lineArr0.concat("\t").concat(lineArr1);

        return result;
    }

    public String csv1RecFirst(String lineArr0) {
        String[] col1 = lineArr0.trim().split("   ");
        String acc = "";
        for (int i = 1; i < col1.length; i++) {
            acc += col1[i];
        }
//        System.out.println(Arrays.toString(col1));
        return col1[0].concat("\t").concat(acc.trim());
    }

    public String csv1RecLast(String lineArr1) {
        String[] col2 = replaceSpace(lineArr1.trim()).split(" ");
        if (col2.length == 2) {
            lineArr1 = "OTC     " + lineArr1.trim();
        }

        String[] reform = replaceSpace(lineArr1.trim()).split(" ");
        return reform[0].concat("\t").concat(reform[1]).concat("\t").concat(reform[2]); //cut array only 3 part

    }

    public String csv2Rec(String rec2) {
        String[] lineArr = rec2.split("Contact");
//        System.out.println(Arrays.toString(lineArr));
//        System.out.println(lineArr.length);

        String lineArr0 = lineArr[0]
                .replaceAll("@Address", "")
                .replaceAll(":", "")
                .trim();
        if (lineArr0.equalsIgnoreCase("")) {
            lineArr0 = "UNKNOWN";
        }
        String lineArr1 = lineArr[lineArr.length - 1]
                .replaceAll(":", "")
                .trim();

        if (lineArr1.equalsIgnoreCase("")) {
            lineArr1 = "UNKNOWN";
        }
        String result = lineArr1.concat("\t").concat(lineArr0);

        return result;
    }

    public String csv3Rec(String rec3) {

        rec3 = rec3.replaceAll("A/C No.", "")
                .replaceAll(":", "")
                .replaceAll("114110", "")
                .replaceAll("Ship-Via", "")
                .replaceAll(" ", " ");//html

        String[] lineArr = rec3.split(" อ.");
        if (lineArr.length == 2) {
            lineArr[1] = "อ." + lineArr[1].replaceAll("\\..", ".");
        }

        if (lineArr.length != 2) {
            lineArr = rec3.split(" เขต");
            if (lineArr.length == 2) {
                lineArr[1] = "เขต" + lineArr[1];
            }
        }

        String lineArr0 = "";
        String lineArr1 = "";
        if (lineArr.length > 1) {
            lineArr0 = lineArr[0]
                    .replaceAll("@", "")
                    .trim();
            if (lineArr0.equalsIgnoreCase("")) {
                lineArr0 = "UNKNOWN";
            }

            lineArr1 = lineArr[1]
                    .replaceAll("@", "")
                    .trim();
            if (lineArr1.equalsIgnoreCase("")) {
                lineArr1 = "UNKNOWN";
            }
        } else {
            lineArr0 = lineArr[0]
                    .replaceAll("@", "")
                    .trim();
            if (lineArr0.equalsIgnoreCase("")) {
                lineArr0 = "UNKNOWN";
            }
            lineArr1 = "UNKNOWN";
        }

//        System.out.println(lineArr.length);
        String result = lineArr0.concat("\t").concat(lineArr1);
//        System.out.println(result);
        return result;
    }

    public String csv4Rec(String rec4) {
        String[] lineArr = rec4.split("Credit");

//        System.out.println(Arrays.toString(lineArr[0].trim().split("   ")));
        String lineArr0 = "";
        String lineArr0_1 = "";
        String lineArr0_2 = "";
        String[] sublineArr0 = lineArr[0].trim().split("   ");
//        System.out.println(Arrays.toString(sublineArr0));
        if (sublineArr0.length > 1) {
            lineArr0_1 = sublineArr0[0]
                    .replaceAll("@", "")
                    .replaceAll("จ.", "")
                    .replaceAll(":", "")
                    .replaceAll(" ", " ")
                    .trim();
            if (lineArr0_1.equalsIgnoreCase("")) {
                lineArr0_1 = "UNKNOWN";
            }

            lineArr0_2 = sublineArr0[sublineArr0.length - 1]
                    .replaceAll("@", "")
                    .replaceAll(" ", " ")
                    .trim();
            if (lineArr0_2.equalsIgnoreCase("")) {
                lineArr0_2 = "UNKNOWN";
            }
        } else {
            lineArr0_1 = sublineArr0[0]
                    .replaceAll("@", "")
                    .replaceAll("จ.", "")
                    .replaceAll(":", "")
                    .replaceAll(" ", " ")
                    .trim();
            if (lineArr0_1.equalsIgnoreCase("")) {
                lineArr0_1 = "UNKNOWN";
            }
            lineArr0_2 = "UNKNOWN";
        }
        lineArr0 = (lineArr0_1 + "\t" + lineArr0_2);

        String lineArr1 = lineArr[lineArr.length - 1] // Credit
                .replaceAll("CR Limit", "")
                .replaceAll(":", "")
                .replaceAll("0.00", "")
                .trim();

        if (lineArr1.equalsIgnoreCase("")) {
            lineArr1 = "UNKNOWN";
        }
//        System.out.println(lineArr1);
        String result = lineArr0.concat("\t").concat(lineArr1);
//        System.out.println(result);

        return result;
    }

    public String csv5Rec(String rec5) {
//        System.out.println(rec5);

        String[] lineArr = rec5.split("Term");
//        System.out.println(Arrays.toString(lineArr));
        String lineArr0 = "";
        lineArr0 = lineArr[0]
                .replaceAll("@", "")
                .replaceAll("Tel.", "")
                .replaceAll(":", "")
                .trim();
        if (lineArr0.equalsIgnoreCase("")) {
            lineArr0 = "UNKNOWN";
        }
//        System.out.println(lineArr0);
        return lineArr0;
    }

    public String replaceSpace(String str) {

        return str
                .replaceAll("\"", "")
                .trim()
                .replaceAll("  ", " ")
                .replaceAll("  ", " ")
                .replaceAll("  ", " ")
                .replaceAll("  ", " ")
                .replaceAll("  ", " ")
                .replaceAll("  ", " ");
    }

}
