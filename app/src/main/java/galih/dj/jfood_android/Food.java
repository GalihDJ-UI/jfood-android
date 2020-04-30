package galih.dj.jfood_android;

public class Food
{
    private int id;
    private String name;
    private int price;
    private String category;
    private Seller seller;

    public Food(int id, String name, Seller seller, int price, String category)
    {
        // initialise instance variables
        this.id = id;
        this.name = name;
        this.seller = seller;
        this.price = price;
        this.category = category;
    }

    /**
     * Digunakan untuk mendapatkan id makanan
     *
     * @return Isi variable id
     */
    public int getId()
    {
        // put your code here
        return id;
    }

    /**
     * Digunakan untuk mendapatkan nama makanan
     *
     * @return Isi variable name
     */
    public String getName()
    {
        // put your code here
        return name;
    }

    /**
     * Digunakan untuk mendapatkan nama penjual
     *
     * @return Isi variable seller
     */
    public Seller getSeller()
    {
        // put your code here
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
     * Digunakan untuk mendapatkan kategori makanan
     *
     * @return Isi variable category
     */
    public String getCategory()
    {
        // put your code here
        return category;
    }

    /**
     * Mengatur value dari variable id
     *

     * @return Value pada parameter dimasukkan ke variable id
     */
    public void setId(int id)
    {
        // put your code here
        this.id = id;
    }

    /**
     * Mengatur value dari variable name
     *

     * @return Value pada parameter dimasukkan ke variable name
     */
    public void setName(String name)
    {
        // put your code here
        this.name = name;
    }

    /**
     * Mengatur value dari variable seller
     *

     * @return Value pada parameter dimasukkan ke variable seller
     */
    public void setSeller(Seller seller)
    {
        // put your code here
        this.seller = seller;
    }

    /**
     * Mengatur value dari variable price
     *

     * @return Value pada parameter dimasukkan ke variable price
     */
    public void setPrice(int price)
    {
        // put your code here
        this.price = price;
    }

    /**
     * Mengatur value dari variable string
     *

     * @return Value pada parameter dimasukkan ke variable string
     */
    public void setCategory(String category)
    {
        // put your code here
        this.category = category;
    }
}
