package hu.multialarm.excellhandler.util;

import hu.multialarm.excellhandler.model.excel.Excel;

import java.util.ArrayList;
import java.util.List;

public class Utilitizer {

    public List<Excel> generateExcellList() {
        List<Excel> result = new ArrayList<>();
        Excel dummy = new Excel();
        dummy.setFileName("Dummy");
        result.add(dummy);
        Excel dummy2 = new Excel();
        dummy2.setFileName("Paddy");
        result.add(dummy2);
        dummy2.setFileName("Fradie");
        result.add(dummy2);
        dummy2.setFileName("Mady");
        result.add(dummy2);
        return result;
    }
}