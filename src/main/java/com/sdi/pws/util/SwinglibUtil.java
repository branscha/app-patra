package com.sdi.pws.util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

    /**
     * Make a list of all URL's that can be found in a string.
     * @param aSource  A string, possibly containing URL's.
     * @return A List of URL's that could be scraped from the string.
     */
    public static java.util.List<String> extractUrl(String aSource)
    {
        final String lPrefix = "http://";
        final java.util.List<String> lResult = new ArrayList<String>();
        String lTodo = aSource;
        int lUrlPos = lTodo.indexOf(lPrefix);
        while (lUrlPos >= 0)
        {
            lUrlPos += lPrefix.length();
            StringBuffer lUrlBuf = new  StringBuffer();
            while((lUrlPos < lTodo.length()) && lTodo.charAt(lUrlPos) != '\n' && !Character.isSpaceChar(lTodo.charAt(lUrlPos))) lUrlBuf.append(lTodo.charAt(lUrlPos++));
            lResult.add(lUrlBuf.toString());

            if(lUrlPos < lTodo.length()) lTodo = lTodo.substring(lUrlPos);
            else lTodo = "";
            lUrlPos = lTodo.indexOf(lPrefix);
        }
        return lResult;
    }

    public static JPopupMenu popupBuilder(Action[] ... aActionLists)
    {
        final JPopupMenu lMenu = new JPopupMenu();
        for(int i = 0; i < aActionLists.length; i++)
        {
            for(Action lAction: aActionLists[i]) lMenu.add(lAction);
            if( i < aActionLists.length - 1) lMenu.addSeparator();
        }
        return lMenu;
    }
}
