package Project;

import java.io.Serializable;

/***
 * Gym Booking System Mini-Project created on 15/11/2021
 * @author Steven Oprisan
 * @version 1.0
 */

public class Booking implements Serializable {

    private String bookingType;
    private String bookingLocation;
    private int bookingLength;
    private String dayOfWeek;

    /**
     * No-argument constructor
     */
    public Booking() {
        this("Not Selected", "Not selected", 0, "Not selected");
    }

    /**
     * The 4-arguments constructor
     * @param bookingType - The type of Gym class
     * @param bookingLocation - The location of the Gym Class
     * @param bookingLength - The length of the Gym class
     * @param dayOfWeek - The day of week the Gym class will be held
     */
    public Booking(String bookingType, String bookingLocation, int bookingLength, String dayOfWeek) {
        setBookingType(bookingType);
        setBookingLocation(bookingLocation);
        setBookingLength(bookingLength);
        setDayOfWeek(dayOfWeek);
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String classType) {
        this.bookingType = classType;
    }

    public String getBookingLocation() {
        return bookingLocation;
    }

    public void setBookingLocation(String bookingLocation) {
        this.bookingLocation = bookingLocation;
    }

    public int getBookingLength() {
        return bookingLength;
    }

    public void setBookingLength(int bookingLength) {
        this.bookingLength = bookingLength;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return  "Booking Type: " + getBookingType() +
                "\n\nBooking Location: " + getBookingLocation() +
                "\n\nBooking Length: " + getBookingLength() +
                "\n\nDay Of Week: " + getDayOfWeek();
    }
}
