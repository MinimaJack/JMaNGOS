/*******************************************************************************
 * Copyright (c) 2012 JMANGOS
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     JMANGOS - initial API and implementation
 ******************************************************************************/
package org.jmangos.tools.dbc.struct;

import javax.xml.bind.annotation.XmlAttribute;

import org.jmangos.tools.dbc.dataholder.DBCStruct;


public class RandPropPointsEntry extends DBCStruct<RandPropPointsEntry> {
	final static int MAX_PROP = 5;
	@XmlAttribute(name="itemLevel", required=true)
    public final Unsigned32 itemLevel = new Unsigned32();  
	@XmlAttribute(name="EpicPropertiesPoints", required=true)
	   public final Unsigned32[] EpicPropertiesPoints = array(new Unsigned32[MAX_PROP]);
	@XmlAttribute(name="RarePropertiesPoints", required=true)
	   public final Unsigned32[] RarePropertiesPoints = array(new Unsigned32[MAX_PROP]);
	@XmlAttribute(name="UncommonPropertiesPoints", required=true)
	   public final Unsigned32[] UncommonPropertiesPoints = array(new Unsigned32[MAX_PROP]);
}