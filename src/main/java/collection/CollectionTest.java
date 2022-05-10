package collection;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * You should complete the function in this class
 * <p>
 * Feel free to define any method and / or class you want
 */
class CollectionTest {


  /**
   * operation : x -> ((x * 2) + 3) ^ 5
   */
  public static List<Double> compute1(List<Integer> input) {
    List<Double> res = new ArrayList<>();
    for (Integer num : input) {
        Double newNum = Double.valueOf(num * 2);
        newNum += 3;
        newNum = Math.pow(newNum, 5);
        res.add(newNum);
    }
    return res;
  }

  /**
   * operation : abc -> AbcAbc
   */
  public static List<String> compute2(List<String> input) {
      List<String> res = new ArrayList<>();
      for (String str : input) {
          if (str.length() >= 1) {
              String firstCap = str.substring(0, 1).toUpperCase() + str.substring(1);
              StringBuilder newStr = new StringBuilder(firstCap);
              newStr.append(firstCap);
              res.add(newStr.toString());
          }
          else
              res.add("");
      }
      return res;
  }

}
