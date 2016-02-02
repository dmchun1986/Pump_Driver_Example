package com.hypodiabetic.pumpdriverexample.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * Created by Tim on 30/01/2016.
 * Sync object to help the processing of sending and receiving Bolus data
 * Do not edit, this must match the object in HAPP
 */
public class BolusSync {

    public String   action;                     //new / update / cancel
    public String   state;                      //to_sync / sent / received / delivered / error
    public String   details;                    //details of this item being synced
    public Long     happ_integration_id;        //ID of the integration record HAPP has
    public Long     remote_id;                  //ID of remote record
    public String   integrationSecretCode;      //Random string to UID this sync request

    public Double   value;                      //Bolus Amount
    public String   bolusType;                  //Standard / Square Wave, etc
    public Date     requested;                  //Date requested
    public String   note;                       //bolus / correction


    public BolusSync (Treatment bolus){
        //Prepares a Bolus integration to be sent
        action                  =   "update";
        state                   =   bolus.state;
        details                 =   bolus.details;
        happ_integration_id     =   bolus.happ_int_id;
        remote_id               =   bolus.getId();
        integrationSecretCode   =   bolus.auth_code;

        value               =   bolus.value;
        bolusType           =   "standard";
        requested           =   new Date(bolus.date_requested);
        note                =   bolus.type;

    }

    public String asJSONString(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .create();
        return gson.toJson(this, BolusSync.class);
    }



}
