package quote;

import Entities.Insurance;
import Facades.InsuranceFacade;
import Utils.Utilities;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/insuranceQuote")
public class InsuranceQuote {
    @EJB
    InsuranceFacade facade;
    
    @GET
    @Produces("text/plain")
    @Path("{referenceNumber}")
    public String getQuote(@PathParam("referenceNumber") String referenceNumber) {
        //5. return client product details price, premuim, quote number, policy number, product image * (xml)
        String premium = calcQuote(referenceNumber);
        String quoteNumber = Utilities.randomGenerator();

        Insurance insuredItem = facade.getTransactionFromRef(referenceNumber);
        facade.updateInsurance(insuredItem, quoteNumber, calcQuote(insuredItem.getProductPrice()));
        
        //store quote, policy number, product image, premium
        String productDetails = "{\"premium\": \""+premium+"\", \"quote_number\": \""+quoteNumber+"\"}";
        return productDetails;
    }
    
    public String calcQuote(String price) {
        //Lookup original transaction using reference
        String quote = "";
        //remove . and , and R
        price = price.replace("R", "");
        price = price.replace(",", "");
        price = price.substring(0, price.indexOf("."));
        return String.valueOf(Integer.valueOf(price) / 70);
    }
    
}
