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

public interface PwsDatabase
{
    int getNrRecords();
    PwsRecord getRecord(int aIndex);
    void add(PwsRecord aRecord);
    void remove(int aIndex);
    Iterator iterator();

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
