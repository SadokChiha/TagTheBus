package publication;

import Entity.Station;
import sqlite.PictureStation;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class DataPubModel {
    private PictureStation station;

    public DataPubModel(PictureStation station)
    {
        this.station=station;
    }
    public PictureStation getStation() {
        return station;
    }


    public void setStation(PictureStation station) {
        this.station = station;
    }
}
