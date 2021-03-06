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
/**
 * 
 */
package org.jmangos.realm.network.factory;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jmangos.commons.network.factory.BasicPipelineFactory;
import org.jmangos.commons.network.handlers.PacketHandlerFactory;
import org.jmangos.commons.network.model.ConnectHandler;
import org.jmangos.commons.network.receiver.Netty2PacketReceiver;
import org.jmangos.realm.network.decoder.PacketFrameDecoder;
import org.jmangos.realm.network.decoder.PacketFrameEncoder;
import org.jmangos.realm.network.handler.EventLogHandler;
import org.jmangos.realm.network.handler.RealmToClientChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A factory for creating R2CPipeline objects.
 * 
 * @author MinimaJack
 */
@Component("realmToClientPipelineFactory")
public class RealmToClientPipelineFactory extends BasicPipelineFactory {

    /** The connection handler. */
    @Autowired
    @Qualifier("realmToClientConnectHandler")
    private ConnectHandler connectionHandler;

    /** The packet service. */
    @Autowired
    @Qualifier("сlientPacketHandlerFactory")
    private PacketHandlerFactory packetService;

    /**
     * 
     * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
     */
    @Override
    public ChannelPipeline getPipeline() throws Exception {

        final ChannelPipeline pipeline = pipeline();

        pipeline.addLast("framedecoder", new PacketFrameDecoder());
        pipeline.addLast("encoder", new PacketFrameEncoder());
        pipeline.addLast("executor", getExecutorHandler());

        pipeline.addLast("eventlog", new EventLogHandler());

        // and then business logic.
        pipeline.addLast("handler", new RealmToClientChannelHandler(this.packetService,
                this.connectionHandler, new Netty2PacketReceiver()));

        return pipeline;
    }
}
