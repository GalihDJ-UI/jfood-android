package galih.dj.jfood_android;

/**
 * Class yang menyimpan dan mengatur data makanan
 *
 * @author Galih Damar Jati
 * @version 29 Mei 2020
 */

public class Food
{
    private int id;
    private String name;
    private int price;
    private String category;
    private Seller seller;

    /**
     * Constructor untuk object pada class food
     * @param id
     * @param name
     * @param seller
     * @param price
     * @param category
     */
    public Food(int id, String name, Seller seller, int price, String category)
    {
        this.id = id;
        this.name = name;
        this.seller = seller;
        this.price = price;
        this.category = category;
    }

    /**
     * Method getter untuk mendapatkan id makanan
     *
     * @return Isi variable id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Method getter untuk mendapatkan nama makanan
     *
     * @return Isi variable name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method getter untuk mendapatkan nama penjual
     *
     * @return Isi variable seller
     */
    public Seller getSeller()
    {
        return seller;
    }

    /**
     * Digunakan untuk mendapatkan harga makanan
     *
     * @return Isi variable price
     */
    public int getPrice()
    {
        // put your code here
        return price;
    }

    /**
     * Method getter untuk mendapatkan kategori makanan
     *
     * @return Isi variable category
     */
    public String getCategory()
    {
        // put your code here
        return category;
    }

    /**
     * Method setter untuk mengatur id makanan
     * @param id
     *
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Method setter untuk mengatur nama makanan
     * @param name
     *
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Method setter untuk mengatur penjual
     * @param seller
     *
     */
    public void setSeller(Seller seller)
    {
        this.seller = seller;
    }

    /**
     * Method setter untuk mengatur harga makanan
     * @param price
     *
     */
    public void setPrice(int price)
    {
        this.price = price;
    }

    /**
     * Method setter untuk mengatur kategori makanan
     * @param category
     *
     */
    public void setCategory(String category)
    {
        this.category = category;
    }
}
