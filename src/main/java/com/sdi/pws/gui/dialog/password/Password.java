/*
Password Tracker (PATRA). An application to safely store your passwords.
Copyright (C) 2006  Bruno Ranschaert, S.D.I.-Consulting BVBA.

For more information contact: nospam@sdi-consulting.com
Visit our website: http://www.sdi-consulting.com

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

package com.sdi.pws.gui.dialog.password;

import com.sdi.pws.gui.GuiUtil;
import com.sdi.pws.gui.CyclicFocusPolicy;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.GridConstraints;

import javax.swing.*;
import javax.help.HelpBroker;
import java.awt.*;

public class Password
{
    private JPanel mainPanel;
    private JPasswordField password;

    public Password()
    {
        // Install Help. Listen for help from the F1 key.
        final HelpBroker lBroker = GuiUtil.getHelpBroker();
        lBroker.enableHelpKey(mainPanel, "intro_html", null);
    }

    public static String askPassword(JComponent aApp)
    {
        final Password lPwd = new Password();
        final JOptionPane lPane = new JOptionPane(lPwd.mainPanel, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        final JDialog lDialog = lPane.createDialog(aApp, GuiUtil.getText("password.title"));
        lDialog.setFocusTraversalPolicy(new CyclicFocusPolicy(new Component[]{lPwd.password}));
        lDialog.setVisible(true);

        final Object lUserResponseObj = lPane.getValue();
        if(lUserResponseObj instanceof Integer)
        {
            final int lUserResponse = ((Integer) lUserResponseObj).intValue();
            switch(lUserResponse)
            {
                case JOptionPane.OK_OPTION:
                    return new String(lPwd.password.getPassword());
                case JOptionPane.CANCEL_OPTION:
                case JOptionPane.CLOSED_OPTION:
                default:
                    return null;
            }
        }
        else return null;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        password = new JPasswordField();
        mainPanel.add(password, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$()
    {
        return mainPanel;
    }
}