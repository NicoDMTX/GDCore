package org.graviton.client.player;

import lombok.Data;
import org.graviton.client.account.Account;
import org.graviton.client.player.breeds.AbstractBreed;
import org.graviton.game.look.PlayerLook;
import org.graviton.game.statistics.PlayerStatistics;
import org.graviton.utils.StringUtils;
import org.jooq.Record;

import static org.graviton.database.jooq.login.tables.Players.PLAYERS;

/**
 * Created by Botan on 05/11/2016 : 22:57
 */
@Data
public class Player {
    private final int id;
    private final Account account;

    private PlayerLook look;
    private String name;
    private AbstractBreed breed;
    private byte sex;
    private short skin;
    private int[] colors;
    private byte level;
    private long experience;
    private short statisticPoints;
    private short spellPoints;
    private short energy;

    private byte orientation;

    private PlayerStatistics playerStatistics;

    public Player(Record record, Account account) {
        this.account = account;
        this.id = record.get(PLAYERS.ID);
        this.look = new PlayerLook(record);

        this.level = record.get(PLAYERS.LEVEL);
        this.experience = record.get(PLAYERS.EXPERIENCE);
        this.statisticPoints = record.get(PLAYERS.STAT_POINTS);
        this.spellPoints = record.get(PLAYERS.SPELL_POINTS);
        this.energy = record.get(PLAYERS.ENERGY);
    }

    public Player(int id, String data, Account account) {
        this.account = account;

        String[] informations = data.split("\\|");

        this.id = id;
        this.name = informations[0];
        this.breed = AbstractBreed.get(Byte.parseByte(informations[1]));
        this.sex = Byte.parseByte(informations[2]);
        this.skin = this.breed.getDefaultSkin(this.sex);
        this.colors = StringUtils.parseColors(informations[3] + ";" + informations[4] + ";" + informations[5]);
        this.level = 100;
        this.experience = 0;
        this.statisticPoints = 0;
        this.spellPoints = 0;
        this.energy = 10000;
    }

    public int getColor(byte color) {
        return this.colors[color - 1];
    }


}
