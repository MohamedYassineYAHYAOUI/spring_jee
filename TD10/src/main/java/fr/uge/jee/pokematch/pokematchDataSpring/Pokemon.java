package fr.uge.jee.pokematch.pokematchDataSpring;



import javax.persistence.*;


@Entity
public class Pokemon {

    @Id
    @Column(name ="NAME")
    private String name;

    @Column(name = "SCORE")
    private Long score;

    public Pokemon(){}

    public Pokemon(String name, Long score){
        this.name = name;
        this.score = score;
    }



    public String getName() {
        return name;
    }

    public Long getScore() {
        return score;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
