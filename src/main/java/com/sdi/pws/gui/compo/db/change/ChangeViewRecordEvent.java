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

package com.sdi.pws.gui.compo.db.change;

import com.sdi.pws.db.PwsField;
import com.sdi.pws.db.PwsRecord;

import java.util.EventObject;

public class ChangeViewRecordEvent
extends EventObject
{
    public static final int INSERT = 0;
    public static final int DELETE = 1;
    public static final int MODIFY = 2;

    private PwsField field;
    private int op;
    private ChangeViewDatabase db;

    public ChangeViewRecordEvent(ChangeViewDatabase aDb, PwsRecord aSource, PwsField aField, int aOp)
    {
        super(aSource);
        field = aField;
        op = aOp;
        db = aDb;
    }

    public PwsField getField()
    {
        return field;
    }

    public int getOp()
    {
        return op;
    }

    public ChangeViewDatabase getDb()
    {
        return db;
    }
}