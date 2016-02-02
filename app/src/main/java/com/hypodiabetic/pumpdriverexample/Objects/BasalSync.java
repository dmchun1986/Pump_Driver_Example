package com.hypodiabetic.pumpdriverexample.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * Created by Tim on 30/01/2016.
 * Sync object to help the processing of sending and receiving Temp Basal data
 * Do not edit, this must match the object in HAPP
 */
public class BasalSync {

    public String   action;                     //new / update / cancel
    public String   state;                      //to_sync / sent / received / delivered / error
    public String   details;                    //details of this item being synced
    public Long     happ_integration_id;        //ID of the integration record HAPP has
    public Long     remote_id;                  //ID of remote record
    public String   integrationSecretCode;      //Random string to UID this sync request

    public Double   rate;                       //Temp Basal Rate for (U/hr) mode
    public Integer  ratePercent;                //Temp Basal Rate for "percent" of normal basal
    public Integer  duration;                   //Duration of Temp
    public Date start;                      //When the Temp Basal started

    public BasalSync(Basal basal){
        //Prepares a Temp Basal integration to be sent
        action                  =   basal.action;
        state                   =   basal.state;
        details                 =   basal.details;
        happ_integration_id     =   basal.happ_int_id;
        remote_id               =   basal.getId();
        integrationSecretCode   =   basal.auth_code;

        rate                =   basal.rate;
        ratePercent         =   basal.ratePercent;
        duration            =   basal.duration;
        start               =   basal.start_time;
    }

    public String asJSONString(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .create();
        return gson.toJson(this, BasalSync.class);
    }



}
