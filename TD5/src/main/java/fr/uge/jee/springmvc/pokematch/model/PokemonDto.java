package fr.uge.jee.springmvc.pokematch.model;


import java.util.Base64;

public class PokemonDto implements Comparable<PokemonDto>{

    private String name;

    private int id ;

    private SpritesDto sprites;

    private int occurence = 0;

    private byte[] imageCache =null;

    public void incrementOccurence(){
        occurence++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setId(int id) {
        this.id = id;
    }

    void setImageCache(byte[] imageCache) {
        this.imageCache = imageCache;
    }

    public int getId() {
        return id;
    }

    public void setSprites(SpritesDto sprites) {
        this.sprites = sprites;
    }

    public SpritesDto getSprites() {
        return sprites;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof PokemonDto)) return false;
        PokemonDto pokemon = (PokemonDto) o;
        return id == pokemon.id && name.equals(pokemon.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + id;
        return result;}

    @Override
    public String toString() {
        return name +" checked "+ occurence+" times";
    }


    @Override
    public int compareTo(PokemonDto o) {
        return this.occurence - o.occurence;
    }


    public String imagePath(){
        return sprites.imagePath();
    }

    public String getImageBase64(){
        return Base64.getEncoder().encodeToString(imageCache);
    }
}
