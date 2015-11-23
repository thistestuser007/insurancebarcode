package EANQuery;

import java.io.*;
import java.net.*;
import java.util.regex.*;

public final class EANBarcodeQuery {

    public static String eanLookup(String ean) {
        try {
            String productName = "";
            URL url = new URL("http://api.ean-search.org/api?token=dnai770186" + "&op=barcode-lookup&ean=" + ean);
            InputStream is = url.openStream();
            int ptr = 0;
            StringBuffer apiResult = new StringBuffer();
            while ((ptr = is.read()) != -1) {
                apiResult.append((char) ptr);
            }
            Pattern p = Pattern.compile("<name>(.*)</name>");
            Matcher m = p.matcher(apiResult);
            if (m.find()) {
                productName = m.group(1);
                System.out.println("---------product name: " + productName);
            }
            return productName;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
