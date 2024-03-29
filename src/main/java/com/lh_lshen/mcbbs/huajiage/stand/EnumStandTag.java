package com.lh_lshen.mcbbs.huajiage.stand;

public class EnumStandTag {
    public enum StateTags{
        FLY("fly"),
        UNDEAD("undead"),
        RIDE("ride"),
        BLOCK_MOVE("block_move"),
        ELEMENT_LIGHT("element_light"),
        DISC_DEPRIVE("disc_deprive"),
        SOUND_DIE("sound-die:")
        ;

        StateTags(String name) {
            this.name = name;
        }
        private String name;

        public String getName() {
            return name;
        }
    }

    public enum ModelTags{
        BIKE("bike"),
        ;

        ModelTags(String name) {
            this.name = name;
        }
        private String name;

        public String getName() {
            return name;
        }
    }

    public enum StandTags{
        ARROW("arrow"),
        ;

        StandTags(String name) {
            this.name = name;
        }
        private String name;

        public String getName() {
            return name;
        }
    }


}
