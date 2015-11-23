/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Denver
 */
@Entity
@Table(name = "insurance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insurance.findAll", query = "SELECT i FROM Insurance i"),
    @NamedQuery(name = "Insurance.findByIdInsurance", query = "SELECT i FROM Insurance i WHERE i.idInsurance = :idInsurance"),
    @NamedQuery(name = "Insurance.findByBarcode", query = "SELECT i FROM Insurance i WHERE i.barcode = :barcode"),
    @NamedQuery(name = "Insurance.findByProductName", query = "SELECT i FROM Insurance i WHERE i.productName = :productName"),
    @NamedQuery(name = "Insurance.findByProductPrice", query = "SELECT i FROM Insurance i WHERE i.productPrice = :productPrice"),
    @NamedQuery(name = "Insurance.findByQuoteNumber", query = "SELECT i FROM Insurance i WHERE i.quoteNumber = :quoteNumber"),
    @NamedQuery(name = "Insurance.findByPremium", query = "SELECT i FROM Insurance i WHERE i.premium = :premium"),
    @NamedQuery(name = "Insurance.findByTransactionReference", query = "SELECT i FROM Insurance i WHERE i.transactionReference = :transactionReference"),
    @NamedQuery(name = "Insurance.findByUserName", query = "SELECT i FROM Insurance i WHERE i.userName = :userName")})
public class Insurance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idInsurance")
    private Integer idInsurance;
    @Size(max = 100)
    @Column(name = "barcode")
    private String barcode;
    @Size(max = 255)
    @Column(name = "productName")
    private String productName;
    @Size(max = 13)
    @Column(name = "productPrice")
    private String productPrice;
    @Size(max = 100)
    @Column(name = "quoteNumber")
    private String quoteNumber;
    @Size(max = 13)
    @Column(name = "premium")
    private String premium;
    @Size(max = 100)
    @Column(name = "transactionReference")
    private String transactionReference;
    @Lob
    @Column(name = "productImage")
    private byte[] productImage;
    @Size(max = 100)
    @Column(name = "userName")
    private String userName;

    public Insurance() {
    }

    public Insurance(Integer idInsurance) {
        this.idInsurance = idInsurance;
    }

    public Integer getIdInsurance() {
        return idInsurance;
    }

    public void setIdInsurance(Integer idInsurance) {
        this.idInsurance = idInsurance;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsurance != null ? idInsurance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insurance)) {
            return false;
        }
        Insurance other = (Insurance) object;
        if ((this.idInsurance == null && other.idInsurance != null) || (this.idInsurance != null && !this.idInsurance.equals(other.idInsurance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Insurance[ idInsurance=" + idInsurance + " ]";
    }
    
}
