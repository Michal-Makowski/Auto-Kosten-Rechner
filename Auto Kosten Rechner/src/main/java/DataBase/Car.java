package DataBase;

public class Car {

    private int id;
    private String carNumber;
    private String brand;
    private String model;
    private int registration;

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id  = id;
    }

    public String getNumber() {
        return carNumber;
    }

    public void setNumber(String number){
        this.carNumber  = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand){
        this.brand  = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model){
        this.model  = model;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration){
        this.registration  = registration;
    }

    @Override
    public String toString(){
        return carNumber;
    }

}
