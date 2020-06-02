package galih.dj.jfood_android;

/**
 * Class yang menyimpan dan mengatur data lokasi penjual
 *
 * @author Galih Damar Jati
 * @version 29 Mei 2020
 */

public class Location
{
    private String province;
    private String description;
    private String city;

    /**
     * Constructor untuk object class Location
     *@param city
     *@param province
     *@param description
     */
    public Location(String city, String province, String description)
    {
        this.city = city;
        this.province = province;
        this.description = description;
    }

    /**
     * Method getter untuk mendapatkan nama provinsi
     *
     * @return Isi variable province
     */
    public String getProvince()
    {
        return province;
    }

    /**
     * Method getter untuk mendapatkan nama kota
     *
     * @return Isi variable city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Method getter untuk mendapatkan deskripsi lokasi
     *
     * @return Isi variable description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Method setter untuk mengatur nama provinsi
     * @param  province
     *
     */
    public void setProvince(String province)
    {
        this.province = province;
    }

    /**
     * Method setter untuk mengatur nama kota
     * @param  city
     *
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Method setter untuk mengatur deskripsi lokasi
     * @param  description
     *
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
}


