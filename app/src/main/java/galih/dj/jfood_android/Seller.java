package galih.dj.jfood_android;

/**
 * Class yang menyimpan dan mengatur data pelanggan
 *
 * @author Galih Damar Jati
 * @version 29 Mei 2020
 */

public class Seller
{
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;

    /**
     * Constructor for objects of class Customer
     *@param id
     *@param name
     *@param email
     *@param phoneNumber
     *@param location
     */
    public Seller(int id, String name, String email, String phoneNumber,
                  Location location)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    /**
     * Method getter untuk mendapatkan id penjual
     *
     * @return Isi variable id
     */
    public int getId()
    {
        // put your code here
        return id;
    }

    /**
     * Method getter untuk mendapatkan nama penjual
     *
     * @return Isi variable name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method getter untuk mendapatkan email penjual
     *
     * @return Isi variable email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Method getter untuk mendapatkan nomor telepon penjual
     *
     * @return Isi variable phoneNumber
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Method getter untuk mendapatkan lokasi
     *
     * @return Isi variable location
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * Method setter untuk mengatur id penjual
     * @param id
     *
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Method setter untuk mengatur nama penjual
     * @param name
     *
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Method setter untuk mengatur email penjual
     * @param email
     *
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Method setter untuk mengatur nomor telepon penjual
     * @param phoneNumber
     *
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Method setter untuk mengatur lokasi penjual
     * @param location
     *
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }
}
