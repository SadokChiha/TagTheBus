package tn.abdessamed.yessine.tagthebus;

import Entity.Station;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class DataModel {
    private Station station;

    public  DataModel(Station station)
    {
        this.station=station;
    }
    public Station getStation() {
        return station;
    }


    public void setStation(Station station) {
        this.station = station;
    }
}
