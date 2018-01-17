
package si.fri.rso.katalogstrani;

import java.util.ArrayList;
import java.util.List;


public class Database {
    private static List<Stran> strans = new ArrayList<>();

    public static List<Stran> getStrans() {
        /*Stran cus = new Stran();
        cus.setId("1");
        cus.setTitle("PingPong KluB");
        cus.setAbout("Za vse ki jih zanima odbijanje žogice z loparjem. Ni starostne omejitve. Več informacij na telefon.");
        strans.add(cus);*/

        return strans;
    }

    public static Stran getStran(String stranId) {
        for (Stran stran : strans) {
            if (stran.getId().equals(stranId))
                return stran;
        }

        return null;
    }

    public static void addStran(Stran stran) {
        strans.add(stran);
    }

    public static void deleteStran(String stranId) {
        for (Stran stran : strans) {
            if (stran.getId().equals(stranId)) {
                strans.remove(stran);
                break;
            }
        }
    }
}
