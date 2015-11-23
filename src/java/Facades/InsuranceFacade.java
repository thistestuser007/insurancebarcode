/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entities.Insurance;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author Denver
 */
@Stateless
public class InsuranceFacade extends AbstractFacade<Insurance> {

    @PersistenceContext(unitName = "StellrInsuranceWebServicePU")
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InsuranceFacade() {
        super(Insurance.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addNewInsurance(String transactionReference, String productName, String productPrice, String barCode) {
        try {
            utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            utx.begin();
            Insurance _insure = new Insurance();
            _insure.setTransactionReference(transactionReference);
            _insure.setProductName(productName);
            _insure.setProductPrice(productPrice);
            _insure.setBarcode(barCode);
            _insure.setUserName("");
            _insure.setPremium("");
            _insure.setQuoteNumber("");
            _insure.setIdInsurance(Integer.MIN_VALUE);
            _insure.setProductImage(null);
            utx.commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateInsurance(Insurance insuranceReq, String quote, String premium) {
        try {
            utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            utx.begin();
            insuranceReq.setPremium(premium);
            insuranceReq.setQuoteNumber(quote);
            getEntityManager().detach(insuranceReq);
            getEntityManager().merge(insuranceReq);
            utx.commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public Insurance getTransactionFromRef(String referenceNumber) {
        try {
            List<Insurance> returnedList = (List<Insurance>) getEntityManager().createNamedQuery("Insurance.findByTransactionReference").setParameter("transactionReference", referenceNumber).getResultList();
            if (returnedList.size() >= 1) {
                return returnedList.get(0);
            } else {
                System.out.println("No transactions found.");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
