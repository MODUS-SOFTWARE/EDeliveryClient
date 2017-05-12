/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.models;

/**
 *
 * @author modussa
 */
public class Participant {
    
    private String ParticipantIdentifierScheme;
    private String ParticipantIdentifierValue;

    public Participant(){}
    
    public Participant(String ParticipantIdentifierScheme, String ParticipantIdentifierValue){
        this.ParticipantIdentifierScheme = ParticipantIdentifierScheme;
        this.ParticipantIdentifierValue = ParticipantIdentifierValue;
    }
    
    
    public String getParticipantIdentifierScheme() {
        return ParticipantIdentifierScheme;
    }

    public void setParticipantIdentifierScheme(String ParticipantIdentifierScheme) {
        this.ParticipantIdentifierScheme = ParticipantIdentifierScheme;
    }

    public String getParticipantIdentifierValue() {
        return ParticipantIdentifierValue;
    }

    public void setParticipantIdentifierValue(String ParticipantIdentifierValue) {
        this.ParticipantIdentifierValue = ParticipantIdentifierValue;
    }
    
    
    
}
