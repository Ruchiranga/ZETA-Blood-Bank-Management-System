

package model;


public class BloodrecievedDetail {
    
    private String receivedID;
    private String packetID;

    public BloodrecievedDetail(String receivedID, String packetID) {
        this.receivedID = receivedID;
        this.packetID = packetID;
    }

    /**
     * @return the receivedID
     */
    public String getReceivedID() {
        return receivedID;
    }

    /**
     * @param receivedID the receivedID to set
     */
    public void setReceivedID(String receivedID) {
        this.receivedID = receivedID;
    }

    /**
     * @return the packetID
     */
    public String getPacketID() {
        return packetID;
    }

    /**
     * @param packetID the packetID to set
     */
    public void setPacketID(String packetID) {
        this.packetID = packetID;
    }
    
    
}
