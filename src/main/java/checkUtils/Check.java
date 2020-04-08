package checkUtils;

public class Check {
    private Check() {
    }

    public static boolean isRightInitInfo(String initInfo) {
        if (initInfo == null || initInfo.equals("")) {
            return false;
        }
        String[] parkings = initInfo.trim().split(",");
        if (parkings.length != 2) {
            return false;
        }
        for (String parking : parkings) {
            if (parking.trim().split(":").length != 2) {
                return false;
            }
        }
        return true;
    }
}
