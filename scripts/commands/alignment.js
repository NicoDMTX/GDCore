importClass(org.graviton.database.repository.CommandRepository);
importClass(org.graviton.game.command.api.AbstractCommand);

commandRepository.register(JavaAdapter(AbstractCommand, {
    name : function(){
        return "alignment";
    },

    description : function(){
        return "";
    },

    apply: function(player, data){
        player.changeAlignment(Byte.parseByte(data[1]));
    }

}));