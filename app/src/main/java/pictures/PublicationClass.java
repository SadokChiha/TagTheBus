package pictures;

/**
 * Created by sadok on 25/03/2017.
 */

public class PublicationClass {

    private String id;
    private String nomPublication;
    private String description;
    private String emplacement;

    private String date;

    private String imgFood;


    public PublicationClass() {
    }

    public PublicationClass(String id, String nomPublication, String description, String emplacement, int note, String date, String imgFood) {
        this.id = id;
        this.nomPublication = nomPublication;
        this.description = description;
        this.emplacement = emplacement;
        this.date = date;
        this.imgFood = imgFood;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getNomPublication() {
        return nomPublication;
    }

    public void setNomPublication(String nomPublication) {
        this.nomPublication = nomPublication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgFood() {
        return imgFood;
    }

    public void setImgFood(String imgFood) {
        this.imgFood = imgFood;
    }

  }
