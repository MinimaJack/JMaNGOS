/*******************************************************************************
 * Copyright (C) 2013 JMaNGOS <http://jmangos.org/>
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.jmangos.realm.network.packet.wow.client;

import java.nio.BufferUnderflowException;

import org.jmangos.commons.network.sender.AbstractPacketSender;
import org.jmangos.realm.network.packet.wow.AbstractWoWClientPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The Class CMSG_SET_SELECTION.
 */
@Component
public class CMSG_SET_SELECTION extends AbstractWoWClientPacket {

    private static final Logger log = LoggerFactory.getLogger(CMSG_SET_SELECTION.class);
    /** The sender. */
    @Autowired
    @Qualifier("nettyPacketSender")
    private AbstractPacketSender sender;

    /** The guid. */
    private long guid;

    @Override
    protected void readImpl() throws BufferUnderflowException, RuntimeException {

        this.guid = readQ();
        log.debug("Player {} change selection to {}", getPlayer().getCharacterData().getName(),
                this.guid);
    }

    @Override
    protected void runImpl() {

        getPlayer().getCharacterData().setTarget(this.guid);

    }
}
