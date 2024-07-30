package com.console_network.app.post.infrastructure.Dto;

public class DashBoardPostResponse {
    public DashBoardPostResponse(String formattedResponse){
        this.formattedResponse=formattedResponse;
    }
    private String formattedResponse;


    public String getFormattedResponse(){return formattedResponse;}
    public void setFormattedResponse(String formattedResponse){this.formattedResponse=formattedResponse;}

}
