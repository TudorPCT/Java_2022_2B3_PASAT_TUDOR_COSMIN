package paint;

import model.Node;
import model.Solution;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class DrawingPanel extends JPanel implements Serializable {
    private final MainFrame frame;
    int nodeSize = 50;
    int canvasWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 10);
    int canvasHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 150;
    BufferedImage image;
    Graphics2D offscreen;
    Solution solution;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffScreenImage();
        init();
    }

    final void init() {
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, frame.getWidth(), frame.getHeight());
    }

    private void createOffScreenImage(){
        image = new BufferedImage(canvasWidth,
                canvasHeight,
                BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
    }

    public void drawRoute(String selectedTown) {
        solution = new Solution(selectedTown);
        frame.list.removeAll();
        frame.list.setVisible(true);
        for (Map.Entry<Long, List<Node>> set :
                solution.getRoute().entrySet()) {
            List<Node> vehicleRoute = set.getValue();

            offscreen.setColor(Color.BLACK);
            drawLine(solution.getWarehouse().getLatitude().intValue(),
                    solution.getWarehouse().getLongitude().intValue(),
                    vehicleRoute.get(0).getLatitude().intValue(),
                    vehicleRoute.get(0).getLongitude().intValue());


            for (int i = 1; i < vehicleRoute.size(); i++) {
                frame.list.add(vehicleRoute.get(i).toString());
                offscreen.setColor(Color.BLACK);
                drawLine(vehicleRoute.get(i-1).getLatitude().intValue(),
                        vehicleRoute.get(i-1).getLongitude().intValue(),
                        vehicleRoute.get(i).getLatitude().intValue(),
                        vehicleRoute.get(i).getLongitude().intValue());

                offscreen.setColor(Color.CYAN);
                drawLocation(vehicleRoute.get(i - 1).getLatitude().intValue(),
                        vehicleRoute.get(i - 1).getLongitude().intValue(),i);
            }



            offscreen.setColor(Color.BLACK);
            drawLine(solution.getWarehouse().getLatitude().intValue(),
                    solution.getWarehouse().getLongitude().intValue(),
                    vehicleRoute.get(vehicleRoute.size() - 1).getLatitude().intValue(),
                    vehicleRoute.get(vehicleRoute.size() - 1).getLongitude().intValue());

            offscreen.setColor(Color.CYAN);
            drawLocation(vehicleRoute.get(vehicleRoute.size() - 1).getLatitude().intValue(),
                    vehicleRoute.get(vehicleRoute.size() - 1).getLongitude().intValue(), vehicleRoute.size());

            offscreen.setColor(Color.RED);
            drawLocation(solution.getWarehouse().getLatitude().intValue(),
                    solution.getWarehouse().getLongitude().intValue(),0);

        }
        frame.list.repaint();
        repaint();
    }

    private void drawLocation(int x, int y,int i) {
        x = (int) (x / solution.getWidth() * (canvasWidth - 100)) + 10;
        y = (int) (y / solution.getHeight() * (canvasHeight - 100)) + 10;
        offscreen.fillOval(x - 10, y - 10, 10, 10);
        offscreen.setColor(Color.black);
        if(i!=0) {
            offscreen.drawString(String.valueOf(i),x,y);
        }
        else
            offscreen.drawString("",x,y);
    }

    private void drawLine(int x, int y, int x1, int y1){
        x = (int) (x / solution.getWidth() * (canvasWidth - 100)) + 10;
        y = (int) (y / solution.getHeight() * (canvasHeight - 100)) + 10;
        x1 = (int) (x1 / solution.getWidth() * (canvasWidth - 100)) + 10;
        y1 = (int) (y1 / solution.getHeight() * (canvasHeight - 100)) + 10;
        offscreen.drawLine(x - 5, y - 5, x1 - 5, y1 - 5);
    }

    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }



}
