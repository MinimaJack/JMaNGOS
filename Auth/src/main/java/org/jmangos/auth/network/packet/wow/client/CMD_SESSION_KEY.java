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
package org.jmangos.auth.network.packet.wow.client;

import java.nio.BufferUnderflowException;

import org.jmangos.auth.network.packet.wow.AbstractWoWClientPacket;
import org.jmangos.auth.network.packet.wow.server.SMD_SESSION_KEY;
import org.jmangos.auth.wow.controller.AccountController;
import org.jmangos.commons.model.AccountInfo;
import org.jmangos.commons.network.sender.AbstractPacketSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The Class <tt>CMD_SESSION_KEY</tt>.
 */
@Component
public class CMD_SESSION_KEY extends AbstractWoWClientPacket {

    @Autowired
    @Qualifier("nettyPacketSender")
    private AbstractPacketSender sender;

    @Autowired
    private AccountController accountController;

    private String accountName;

    public CMD_SESSION_KEY() {

        super();
    }

    /**
     * 
     * @see org.jmangos.commons.network.model.ReceivablePacket#readImpl()
     */
    @Override
    protected void readImpl() throws BufferUnderflowException, RuntimeException {

        this.accountName = readS();
    }

    /**
     * 
     * @see org.jmangos.commons.network.model.ReceivablePacket#runImpl()
     */
    @Override
    protected void runImpl() {

        final AccountInfo account = this.accountController.getAndCleanAccount(this.accountName);
        this.sender.send(getClient(), new SMD_SESSION_KEY(account));

    }
}
