package app.map;

import app.city.City;
import app.city.CityDAO;
import app.generic.Location;
import app.map.mercator.Mercator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class DrawingPanel extends JPanel implements Serializable {
    private final MainFrame frame;
    private BufferedImage image;
    private Graphics2D offscreen;
    private final CityDAO cityDAO = new CityDAO();
    private int mapWidth;
    private int mapHeight;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        Image img = null;
        try {
            img = ImageIO.read(new File("./src/main/resources/map3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mapWidth = img.getWidth(this);
        mapHeight = img.getHeight(this);

        setPreferredSize(new Dimension(img.getWidth(this), img.getHeight(this)));

        image = new BufferedImage(
                img.getWidth(this),
                img.getHeight(this),
                BufferedImage.TYPE_INT_ARGB);

        offscreen = image.createGraphics();

        offscreen.drawImage(
                img,
                0,
                0,
                img.getWidth(this),
                img.getHeight(this),
                this);

        paintCities();

        repaint();
    }

    private void paintCities() {
        List<Location> cities = null;
        try {
            cities = cityDAO.findAll();
            offscreen.setFont(new Font( "SansSerif", Font.PLAIN, 12 ));;
            for (Location city : cities) {
                drawCity((City) city);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        repaint();
    }

    private void drawCity(City city) {
        Double longitude = city.getLongitude();
        Double latitude = city.getLatitude();
        double x = (longitude + 180) / 360.0 * mapWidth;
        double yProjection = Mercator.yAxisProjection(latitude) / Math.PI;
        double y = mapHeight / 2.0 * (1 - yProjection);
        int radius = 4;
        offscreen.setColor(Color.BLACK);
        // offscreen.drawString(city.getName(),(int) x ,(int) y);
        offscreen.setColor(Color.BLUE);
        offscreen.fillOval((int) x - radius , (int) y - radius, 2*radius, 2*radius);
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

}
