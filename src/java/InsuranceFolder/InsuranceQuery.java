package InsuranceFolder;

import EANQuery.EANBarcodeQuery;
import Facades.InsuranceFacade;
import PricecheckQuery.PricecheckLookup;
import Utils.Utilities;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;


@Stateless
@Path("/productDetails")
public class InsuranceQuery {
    @EJB
    InsuranceFacade facade;
    
    @GET
    @Produces("text/plain")
    @Path("{barcode}")
    public String getProductDetails(@PathParam("barcode") String barcode) {
        facade = new InsuranceFacade();
        //2. return client product details, price based on upc * (xml)
        //Generate Transaction Reference
        String transactionRef = Utilities.randomGenerator();
        //Query EAN Barcode
        String productName = EANBarcodeQuery.eanLookup(barcode);
        //Query Pricecheck
        String price = PricecheckLookup.getPriceFromPriceCheck(productName);
        //Store price, barcode, transaction ref and product name
        facade.addNewInsurance(transactionRef, productName, price, barcode);
        String productDetails = "{\"price\": \""+price+"\", \"name\": \""+productName+"\", \"reference\": \""+transactionRef+"\"}";
        return productDetails;
    }
    
}
