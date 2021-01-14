Java.asJSONCompatible({
    animation: function (player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, modelMap) {
        flash1 = modelMap.get("flash_frame_1");
        flash2 = modelMap.get("flash_frame_2");
        remainder = ageInTicks % 40;
        if (flash1 != undefined) {
                    flash1.setHidden(!(35 < remainder && remainder < 40));
                }

        if (flash2 != undefined) {
                    flash2.setHidden(!(33 < remainder && remainder < 38));
                }
    }
})