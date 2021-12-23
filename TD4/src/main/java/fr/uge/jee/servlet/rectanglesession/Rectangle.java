package fr.uge.jee.servlet.rectanglesession;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;

@Component
public class Rectangle {
    @Min(0)
    private int width;

    @Min(0)
    private int height;

    public int area(){return width*height;}

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "area of Rectangle (w:" + width +"; h: "+ height +") = "+area();
    }
}
