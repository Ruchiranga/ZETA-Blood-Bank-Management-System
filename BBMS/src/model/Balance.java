

package model;

import java.sql.Date;

public class Balance {
    
    private Date stockDate;
    private String bloodType;
    private int balance;

    public Balance(Date stockDate, String bloodType, int balance) {
        this.stockDate = stockDate;
        this.bloodType = bloodType;
        this.balance = balance;
    }

    /**
     * @return the stockDate
     */
    public Date getStockDate() {
        return stockDate;
    }

    /**
     * @param stockDate the stockDate to set
     */
    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    /**
     * @return the bloodType
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * @param bloodType the bloodType to set
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    
}
