package org.graviton.network.game.handler;

import lombok.extern.slf4j.Slf4j;
import org.graviton.game.client.player.Player;
import org.graviton.game.look.enums.Orientation;
import org.graviton.network.game.GameClient;
import org.graviton.network.game.protocol.GamePacketFormatter;

/**
 * Created by Botan on 07/12/2016. 14:26
 */

@Slf4j
public class EnvironmentHandler {
    private final GameClient client;

    public EnvironmentHandler(GameClient client) {
        this.client = client;
    }

    public void handle(String data, char subHeader) {
        switch (subHeader) {
            case 'D': // 'D'
                changeOrientation(Byte.parseByte(data));
                break;

            default:
                log.error("not implemented environment packet '{}'", subHeader);
        }
    }

    private void changeOrientation(byte orientation) {
        Player player = client.getPlayer();
        player.getMap().send(GamePacketFormatter.changeOrientationMessage(player.getId(), orientation));
        player.getLocation().setOrientation(Orientation.valueOf(orientation));
    }
}
