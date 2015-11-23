/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SMSNotification;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("notification")
public class SMSNotification {

    @GET
    @Produces("text/plain")
    @Path("{mobileNumber}")
    public String sendSMS(@PathParam("mobileNumber") String mobileNumber) {
        //8. if yes send sms to client confirming addition of product product details, to policy number at premium * (sms)
        Boolean smsSent = false;
        try {
            if (smsSent) {
                return "SMS sent";
            } else {
                return "Error SMS not sent";
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return "Error SMS not sent";
    }
}
