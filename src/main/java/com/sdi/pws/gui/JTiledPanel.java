package com.sdi.pws.gui;

import javax.swing.*;
import java.awt.*;

public class JTiledPanel
        extends JPanel
{

    private Image img;

    public JTiledPanel()
    {
        setLayout(null);
    }

    public JTiledPanel(String img)
    {
        this(new ImageIcon(img).getImage());
    }

    public JTiledPanel(Image img)
    {
        this.setTile(img);

    }

    public void setTile(Image img)
    {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        this.invalidate();
    }

    public void paintComponent(Graphics g)
    {
        if(img != null)
        {
            final Rectangle lBounds = this.getBounds();

            final int dx = img.getWidth(null);
            final int dy = img.getHeight(null);

            final int tx = lBounds.getSize().width;
            final int ty = lBounds.getSize().height;

            for(int x = 0; x < tx; x+=dx)
                for(int y = 0; y < ty; y+=dy)
                    g.drawImage(img, x, y, null);
        }
        else super.paintComponent(g);
    }

}
