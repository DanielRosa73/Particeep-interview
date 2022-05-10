package basic;

import io.vavr.control.Option;

/**
 * For this basic test, you should not use any library. e.g. you should not use Math.pow for power method
 */
public class BasicTest {

  /**
   * return i ^ n for positive Integer, None otherwise
   * alse return None in case of errors
   */
  public static Option<Integer> power(Integer i, Integer n) {
     
      if (n < 0 || i < 0)
        return Option.none();
      if (n == 0)
          return Option.of(1);
      int res = i;
      for (int x = 1; x < n; x++){
          long oTest = (long)res * (long)i;
          if (oTest > Integer.MAX_VALUE) {
              return Option.none();
          }
          res = res * i;
          }

      if (res > Integer.MAX_VALUE)
          return Option.none();
      return Option.of(res);
  }
}
