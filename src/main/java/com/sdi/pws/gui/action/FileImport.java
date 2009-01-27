/*
Password Tracker (PATRA). An application to safely store your passwords.
Copyright (C) 2006-2009  Bruno Ranschaert, S.D.I.-Consulting BVBA.

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

package com.sdi.pws.gui.action;

import com.sdi.pws.codec.Codec;
import com.sdi.pws.codec.CodecException;
import com.sdi.pws.codec.Codec1;
import com.sdi.pws.codec.CodecUtil;
import com.sdi.pws.db.PwsDatabase;
import com.sdi.pws.db.PwsRecord;
import com.sdi.pws.gui.DatabaseHolder;
import com.sdi.pws.gui.compo.db.change.ChangeViewDatabase;
import com.sdi.pws.preferences.Preferences;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Iterator;

abstract class FileImport
extends AbstractAction
{
    protected DatabaseHolder dbHolder;
    protected JComponent application;
    protected Preferences prefs;

    public FileImport(String aName)
    {
        super(aName);
    }

    protected abstract Codec getCodec();
    protected abstract File askFile();
    protected abstract String askPassphrase();

    protected void wireAction(JComponent aAppFrame, DatabaseHolder aHolder, Preferences aPrefs)
    {
        dbHolder = aHolder;
        application = aAppFrame;
        prefs = aPrefs;

        // Decide if the action is enabled or not right now.
        // Initialize current state using the current database.
        if (dbHolder.getCurrentDatabase() != null)
        {
            setEnabled(true);
            dbHolder.getCurrentDatabase().addPropertyChangeListener(new PropertyChangeListener()
            {
                public void propertyChange(PropertyChangeEvent evt)
                {
                    // Decide if the action is enabled or not right now.
                    setEnabled(dbHolder.getCurrentDatabase() != null);
                }
            });
        }

        // Add a listener chain so that the state is maintained if the database changes.
        dbHolder.addPropertyChangeListener(new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent aHolderEvt)
            {
                final ChangeViewDatabase lOldDb = (ChangeViewDatabase) aHolderEvt.getOldValue();
                final ChangeViewDatabase lNewDb = (ChangeViewDatabase) aHolderEvt.getNewValue();

                // We must not forget to stop listening to the old database.
                if (lOldDb != null)
                {
                    lOldDb.removePropertyChangeListener(this);
                }

                // We can now investigate the state of the new database and
                // add a listener to the new database.
                if (lNewDb != null)
                {
                    setEnabled(true);
                    lNewDb.addPropertyChangeListener(new PropertyChangeListener()
                    {
                        public void propertyChange(PropertyChangeEvent aDbEvent)
                        {
                            // Decide if the action is enabled or not right now.
                            setEnabled(dbHolder.getCurrentDatabase() != null);
                        }
                    });
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            final PwsDatabase lDb = dbHolder.getCurrentDatabase();

            final File lImport = askFile();
            final String lPassphrase = askPassphrase();

            if (lImport != null && lPassphrase != null)
            {
                // Import the data.
                final PwsDatabase lImportedDb = getCodec().read(lImport, lPassphrase);
                if(lDb.getVersion().equals(Codec1.VERSION)) CodecUtil.downgradeVersion(lImportedDb);

                // Merge the databases.
                final Iterator lIter = lImportedDb.iterator();
                while (lIter.hasNext())
                {
                    final PwsRecord lRec = (PwsRecord) lIter.next();
                    lDb.add(lRec);
                }
            }
        }
        catch (CodecException eCodec)
        {
            JOptionPane.showMessageDialog(application, eCodec.getFormattedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}