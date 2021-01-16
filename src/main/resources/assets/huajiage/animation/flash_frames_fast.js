Java.asJSONCompatible({
    animation: function (player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, modelMap) {
        flash1 = modelMap.get("flash_frame_1_f");
        flash2 = modelMap.get("flash_frame_2_f");
        remainder = ageInTicks % 20;
        if (flash1 != undefined) {
                    flash1.setHidden(!(15 < remainder && remainder < 20));
                }

        if (flash2 != undefined) {
                    flash2.setHidden(!(13 < remainder && remainder < 18));
                }
    }
})