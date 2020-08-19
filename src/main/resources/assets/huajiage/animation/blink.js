Java.asJSONCompatible({
    animation: function (player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, modelMap) {
        blink = modelMap.get("blink");

        if (blink != undefined) {
            remainder = ageInTicks % 60;
            blink.setHidden(!(55 < remainder && remainder < 60));
        }
    }
})