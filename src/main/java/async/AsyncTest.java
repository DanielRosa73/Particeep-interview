package async;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Option;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * You should complete the function in this class
 */
class AsyncTest {

  private static List<Enterprise> enterprises = List.of(
      new Enterprise("ent_1", "Google", "ceo_2"),
      new Enterprise("ent_2", "Facebook", "ceo_1")
  );

  private static List<Ceo> ceos = List.of(
      new Ceo("ceo_1", "Mark"),
      new Ceo("ceo_2", "Sundar"),
      new Ceo("ceo_3", "Bill")
  );

  public static CompletableFuture<Option<Ceo>> getCeoById(String ceo_id) {
    CompletableFuture<Option<Ceo>> res = CompletableFuture.supplyAsync(new Supplier<Option<Ceo>>() {
      @Override
      public Option<Ceo> get() {
        for (Ceo ceo : ceos) {
          if (ceo.id == ceo_id)
            return Option.of(ceo);
        }
        return Option.none();
      }
    });
    return res;
  }

  public static CompletableFuture<Option<Enterprise>> getEnterpriseByCeoId(String ceo_id) {
    CompletableFuture<Option<Enterprise>> res = CompletableFuture.supplyAsync(new Supplier<Option<Enterprise>>() {
      @Override
      public Option<Enterprise> get() {
        for (Enterprise enterprise : enterprises) {
          if (enterprise.ceo_id == ceo_id)
            return Option.of(enterprise);
        }
        return Option.none();
      }
    });
    return res;
  }

  public static CompletableFuture<Tuple2<Option<Ceo>, Option<Enterprise>>> getCEOAndEnterprise(String ceo_id) {
    CompletableFuture<Tuple2<Option<Ceo>, Option<Enterprise>>> res = CompletableFuture.supplyAsync(new Supplier<Tuple2<Option<Ceo>, Option<Enterprise>>>() {
      @Override
      public Tuple2<Option<Ceo>, Option<Enterprise>> get() {
        Option<Ceo> oCeo = Option.none();
        Option<Enterprise> oEnterprise = Option.none();

        try {
          oCeo = getCeoById(ceo_id).get();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } catch (ExecutionException e) {
          throw new RuntimeException(e);
        }
        try {
          oEnterprise = getEnterpriseByCeoId(ceo_id).get();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } catch (ExecutionException e) {
          throw new RuntimeException(e);
        }
        Tuple2<Option<Ceo>, Option<Enterprise>> res = new Tuple2<>(oCeo, oEnterprise);
        return res;
      }
    });
    return res;
  }

}
