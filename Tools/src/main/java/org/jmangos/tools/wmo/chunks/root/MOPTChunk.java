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
package org.jmangos.tools.wmo.chunks.root;

import java.nio.ByteBuffer;

import org.jmangos.tools.chunk.BaseChunk;
import org.jmangos.tools.wmo.chunks.WMOChunk;

public class MOPTChunk extends WMOChunk{
	class MOPTEntry extends WMOChunk{ 
		Unsigned16  BaseVertex = new Unsigned16();
		Unsigned16 	countVert = new Unsigned16();
		Float32[] vector = array(new Float32[3]); 		
		Float32 unknown  = new Float32();
	}

	private MOPTEntry[] MOPTEntries;
	@Override
	public BaseChunk reads(ByteBuffer bb, int offset, long size) {
		MOPTEntries = new MOPTEntry[(int) (size /20)];
		for (int i = 0; i < (size /20); i++) {
			MOPTEntries[i] = new MOPTEntry();
			MOPTEntries[i].setByteBuffer(bb, offset + 20*i);
		}
		setGlobalOffcet(offset + size + HEADERSIZE);
		this.setByteBuffer(bb, offset);
		return this;	
	}
	
	@SuppressWarnings("unused")
	private String getAllName(){
		String tmp = "";
		for (int i = 0; i < MOPTEntries.length; i++) {
			tmp +="\n\tBaseVertex:" + MOPTEntries[i].BaseVertex.get()+
			"\n\tcountVert:" + MOPTEntries[i].countVert.get()+
			"\n\tvector:" + MOPTEntries[i].vector[0].get()+ " " +MOPTEntries[i].vector[1].get()+ " " + MOPTEntries[i].vector[2].get()+
			"\n\tunknown:" + MOPTEntries[i].unknown.get();

		}
		return tmp;
	}
	
	public String toString(){
		return "[MOPTChunk]" + 
		"\n\tMOPTEntries count: " + MOPTEntries.length ;//+ getAllName(); 
	}
}