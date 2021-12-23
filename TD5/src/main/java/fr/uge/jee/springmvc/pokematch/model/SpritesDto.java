package fr.uge.jee.springmvc.pokematch.model;


public class SpritesDto {

    private String front_default;

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    public String imagePath(){
        return front_default;
    }
}
