Java.asJSONCompatible({
    animation: function (player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, modelMap) {
        left_hands = modelMap.get("left_hands_ro");
        right_hands = modelMap.get("right_hands_ro");
        if (left_hands != undefined) {
          left_hands.setRotateAngleX(headPitch * 0.017453292);
          left_hands.setRotateAngleY(netHeadYaw * 0.017453292);
        }
        if (right_hands != undefined) {
          right_hands.setRotateAngleX(headPitch * 0.017453292);
          right_hands.setRotateAngleY(netHeadYaw * 0.017453292);
        }
    }
})