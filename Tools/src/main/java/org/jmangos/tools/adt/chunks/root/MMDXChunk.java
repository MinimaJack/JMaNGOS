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
package org.jmangos.tools.adt.chunks.root;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.jmangos.tools.adt.chunks.ADTChunk;

public class MMDXChunk extends ADTChunk {
	String[] filename;
	private MMIDChunk mmid;
	private int offset;

	@Override
	public ADTChunk reads(ByteBuffer bb, int offset, long size) {
		setGlobalOffcet(offset + size + HEADERSIZE);
		this.setByteBuffer(bb, offset);
		this.offset = offset;
		return this;
	}

	public String fillName() {
		String name = "";
		if (mmid != null) {
			filename = new String[mmid.offset.length];
			for (int i = 0; i < mmid.offset.length; i++) {
				filename[i] = readStringf((int) mmid.offset[i].get());
				name += "\n"+filename[i].toString();
			}
		}
		return name;
	}

	public String readStringf(int l) {
		byte[] tsring = new byte[2024];
		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		int i = 0;
		for (; (tsring[i] = getByteBuffer().get(
				(int) (offset+ HEADERSIZE + i + l))) != 0; i++) {
		}
		try {
			return decoder.decode(ByteBuffer.wrap(tsring, 0, i)).toString();
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		return "";

	}

	public String toString() {
		return "[MMDXChunk]" + fillName();
	}

	public void setMMID(MMIDChunk mmid) {
		this.mmid = mmid;
	}
}