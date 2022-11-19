package DataBase;

public class Cost {

    private int id;
    private String costType;
    private int cost;
    private int kilometer;
    private String date;
    private String comment;
    private int carID;

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id  = id;
    }

    public String getCostType(){return costType;}

    public void setCostType(String costType){ this.costType = costType; }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost){
        this.cost  = cost;
    }

    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer){
        this.kilometer  = kilometer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        this.date  = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment){
        this.comment  = comment;
    }

    public int getCarID(){return carID;}

    public void setCarID(int carID){ this.carID = carID; }

}
