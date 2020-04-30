package galih.dj.jfood_android;


public class Location
{
    private String province;
    private String description;
    private String city;

    /**
     * Constructor for objects of class Location
     *@param city
     *@param province
     *@param description
     */
    public Location(String city, String province, String description)
    {
        // initialise instance variables
        this.city = city;
        this.province = province;
        this.description = description;
    }

    /**
     * Digunakan untuk mendapatkan nama provinsi
     *
     * @return Isi variable province
     */
    public String getProvince()
    {
        // put your code here
        return province;
    }

    /**
     * Digunakan untuk mendapatkan nama kota
     *
     * @return Isi variable city
     */
    public String getCity()
    {
        // put your code here
        return city;
    }

    /**
     * Digunakan untuk mendapatkan deskripsi lokasi
     *
     * @return Isi variable description
     */
    public String getDescription()
    {
        // put your code here
        return description;
    }

    /**
     * Mengatur value dari variable province
     *
     * @param  province dengan tipe data string
     * @return Value pada parameter dimasukkan ke variable province
     */
    public void setProvince(String province)
    {
        // put your code here
        this.province = province;
    }

    /**
     * Mengatur value dari variable city
     *
     * @param  city dengan tipe data string
     * @return Value pada parameter dimasukkan ke variable city
     */
    public void setCity(String city)
    {
        // put your code here
        this.city = city;
    }

    /**
     * Mengatur value dari variable description
     *
     * @param  description dengan tipe data string
     * @return Value pada parameter dimasukkan ke variable description
     */
    public void setDescription(String description)
    {
        // put your code here
        this.description = description;
    }
}


