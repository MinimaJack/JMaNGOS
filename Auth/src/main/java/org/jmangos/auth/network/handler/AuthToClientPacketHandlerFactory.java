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
package org.jmangos.auth.network.handler;

import org.jmangos.commons.network.handlers.AbstractPacketHandlerFactory;
import org.jmangos.commons.network.model.PacketData;
import org.springframework.stereotype.Component;

/**
 * 
 * @author MinimaJack
 */
@Component("сlientPacketHandlerFactory")
public class AuthToClientPacketHandlerFactory extends AbstractPacketHandlerFactory {

    /**
     * 
     * @see org.jmangos.commons.network.handlers.PacketHandlerFactory#loadPacket()
     */
    @Override
    public void loadPacket() {

        addList(loadStaticData(PacketData.class, this.packetXSDLocation, this.clientPacketPath));

    }
}
