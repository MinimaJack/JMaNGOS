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
package org.jmangos.realm.network.netty.packet.client;

import java.nio.BufferUnderflowException;

import javax.inject.Inject;
import javax.inject.Named;

import org.jmangos.commons.network.netty.sender.AbstractPacketSender;
import org.jmangos.realm.network.netty.packet.AbstractWoWClientPacket;
import org.jmangos.realm.network.netty.packet.server.SMSG_INIT_WORLD_STATES;

// TODO: Auto-generated Javadoc
/**
 * The Class CMSG_ZONEUPDATE.
 */
public class CMSG_ZONEUPDATE extends AbstractWoWClientPacket {

	/** The new zone. */
	@SuppressWarnings("unused")
	private int newZone;
	
	/** The sender. */
	@Inject
	@Named("client")
	private AbstractPacketSender sender;
	
	/* (non-Javadoc)
	 * @see org.wowemu.common.network.model.ReceivablePacket#readImpl()
	 */
	@Override
	protected void readImpl() throws BufferUnderflowException, RuntimeException {
		newZone = readD();
		
	}

	/* (non-Javadoc)
	 * @see org.wowemu.common.network.model.ReceivablePacket#runImpl()
	 */
	@Override
	protected void runImpl() {
		// FIXME implement
		sender.send(getClient(), new SMSG_INIT_WORLD_STATES(getPlayer())); 
		
	}

}