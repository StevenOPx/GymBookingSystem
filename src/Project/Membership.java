package Project;

import java.io.Serializable;

/***
 * Gym Booking System Mini-Project created on 15/11/2021
 * @author Steven Oprisan
 * @version 1.0
 */

public class Membership implements Serializable {

   private String memberName;
   private int memberAge;
   private String membershipType;
   private String length;

    /**
     * Member no-argument constructor calls the 4-argument Member constructor
     */

    public Membership() {
        this("Not Entered", 0, "Not selected", "Not selected");
    }

    /**
     * Member 4-arguments constructor
     * @param memberName - The name of the Member
     * @param memberAge - The Member's age
     * @param membershipType - The membership type
     * @param length - The price of the membership
     */
    public Membership(String memberName, int memberAge, String membershipType, String length) {
        setMemberName(memberName);
        setMemberAge(memberAge);
        setMembershipType(membershipType);
        setLength(length);
    }

    /**
     * Accessor method that returns the member's name
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * Mutator method to set the member's name
     * @param memberName
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * Accessor method that returns the member's age
     * @return
     */
    public int getMemberAge() {
        return memberAge;
    }

    /**
     * Mutator method to set the member's age
     * @param memberAge
     */
    public void setMemberAge(int memberAge) {
        this.memberAge = memberAge;
    }

    /**
     * Accessor method that returns the member's membership type
     * @return
     */
    public String getMembershipType() {
        return membershipType;
    }

    /**
     * Mutator method to set the member's membership type
     * @param membershipType
     */
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    /**
     * Accessor method that returns the member's membership length
     * @return
     */
    public String getLength() {
        return length;
    }

    /**
     * Mutator method to set the member's membership length
     * @param length
     */
    public void setLength(String length) {
        this.length = length;
    }

    /**
     * Method to get the state of a Member object
     * @return
     */
    @Override
    public String toString() {
        return  "Member Name: " + getMemberName() +
                "\n\nMember Age: " + getMemberAge() +
                "\n\nMembership Type: " + getMembershipType() +
                "\n\nLength: " + getLength();
    }
}
