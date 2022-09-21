package com.example.react_gradle.reactbackend.Util;
import java.util.Map;
import java.util.List;
public class Util {
    public static boolean isEmpty(Object obj) {
        if(obj == null) return true;
        if ((obj instanceof String) && (((String)obj).trim().length() == 0))
        { return true; }
        if (obj instanceof Map)
        { return ((Map<?, ?>) obj).isEmpty(); }
        if (obj instanceof Map) {
            return ((Map<?, ?>)obj).isEmpty();
        }
        if (obj instanceof List) {
            return ((List<?>)obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            return (((Object[])obj).length == 0);
        }

        return false;
    }
}

