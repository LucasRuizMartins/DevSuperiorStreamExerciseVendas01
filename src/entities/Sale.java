package entities;

public class Sale {

    private Integer month;
    private Integer year;
    private Integer items;
    private String seller;
    private Double total;

    public Sale() {

    }

    public Sale(Integer month, Integer year, Integer items, String seller, Double total) {
        this.month = month;
        this.year = year;
        this.items = items;
        this.seller = seller;
        this.total = total;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double averagePrice(Integer quantity, Double total) {
        return total / quantity;
    }

    @Override
    public String toString() {
        return month + "/" + year + ", " + seller + "," + items + ", " + total;
    }
}