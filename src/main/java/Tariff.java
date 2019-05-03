class Tariff {
    private int idTariff;
    private String nameTariff;
    private int Price;
    private int idArea;

    public void setIdTariff(int idTariff) {
        this.idTariff = idTariff;
    }

    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdTariff() {
        return idTariff;
    }

    public String getNameTariff() {
        return nameTariff;
    }

    public int getPrice() {
        return Price;
    }

    public int getIdArea() {
        return idArea;
    }
}
