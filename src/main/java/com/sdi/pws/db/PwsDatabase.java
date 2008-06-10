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

package com.sdi.pws.db;

import com.sdi.pws.codec.Codec;

import java.util.Iterator;
import java.io.File;

/**
 * Represents a "password safe" compatible database.
 * Note that there are different versions of this database, multiple versions
 * can be supported by this datamodel.
 */
public interface PwsDatabase
{
    /**
     * Get the number of entries in the database.
     * @return The number of entries.
     */
    int getNrRecords();

    /**
     * Get a specific entry from the database.
     * @param aIndex The index of the requested record. 
     * @return The password record at that index.
     */
    PwsRecord getRecord(int aIndex);

    /**
     * Add a new record to the database.
     * @param aRecord The new record to be added to the database.
     */
    void add(PwsRecord aRecord);

    /**
     * Remove the specified record from the database.
     * @param aIndex The index of the record to be removed from the database.
     */
    void remove(int aIndex);

    /**
     * Walk over the password records in the database.
     * @return An iterator that walks over all PwsRecords in the database.
     */
    Iterator<PwsRecord> iterator();

    /**
     * The version of the password database.
     * There are codecs for different versions of the "password safe" application.
     * It depends on the database version which features are available.
     * @return The version of the database file.
     */
    String getVersion();

    String getPassphrase();
    void setPassphrase(String passphrase);

    Codec getCodec();
    void setCodec(Codec codec);

    File getFile();
    void setFile(File file);

    String getParameters();
    void setParameters(String parameters);

    boolean isChanged();
    void setChanged(boolean aChanged);
}
