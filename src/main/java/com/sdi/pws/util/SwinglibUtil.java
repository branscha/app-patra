package com.sdi.pws.util;

import com.jgoodies.forms.layout.Size;

import javax.swing.*;
import java.awt.*;

public class SwinglibUtil
{
    private SwinglibUtil()
    {
    }

    public static void centerOnScreen(Component aCompo)
    {
        final Dimension lComponentSize = aCompo.getSize();
        centerOnScreen(aCompo, lComponentSize);
    }

    public static void centerOnScreen(Component aCompo, Dimension aCompoSize)
    {
        final Dimension lScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aCompo.setLocation(lScreenSize.width / 2 - (aCompoSize.width / 2), lScreenSize.height / 2 - (aCompoSize.height / 2));
    }
    public static JFrame findTopFrame(Component aCompo)
    {
        if(aCompo == null) return null;
        else if(aCompo instanceof JFrame) return (JFrame) aCompo;
        else return findTopFrame(aCompo.getParent());
    }

    public static interface ComponentProcessor
    {
        public void process(Component aComponent);
    }

    public static class BackgroundColorChanger
    implements ComponentProcessor
    {
        private Color color;

        public BackgroundColorChanger(Color aColor)
        {
            color = aColor;
        }

        public void process(Component aComponent)
        {
            if(aComponent instanceof JPanel  )
            {
                JPanel lPanel = (JPanel) aComponent;
                lPanel.setBackground(color);
            }
            else if (aComponent instanceof JOptionPane)
            {
                JOptionPane lPane = (JOptionPane) aComponent;
                lPane.setOpaque(false);
            }
        }
    }

    public static void processNestedComponents(Component aComponent, ComponentProcessor aProcessor)
    {
        aProcessor.process(aComponent);
        if(aComponent instanceof Container)
        {
            Container lCont = (Container) aComponent;
            for(int i = 0; i < lCont.getComponentCount(); i++)
            {
                processNestedComponents(lCont.getComponent(i), aProcessor);
            }
        }
        
    }
}
